package committee.nova.plg.common.integration;

import committee.nova.plg.Plg;
import committee.nova.plg.api.energy.ITileEnergyHandler;
import committee.nova.plg.utils.PlgUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 10:26
 * Version: 1.0
 */
public class ForgeEnergyHandler implements ITileEnergyHandler {

    public static final ForgeEnergyHandler INSTANCE = new ForgeEnergyHandler();

    private ForgeEnergyHandler() {
    }

    @Override
    public boolean hasCapability(@Nonnull TileEntity tile, @Nonnull Direction dir) {
        return !tile.isRemoved() && tile.getCapability(CapabilityEnergy.ENERGY, dir).isPresent();
    }

    @Override
    public boolean canAddEnergy(@Nonnull TileEntity tile, @Nonnull Direction dir) {
        if (!tile.isRemoved()) {
            IEnergyStorage storage = PlgUtil.get(tile.getCapability(CapabilityEnergy.ENERGY, dir));
            if (storage != null) {
                return storage.canReceive();
            }
        }
        return false;
    }

    @Override
    public boolean canRemoveEnergy(@Nonnull TileEntity tile, @Nonnull Direction dir) {
        if (!tile.isRemoved()) {
            IEnergyStorage storage = PlgUtil.get(tile.getCapability(CapabilityEnergy.ENERGY, dir));
            if (storage != null) {
                return storage.canExtract();
            }
        }
        return false;
    }

    @Override
    public long addEnergy(long amount, @Nonnull TileEntity tile, @Nonnull Direction dir, boolean simulate) {
        IEnergyStorage storage = PlgUtil.get(tile.getCapability(CapabilityEnergy.ENERGY, dir));
        return storage == null ? 0 : storage.receiveEnergy((int) Math.min(amount, Integer.MAX_VALUE), simulate);
    }

    @Override
    public long removeEnergy(long amount, @Nonnull TileEntity tile, @Nonnull Direction dir) {
        IEnergyStorage storage = PlgUtil.get(tile.getCapability(CapabilityEnergy.ENERGY, dir));
        return storage == null ? 0 : storage.extractEnergy((int) Math.min(amount, Integer.MAX_VALUE), false);
    }
}
