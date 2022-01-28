package committee.nova.plg.common.blocks;

import com.google.common.collect.Maps;
import net.minecraft.util.IStringSerializable;

import java.util.HashMap;
import java.util.Locale;


/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:21
 * Version: 1.0
 */
public enum PlgType implements IStringSerializable {


    PermanentLight2(0, "permanentlight2", new Power(0, 2)),
    PermanentLight8(1, "permanentlight8", new Power(1, 8)),
    PermanentLight32(2, "permanentlight32", new Power(2, 32)),
    PermanentLight128(3, "permanentlight128", new Power(3, 128)),
    PermanentLight512(4, "permanentlight512", new Power(4, 512)),
    PermanentLight2048(5, "permanentlight2048", new Power(5, 2048)),
    PermanentLight8192(6, "permanentlight8192", new Power(6, 8192)),
    PermanentLight32768(7, "permanentlight32768", new Power(7, 32768)),
    PermanentLight131072(8, "permanentlight131072", new Power(8, 131072)),
    PermanentLight532480(9, "permanentlight532480", new Power(9, 532480)),
    ;

    private final int id;
    private final String name;
    private final Power power;

    private PlgType(final int id, final String name, final Power power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

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


    public static final HashMap<Integer, PlgType> map = Maps.newHashMap();

    static {
        for (final PlgType ce : values()) {
            map.put(ce.getID(), ce);

        }
    }

    public int getID() {
        return this.id;
    }


    public static PlgType fromID(final int id) {
        PlgType ce = map.get(id);
        if (ce==null)
            ce = PermanentLight2;
        return ce;
    }






}
