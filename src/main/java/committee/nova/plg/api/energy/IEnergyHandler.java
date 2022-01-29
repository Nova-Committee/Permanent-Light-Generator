package committee.nova.plg.api.energy;


import net.minecraft.core.Direction;

public interface IEnergyHandler {

    default boolean isEnergyHandlerInvalid() {
        return false;
    }

    default double getMaxInputEnergy() {
        return 0D;
    }

    double getEnergyCapacity();

    double getEnergy();

    default void setEnergy(double energy) {
        double prev = getEnergy();

        if (prev != energy) {
            setEnergyRaw(energy);
            energyChanged(prev);
        }
    }

    void setEnergyRaw(double energy);

    /**
     * @param maxInsert 数值
     * @param simulate  限制
     * @return 传输能量
     */
    default double insertEnergy(double maxInsert, boolean simulate) {
        if (isBurnt()) {
            return 0D;
        }

        double max = getMaxInputEnergy();

        if (max <= 0D) {
            return 0;
        }

        if (maxInsert > max && canBurn()) {
            if (!simulate) {
                setBurnt(true);
            }

            return maxInsert;
        }

        double energy = getEnergy();
        double energyReceived = Math.min(getEnergyCapacity() - energy, Math.min(max, maxInsert));

        if (!simulate && energyReceived > 0D) {
            setEnergyRaw(energy + energyReceived);
            energyChanged(energy);
        }

        return energyReceived;
    }


    /**
     * @param prevEnergy 缓存
     */
    default void energyChanged(double prevEnergy) {
    }

    /**
     * @return 是否爆炸
     */
    default boolean canBurn() {
        return false;
    }

    default boolean isBurnt() {
        return false;
    }

    default void setBurnt(boolean burnt) {
    }

    default boolean isValidEnergyInputSide(Direction direction) {
        return true;
    }
}
