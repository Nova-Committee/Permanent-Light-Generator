package committee.nova.plg.common.blocks;

import committee.nova.plg.common.tiles.PlgTileEntity;
import committee.nova.plg.utils.FormatUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 11:32
 * Version: 1.0
 */
public class PlgBlock extends Block implements IWaterLoggable {

    private final PlgType type;//种类
    private static final ResourceLocation WRENCH = new ResourceLocation("forge", "wrench");//通用扳手
    private static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");//含水方块？



    public PlgBlock(PlgType plgType) {
        super(Properties.of(Material.METAL)
                .noOcclusion()
                .strength(3F, 10F)
                .sound(SoundType.METAL)
        );
        this.registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
        this.type = plgType;
    }

    public PlgType getType() {
        return type;
    }

    @Nonnull
    @Override
    public ActionResultType use(@Nonnull BlockState pState, World pLevel, @Nonnull BlockPos pPos, @Nonnull PlayerEntity pPlayer, @Nonnull Hand pHand, @Nonnull BlockRayTraceResult pHit) {
        if (!pLevel.isClientSide) {
            //使用扳手
            if (pPlayer.isCrouching()) {
                if (pPlayer.getMainHandItem().getItem().getTags().contains(WRENCH)) {
                    dismantleBlock(pLevel, pPos);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
        return willHarvest || super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
    }

    @Override
    public void playerDestroy(@Nonnull World worldIn, @Nonnull PlayerEntity player, @Nonnull BlockPos pos, @Nonnull BlockState state, TileEntity te, @Nonnull ItemStack stack) {
        super.playerDestroy(worldIn, player, pos, state, te, stack);
        worldIn.removeBlock(pos, false);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable IBlockReader pLevel, @Nonnull List<ITextComponent> pTooltip, @Nonnull ITooltipFlag pFlag) {
        final CompoundNBT compoundnbt = pStack.getTagElement("BlockEntityTag");
        final int energy = (compoundnbt != null && compoundnbt.contains("energy")) ? compoundnbt.getCompound("energy").getInt("value") : 0;

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
    public boolean placeLiquid(@Nonnull IWorld worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull FluidState fluidStateIn) {
        return IWaterLoggable.super.placeLiquid(worldIn, pos, state, fluidStateIn);
    }

    @Override
    public boolean canPlaceLiquid(@Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, @Nonnull Fluid fluidIn) {
        return IWaterLoggable.super.canPlaceLiquid(worldIn, pos, state, fluidIn);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PlgTileEntity(type);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    private void dismantleBlock(World worldIn, BlockPos pos) {
        final ItemStack itemStack = new ItemStack(this);

        final PlgTileEntity localTileEntity = (PlgTileEntity) worldIn.getBlockEntity(pos);
        if (localTileEntity == null) {
            return;
        }
        final int internalEnergy = localTileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
        if (internalEnergy > 0) {
            final CompoundNBT energyValue = new CompoundNBT();
            energyValue.putInt("value", internalEnergy);

            final CompoundNBT energy = new CompoundNBT();
            energy.put("energy", energyValue);

            final CompoundNBT root = new CompoundNBT();
            root.put("BlockEntityTag", energy);
            itemStack.setTag(root);
        }

        worldIn.removeBlock(pos, false);

        final ItemEntity entityItem = new ItemEntity(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);

        entityItem.setDeltaMovement(0, entityItem.getMyRidingOffset(), 0);
        worldIn.addFreshEntity(entityItem);
    }

    @Nonnull
    @Override
    public List<ItemStack> getDrops(@Nonnull BlockState state, @Nonnull LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(this, 1));
    }
}
