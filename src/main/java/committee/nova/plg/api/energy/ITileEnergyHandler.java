package committee.nova.plg.api.energy;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

import javax.annotation.Nonnull;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 9:45
 * Version: 1.0
 */
public interface ITileEnergyHandler {

    boolean hasCapability(@Nonnull TileEntity tile, @Nonnull Direction dir);

    boolean canAddEnergy(@Nonnull TileEntity tile, @Nonnull Direction dir);

    boolean canRemoveEnergy(@Nonnull TileEntity tile, @Nonnull Direction dir);

    long addEnergy(long amount, @Nonnull TileEntity tile, @Nonnull Direction dir, boolean simulate);

    long removeEnergy(long amount, @Nonnull TileEntity tile, @Nonnull Direction dir);
}
