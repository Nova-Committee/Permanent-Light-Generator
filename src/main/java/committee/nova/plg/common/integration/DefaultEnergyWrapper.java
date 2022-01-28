//package committee.nova.plg.common.integration;
//
//import committee.nova.plg.api.energy.IModEnergyStorage;
//import net.minecraft.util.Direction;
//import net.minecraftforge.energy.IEnergyStorage;
//
///**
// * Description:
// * Author: cnlimiter
// * Date: 2022/1/28 20:30
// * Version: 1.0
// */
//public class DefaultEnergyWrapper implements IEnergyStorage, IModEnergyStorage {
//
//    private final IFluxDevice device;
//    private final Direction side;
//
//    public DefaultEnergyWrapper(IFluxDevice device, Direction side) {
//        this.device = device;
//        this.side = side;
//    }
//
//    ///// FLUX EXTENDED \\\\\
//
//    @Override
//    public long receiveEnergyL(long maxReceive, boolean simulate) {
//        if (device.getDeviceType().isPlug() && device.isActive()) {
//            return device.getTransferHandler().receiveFromSupplier(maxReceive, side, simulate);
//        }
//        return 0;
//    }
//
//    @Override
//    public long extractEnergyL(long maxExtract, boolean simulate) {
//        return 0;
//    }
//
//    @Override
//    public long getEnergyStoredL() {
//        return device.getTransferBuffer();
//    }
//
//    @Override
//    public long getMaxEnergyStoredL() {
//        return device.getMaxTransferLimit();
//    }
//
//    @Override
//    public boolean canExtractL() {
//        return false;
//    }
//
//    @Override
//    public boolean canReceiveL() {
//        return device.getDeviceType().isPlug();
//    }
//
//    ///// FORGE \\\\\
//
//    @Override
//    public int receiveEnergy(int maxReceive, boolean simulate) {
//        // so other mods didn't check if this canReceive() at all
//        if (device.getDeviceType().isPlug() && device.isActive()) {
//            return (int) device.getTransferHandler().receiveFromSupplier(maxReceive, side, simulate);
//        }
//        return 0;
//    }
//
//    @Override
//    public int extractEnergy(int maxExtract, boolean simulate) {
//        return 0;
//    }
//
//    @Override
//    public int getEnergyStored() {
//        return (int) Math.min(device.getTransferBuffer(), Integer.MAX_VALUE);
//    }
//
//    @Override
//    public int getMaxEnergyStored() {
//        return (int) Math.min(device.getMaxTransferLimit(), Integer.MAX_VALUE);
//    }
//
//    @Override
//    public boolean canExtract() {
//        return false;
//    }
//
//    @Override
//    public boolean canReceive() {
//        return device.getDeviceType().isPlug();
//    }
//}
