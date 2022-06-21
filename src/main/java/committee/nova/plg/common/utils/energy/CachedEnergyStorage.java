package committee.nova.plg.common.utils.energy;

import committee.nova.plg.api.energy.IEnergyHandler;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CachedEnergyStorage {
    public static final CachedEnergyStorage[] EMPTY = new CachedEnergyStorage[0];

    public CachedEnergyStorageOrigin origin;
    public int distance;
    public BlockEntity blockEntity;
    public IEnergyHandler energyHandler;

    public int hashCode() {
        return blockEntity.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof CachedEnergyStorage && blockEntity == ((CachedEnergyStorage) o).blockEntity;
    }

    public boolean isInvalid() {
        return origin.cableBurnt || energyHandler.isEnergyHandlerInvalid();
    }

    @Override
    public String toString() {
        return String.format("%s@%d,%d,%d", blockEntity.getType().getRegistryName(), blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ());
    }

    public boolean shouldReceiveEnergy() {
        return energyHandler.getEnergy() < energyHandler.getEnergyCapacity();
    }
}
