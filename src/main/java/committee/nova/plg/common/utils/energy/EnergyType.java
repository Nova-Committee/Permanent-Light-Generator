package committee.nova.plg.common.utils.energy;

import committee.nova.plg.common.utils.FormatUtil;

import javax.annotation.Nonnull;
import java.text.NumberFormat;

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

    public String getName() {
        return name;
    }

    public String getStorageSuffix() {
        return storage;
    }

    public String getUsageSuffix() {
        return usage;
    }


}
