package committee.nova.plg.common.block.base;

import committee.nova.plg.common.blockEntity.base.PLGBlockEntity;
import committee.nova.plg.common.utils.FormatUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class PLGBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {

    private static final ResourceLocation WRENCH = new ResourceLocation("forge", "wrench");//通用扳手
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;//含水方块？
    private final PLGType type;//种类


    public PLGBlock(PLGType plgType) {
        super(Properties.of(Material.METAL)
                .noOcclusion()
                .strength(3F, 10F)
                .sound(SoundType.METAL)
        );
        this.registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
        this.type = plgType;
    }

    public PLGType getType() {
        return type;
    }

    @Nonnull
    @Override
    public InteractionResult use(@Nonnull BlockState pState, Level pLevel, @Nonnull BlockPos pPos, @Nonnull Player pPlayer, @Nonnull InteractionHand pInteractionHand, @Nonnull BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            //使用扳手
            if (pPlayer.isCrouching()) {
                if (pPlayer.getMainHandItem().getItem().getTags().contains(WRENCH)) {
                    dismantleBlock(pLevel, pPos);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return null;
        } else {
            return (level1, pos, state1, tile) -> {
                if (tile instanceof PLGBlockEntity generator) {
                    generator.tickServer(state1);
                }
            };
        }
    }

    @Override
    public void playerDestroy(@Nonnull Level worldIn, @Nonnull Player player, BlockPos pos, @Nonnull BlockState state, BlockEntity te, @Nonnull ItemStack stack) {
        super.playerDestroy(worldIn, player, pos, state, te, stack);
        worldIn.removeBlock(pos, false);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, @Nonnull List<Component> pTooltip, @Nonnull TooltipFlag pFlag) {
        final CompoundTag tag = pStack.getTagElement("BlockEntityTag");
        final int energy = (tag != null && tag.contains("energy")) ? tag.getCompound("energy").getInt("value") : 0;
        FormatUtil.showInfoCtrl(energy, pTooltip);
        FormatUtil.showInfoShift(this.type, pTooltip);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new PLGBlockEntity(type, pos, state);
    }

    private void dismantleBlock(Level worldIn, BlockPos pos) {
        final ItemStack itemStack = new ItemStack(this);
        final PLGBlockEntity localTileEntity = (PLGBlockEntity) worldIn.getBlockEntity(pos);
        if (localTileEntity == null) {
            return;
        }
        final int internalEnergy = localTileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
        if (internalEnergy > 0) {
            final CompoundTag energyValue = new CompoundTag();
            energyValue.putInt("value", internalEnergy);

            final CompoundTag energy = new CompoundTag();
            energy.put("energy", energyValue);

            final CompoundTag root = new CompoundTag();
            root.put("BlockEntityTag", energy);
            itemStack.setTag(root);
        }

        worldIn.removeBlock(pos, false);

        final ItemEntity entityItem = new ItemEntity(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);

        entityItem.setDeltaMovement(0, entityItem.getMyRidingOffset(), 0);
        worldIn.addFreshEntity(entityItem);
    }


}
