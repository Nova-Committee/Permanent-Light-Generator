package committee.nova.plg.common.integration;

import committee.nova.plg.api.energy.IEnergyHandler;
import net.minecraft.util.Mth;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

public class ForgeEnergyHandler implements IEnergyHandler {

    private static final double MOD_TO_FE_CONVERSION_RATE = 4.0;
    public final LazyOptional<IEnergyStorage> optional;
    public final IEnergyStorage storage;

    public ForgeEnergyHandler(LazyOptional<IEnergyStorage> o, IEnergyStorage s) {
        optional = o;
        storage = s;
    }

    @Override
    public boolean isEnergyHandlerInvalid() {
        return !optional.isPresent();
    }

    @Override
    public double getEnergyCapacity() {
        return storage.getMaxEnergyStored() / MOD_TO_FE_CONVERSION_RATE;
    }

    @Override
    public double getEnergy() {
        return storage.getEnergyStored() / MOD_TO_FE_CONVERSION_RATE;
    }

    @Override
    public void setEnergyRaw(double e) {
        if (storage.getEnergyStored() <= 0 || storage.extractEnergy(storage.getEnergyStored(), false) > 0) {
            storage.receiveEnergy(Mth.ceil(e * MOD_TO_FE_CONVERSION_RATE), false);
        }
    }

    @Override
    public double insertEnergy(double maxInsert, boolean simulate) {
        return storage.receiveEnergy(Mth.ceil(maxInsert * MOD_TO_FE_CONVERSION_RATE), simulate) / MOD_TO_FE_CONVERSION_RATE;
    }
}
