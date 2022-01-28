package committee.nova.plg.common.integration;

import committee.nova.plg.api.energy.IEnergyHandler;
import committee.nova.plg.api.energy.IModEnergyStorage;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 10:30
 * Version: 1.0
 */
public class ModEnergyHandler implements IEnergyHandler {

    public final LazyOptional<IModEnergyStorage> optional;
    public final IModEnergyStorage storage;
    private static final double RATE = 4.0;

    public ModEnergyHandler(LazyOptional<IModEnergyStorage> o, IModEnergyStorage s) {
        optional = o;
        storage = s;
    }

    @Override
    public boolean isEnergyHandlerInvalid() {
        return !optional.isPresent();
    }

    @Override
    public double getEnergyCapacity() {
        return storage.getMaxEnergyStoredL() ;
    }

    @Override
    public double getEnergy() {
        return storage.getMaxEnergyStoredL() ;
    }

    @Override
    public void setEnergyRaw(double e) {
        if (storage.getEnergyStoredL() <= 0 || storage.extractEnergyL(storage.getEnergyStoredL(), false) > 0) {
            storage.receiveEnergyL(MathHelper.ceil(e * RATE), false);
        }
    }

    @Override
    public double insertEnergy(double maxInsert, boolean simulate) {
        return storage.receiveEnergyL(MathHelper.ceil(maxInsert * RATE), simulate) / RATE;
    }
}
