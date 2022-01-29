package committee.nova.plg.common.block.base;

import com.google.common.collect.Maps;
import committee.nova.plg.common.utils.energy.Power;
import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Locale;

public enum PLGType implements StringRepresentable {


    PermanentLight2(0, "pl2", new Power(0, 2)),
    PermanentLight8(1, "pl8", new Power(1, 8)),
    PermanentLight32(2, "pl32", new Power(2, 32)),
    PermanentLight128(3, "pl128", new Power(3, 128)),
    PermanentLight512(4, "pl512", new Power(4, 512)),
    PermanentLight2048(5, "pl2048", new Power(5, 2048)),
    PermanentLight8192(6, "pl8192", new Power(6, 8192)),
    PermanentLight32768(7, "pl32768", new Power(7, 32768)),
    PermanentLight131072(8, "pl131072", new Power(8, 131072)),
    PermanentLight532480(9, "pl532480", new Power(9, 532480)),
    ;

    public static final HashMap<Integer, PLGType> map = Maps.newHashMap();

    static {
        final PLGType[] types = values();
        for (final PLGType ce : types) {
            map.put(ce.getID(), ce);
        }
    }

    private final int id;
    private final String name;
    private final Power power;

    PLGType(final int id, final String name, final Power power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public static PLGType fromID(final int id) {
        final PLGType ce = map.get(id);
        return (ce == null) ? PermanentLight2 : ce;
    }

    @Nonnull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public String getName() {
        return name;
    }

    public Power getPower() {
        return this.power;
    }

    public int getID() {
        return this.id;
    }

}
