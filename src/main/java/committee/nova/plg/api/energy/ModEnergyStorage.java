package committee.nova.plg.api.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 9:46
 * Version: 1.0
 */
public class ModEnergyStorage implements IModEnergyStorage, IEnergyStorage {

    protected long energy;//能量
    protected long capacity;//容量
    protected long maxReceive;//最大输入
    protected long maxExtract;//最大输出

    public ModEnergyStorage(long capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public ModEnergyStorage(long capacity, long maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public ModEnergyStorage(long capacity, long maxReceive, long maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public ModEnergyStorage(long capacity, long maxReceive, long maxExtract, long energy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0, Math.min(capacity, energy));
    }


    public void setEnergy(long energy) {
        this.energy = energy;
    }

    @Override
    public long receiveEnergyL(long maxReceive, boolean simulate) {
        if (!canReceiveL()) {
            return 0;
        }

        long energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;    }

    @Override
    public long extractEnergyL(long maxExtract, boolean simulate) {
        if (!canExtractL()) {
            return 0;
        }

        long energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    public long getEnergyStoredL() {
        return energy;
    }

    @Override
    public long getMaxEnergyStoredL() {
        return capacity;
    }

    @Override
    public boolean canExtractL() {
        return maxExtract > 0;
    }

    @Override
    public boolean canReceiveL() {
        return maxReceive > 0;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return (int) Math.min(receiveEnergyL(maxReceive, simulate), Integer.MAX_VALUE);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return (int) Math.min(extractEnergyL(maxExtract, simulate), Integer.MAX_VALUE);
    }

    @Override
    public int getEnergyStored() {
        return (int) Math.min(getEnergyStoredL(), Integer.MAX_VALUE);
    }

    @Override
    public int getMaxEnergyStored() {
        return (int) Math.min(getMaxEnergyStoredL(), Integer.MAX_VALUE);
    }

    @Override
    public boolean canExtract() {
        return canExtractL();
    }

    @Override
    public boolean canReceive() {
        return canExtractL();
    }


    public void serializeNBT(CompoundNBT tag) {
        tag.putInt("energy", getEnergyStored());
    }

    public void deserializeNBT(CompoundNBT tag) {
        setEnergy(tag.getInt("energy"));
    }

    public static class CapStorage implements Capability.IStorage<IModEnergyStorage> {

        @Override
        public INBT writeNBT(Capability<IModEnergyStorage> capability, @Nonnull IModEnergyStorage instance, Direction side) {
            return LongNBT.valueOf(instance.getEnergyStoredL());
        }

        @Override
        public void readNBT(Capability<IModEnergyStorage> capability, IModEnergyStorage instance, Direction side, INBT nbt) {
            if (!(instance instanceof ModEnergyStorage))
                throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
            ((ModEnergyStorage) instance).energy = ((LongNBT) nbt).getAsLong();
        }
    }
}
