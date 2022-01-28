package committee.nova.plg.common.tiles;

import committee.nova.plg.api.energy.ModEnergyStorage;
import committee.nova.plg.common.blocks.PlgBlock;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.init.ModTileEntities;
import committee.nova.plg.utils.energy.CachedEnergyStorage;
import committee.nova.plg.utils.energy.PLGEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.world.LightType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:30
 * Version: 1.0
 */
public class PlgTileEntity extends TileEntity implements ITickableTileEntity {


    private final PLGEnergyStorage energyStorage = createEnergy();
    private final LazyOptional<PLGEnergyStorage> energy = LazyOptional.of(() -> energyStorage);
    private double maxOutput;

    public PlgTileEntity() {
        super(ModTileEntities.PLG);
    }

    public PlgType getPlgType(){
        return  ((PlgBlock)this.getBlockState().getBlock()).getType();
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        energy.invalidate();
    }

    @Override
    public void tick() {
        final PlgType plgType = getPlgType();
        maxOutput = getPlgType().getPower().getMaxProduction();

        if (level != null && level.dimensionType().hasSkyLight()) {
            final int light = level.getBrightness(LightType.SKY, worldPosition.above()) - level.getSkyDarken();
            energyStorage.addEnergy((int) (maxOutput * (int) ((light + 1) / 16F)));
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
        final AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            final Direction[] directions = Direction.values();
            for (Direction direction : directions) {
                final TileEntity te = (level != null) ? level.getBlockEntity(worldPosition.relative(direction)) : null;
                if (te != null) {
                    final boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).map(handler ->
                            {
                                if (handler.canReceive()) {
                                    final int received = handler.receiveEnergy((int) Math.min(capacity.get(), maxOutput), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
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

    private PLGEnergyStorage createEnergy() {
        return new PLGEnergyStorage((int) maxOutput);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        energy.ifPresent(h -> h.serializeNBT(tag));
        return super.save(tag);
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT tag) {
        energy.ifPresent(h -> h.deserializeNBT(tag));
        super.load(p_230337_1_, tag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return energy.cast();    }






}
