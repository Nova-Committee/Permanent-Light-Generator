package committee.nova.plg.common.tiles;

import com.google.common.collect.Lists;
import committee.nova.plg.api.energy.ModEnergyStorage;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.common.blocks.Power;
import committee.nova.plg.common.blocks.PlgBlock;
import committee.nova.plg.init.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.LightType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:30
 * Version: 1.0
 */
public class PlgTileEntity extends TileEntity implements ITickableTileEntity {



    /** 数据 */
    private final PlgType plgType;
    private final ModEnergyStorage energyStorage = createEnergy();
    private final LazyOptional<ModEnergyStorage> energy = LazyOptional.of(() -> energyStorage );


    public PlgTileEntity() {
        super(ModTileEntities.PLG);
        this.plgType = ((PlgBlock)getBlockState().getBlock()).getType();
    }




    public PlgType getPlgType() {
        return plgType;
    }

    private ModEnergyStorage createEnergy() {
        return new ModEnergyStorage(getPlgType().getPower().getMaxProduction());
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        energy.invalidate();
    }

    @Override
    public void tick() {
        if (level != null && level.dimensionType().hasSkyLight()) {
            final int light = level.getBrightness(LightType.SKY, worldPosition.above()) - level.getSkyDarken();
            energyStorage.receiveEnergy(getPlgType().getPower().getMaxProduction() * (int) ((light + 1) / 16F), false);
            setChanged();
        }


        final BlockState blockState = (level != null) ? level.getBlockState(worldPosition) : null;
        if (blockState != null) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true),
                    3);
        }
        sendOutPower();
    }



    @Override
    public void load(BlockState state, CompoundNBT pCompound) {
        energy.ifPresent(h -> h.deserializeNBT(pCompound));
        super.load(state, pCompound);
    }

    @Override
    public CompoundNBT save(CompoundNBT pCompound) {
        energy.ifPresent(h -> h.serializeNBT(pCompound));
        return super.save(pCompound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return energy.cast();
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
                                    final int received = handler.receiveEnergy(Math.min(capacity.get(), getPlgType().getPower().getMaxProduction()), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.extractEnergy(received, false);
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
