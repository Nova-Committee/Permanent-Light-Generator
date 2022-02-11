package committee.nova.plg.api.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 9:46
 * Version: 1.0
 */
public class ModEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {

    /**
     *
     * @param energyTransfer 传输值
     * @param energyCapacity 容量
     */
    public ModEnergyStorage(int energyTransfer, int energyCapacity)
    {
        super(energyCapacity, energyTransfer);
        this.maxReceive = 0;
    }

    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    public void generatePower(int energy)
    {
        this.energy += energy;
        if(this.energy > capacity)
            this.energy = capacity;
    }

    public void consumePower(int energy)
    {
        this.energy -= energy;
        if(this.energy < 0)
        {
            this.energy = 0;
        }
    }

    public boolean isFullEnergy()
    {
        return getEnergyStored() >= getMaxEnergyStored();
    }

    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT tag = new CompoundNBT();
        tag.putInt("value", getEnergyStored());
        return tag;
    }

    public void deserializeNBT(CompoundNBT tag) {
        setEnergy(tag.getInt("energy"));
    }

}