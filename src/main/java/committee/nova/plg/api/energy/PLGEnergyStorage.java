package committee.nova.plg.api.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.energy.EnergyStorage;

public class PLGEnergyStorage extends EnergyStorage {

    /**
     * @param energyTransfer 传输值
     * @param energyCapacity 容量
     */
    public PLGEnergyStorage(int energyTransfer, int energyCapacity) {
        super(energyCapacity, energyTransfer);
        this.maxReceive = 0;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void generatePower(int energy) {
        this.energy += energy;
        if (this.energy > capacity)
            this.energy = capacity;
    }

    public void consumePower(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public boolean isFullEnergy() {
        return getEnergyStored() >= getMaxEnergyStored();
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt("value", getEnergyStored());
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        setEnergy(tag.getInt("energy"));
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Cannot deserialize to an instance that isn't the default implementation!");
        setEnergy(intNbt.getAsInt());
    }
}
