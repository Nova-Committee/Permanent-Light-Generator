package committee.nova.plg.api.energy;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 9:45
 * Version: 1.0
 */
public interface IModEnergyStorage {

    long receiveEnergyL(long maxReceive, boolean simulate);

    long extractEnergyL(long maxExtract, boolean simulate);

    long getEnergyStoredL();

    long getMaxEnergyStoredL();

    boolean canExtractL();

    boolean canReceiveL();
}
