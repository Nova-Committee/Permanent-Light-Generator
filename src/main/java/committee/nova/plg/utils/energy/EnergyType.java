package committee.nova.plg.utils.energy;

import committee.nova.plg.utils.FormatUtil;

import javax.annotation.Nonnull;
import java.text.NumberFormat;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 10:00
 * Version: 1.0
 */
public enum EnergyType {
    FE("Forge Energy", "FE", "FE/t"),
    EU("Energy Units", "EU", "EU/t"),
    ZAP("FTB Energy", "ZAP", "ZAPS/t");

    private final String name;
    private final String storage;
    private final String usage;

    EnergyType(String name, String storage, String usage) {
        this.name = name;
        this.storage = storage;
        this.usage = usage;
    }

    public String getName() {
        return name;
    }

    public String getStorageSuffix() {
        return storage;
    }

    public String getUsageSuffix() {
        return usage;
    }

    @Nonnull
    public static String usage(long in) {
        return NumberFormat.getInstance().format(in) + " " + FE.usage;
    }

    @Nonnull
    public static String usageCompact(long in) {
        return FormatUtil.compact(in, FE.usage);
    }

    @Nonnull
    public static String storage(long in) {
        return NumberFormat.getInstance().format(in) + " " + FE.storage;
    }

    @Nonnull
    public static String storageCompact(long in) {
        return FormatUtil.compact(in, FE.storage);
    }


}
