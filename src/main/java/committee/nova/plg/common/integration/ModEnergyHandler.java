package committee.nova.plg.common.integration;

import committee.nova.plg.api.energy.IModEnergyStorage;
import committee.nova.plg.api.energy.ITileEnergyHandler;
import committee.nova.plg.common.cap.ModCapability;
import committee.nova.plg.utils.PlgUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import javax.annotation.Nonnull;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 10:30
 * Version: 1.0
 */
public class ModEnergyHandler implements ITileEnergyHandler {

    public static final ModEnergyHandler INSTANCE = new ModEnergyHandler();

    private ModEnergyHandler() {
    }

    @Override
    public boolean hasCapability(@Nonnull TileEntity tile, @Nonnull Direction side) {
        return !tile.isRemoved() && tile.getCapability(ModCapability.PLG_ENERGY_STORAGE, side).isPresent();
    }

    @Override
    public boolean canAddEnergy(@Nonnull TileEntity tile, @Nonnull Direction side) {
        if (!tile.isRemoved()) {
            IModEnergyStorage storage = PlgUtil.get(tile.getCapability(ModCapability.PLG_ENERGY_STORAGE, side));
            if (storage != null) {
                return storage.canReceiveL();
            }
        }
        return false;
    }

    @Override
    public boolean canRemoveEnergy(@Nonnull TileEntity tile, @Nonnull Direction side) {
        if (!tile.isRemoved()) {
            IModEnergyStorage storage = PlgUtil.get(tile.getCapability(ModCapability.PLG_ENERGY_STORAGE, side));
            if (storage != null) {
                return storage.canExtractL();
            }
        }
        return false;
    }

    @Override
    public long addEnergy(long amount, @Nonnull TileEntity tile, @Nonnull Direction side, boolean simulate) {
        IModEnergyStorage storage = PlgUtil.get(tile.getCapability(ModCapability.PLG_ENERGY_STORAGE, side));
        return storage == null ? 0 : storage.receiveEnergyL(amount, simulate);
    }

    @Override
    public long removeEnergy(long amount, @Nonnull TileEntity tile, @Nonnull Direction side) {
        IModEnergyStorage storage = PlgUtil.get(tile.getCapability(ModCapability.PLG_ENERGY_STORAGE, side));
        return storage == null ? 0 : storage.extractEnergyL(amount, false);
    }
}
