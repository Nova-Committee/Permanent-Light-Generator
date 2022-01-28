package committee.nova.plg.common.integration;

import committee.nova.plg.api.energy.IEnergyHandler;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 10:26
 * Version: 1.0
 */
public class ForgeEnergyHandler implements IEnergyHandler {

    public final LazyOptional<IEnergyStorage> optional;
    public final IEnergyStorage storage;
    private static final double MOD_TO_FE_CONVERSION_RATE = 4.0;

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
            storage.receiveEnergy(MathHelper.ceil(e * MOD_TO_FE_CONVERSION_RATE), false);
        }
    }

    @Override
    public double insertEnergy(double maxInsert, boolean simulate) {
        return storage.receiveEnergy(MathHelper.ceil(maxInsert * MOD_TO_FE_CONVERSION_RATE), simulate) / MOD_TO_FE_CONVERSION_RATE;
    }
}
