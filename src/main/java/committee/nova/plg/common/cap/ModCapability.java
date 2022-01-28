package committee.nova.plg.common.cap;

import committee.nova.plg.api.energy.IModEnergyStorage;
import committee.nova.plg.api.energy.ModEnergyStorage;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 10:19
 * Version: 1.0
 */
public class ModCapability {
    /**
     * 当你的 mod 可以以大于 Integer.MAX_VALUE 的速率发送或者接收能量时才使用此功能。此模组将正常处理所有 FE 能量操作行为。
     *
     * 功能和 {@link net.minecraftforge.energy.CapabilityEnergy} b类似
     */
    @CapabilityInject(IModEnergyStorage.class)
    public static Capability<IModEnergyStorage> PLG_ENERGY_STORAGE = null;


    /**
     * 注册能量
     */
    public static void register() {
        CapabilityManager.INSTANCE.register(IModEnergyStorage.class, new ModEnergyStorage.CapStorage(), () -> new ModEnergyStorage(10000));
    }


}
