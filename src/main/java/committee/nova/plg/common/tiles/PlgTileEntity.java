package committee.nova.plg.common.tiles;

import committee.nova.plg.api.energy.ModEnergyStorage;
import committee.nova.plg.common.blocks.PlgBlock;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.init.ModTileEntities;
import committee.nova.plg.utils.energy.CachedEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.LightType;
import net.minecraftforge.energy.CapabilityEnergy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:30
 * Version: 1.0
 */
public class PlgTileEntity extends BaseTileEntity {





    /** 数据 */
    public double maxEnergyOutput;

    public PlgTileEntity() {
        super(ModTileEntities.PLG);
    }


    public PlgType getPlgType() {
        return ((PlgBlock)getBlockState().getBlock()).getType();
    }


    @Override
    public void tick() {
        final PlgType plgType = getPlgType();

        maxEnergyOutput = plgType.getPower().getMaxProduction();

        if (level != null && level.dimensionType().hasSkyLight()) {
            final int light = level.getBrightness(LightType.SKY, worldPosition.above()) - level.getSkyDarken();
            insertEnergy(maxEnergyOutput * (int) ((light + 1) / 16F), false);
            setChanged();
        }


        final BlockState blockState = (level != null) ? level.getBlockState(worldPosition) : null;
        if (blockState != null) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true),
                    3);
        }
        sendOutPower();
    }

    private void sendOutPower() {
        final AtomicInteger capacity = new AtomicInteger((int) getEnergy());
        if (capacity.get() > 0) {
            final Direction[] directions = Direction.values();
            for (Direction direction : directions) {
                final TileEntity te = (level != null) ? level.getBlockEntity(worldPosition.relative(direction)) : null;
                if (te != null) {
                    final boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).map(handler ->
                            {
                                if (handler.canReceive()) {
                                    final int received = handler.receiveEnergy((int) Math.min(capacity.get(), maxEnergyOutput), false);
                                    capacity.addAndGet(-received);
                                    consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }




}
