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

import javax.annotation.Nullable;
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


    @Override
    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if(!pLevel.isClientSide) {
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
    public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack)
    {
        super.playerDestroy(worldIn, player, pos, state, te, stack);
        worldIn.removeBlock(pos, false);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable IBlockReader pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
        CompoundNBT compoundnbt = pStack.getTagElement("BlockEntityTag");
        int energy = 0;
        if(compoundnbt != null)
            if(compoundnbt.contains("energy"))
                energy = compoundnbt.getCompound("energy").getInt("value");

        FormatUtil.showInfoCtrl(energy, pTooltip);
        FormatUtil.showInfoShift(this.type, pTooltip);
    }

    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn)
    {
        return IWaterLoggable.super.placeLiquid(worldIn, pos, state, fluidStateIn);
    }

    @Override
    public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn)
    {
        return IWaterLoggable.super.canPlaceLiquid(worldIn, pos, state, fluidIn);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
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


    private void dismantleBlock(World worldIn, BlockPos pos)
    {
        ItemStack itemStack = new ItemStack(this);

        PlgTileEntity localTileEntity = (PlgTileEntity) worldIn.getBlockEntity(pos);
        int internalEnergy = localTileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
        if(internalEnergy > 0)
        {
            CompoundNBT energyValue = new CompoundNBT();
            energyValue.putInt("value", internalEnergy);

            CompoundNBT energy = new CompoundNBT();
            energy.put("energy", energyValue);

            CompoundNBT root = new CompoundNBT();
            root.put("BlockEntityTag", energy);
            itemStack.setTag(root);
        }

        worldIn.removeBlock(pos, false);

        ItemEntity entityItem = new ItemEntity(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);

        entityItem.setDeltaMovement(0, entityItem.getMyRidingOffset(), 0);
        worldIn.addFreshEntity(entityItem);
    }


}
