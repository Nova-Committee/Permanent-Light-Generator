package committee.nova.plg.utils.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.tags.Tag;
import net.minecraftforge.energy.EnergyStorage;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 22:05
 * Version: 1.0
 */
public class PLGEnergyStorage extends EnergyStorage {
    public PLGEnergyStorage(int maxOutput) {
        super(maxOutput * 5, 0, maxOutput);
    }

    protected void onEnergyChanged() {
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        onEnergyChanged();
    }

    public void addEnergy(int energy) {
        this.energy += energy;
        if (this.energy > getMaxEnergyStored()) {
            this.energy = getMaxEnergyStored();
        }
        onEnergyChanged();
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
        onEnergyChanged();
    }

    public void serializeNBT(CompoundNBT tag) {
        tag.putInt("energy", getEnergyStored());
    }

    public void deserializeNBT(CompoundNBT tag) {
        setEnergy(tag.getInt("energy"));
    }


}
