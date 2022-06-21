package committee.nova.plg.common.utils.energy;

public class Power {
    public static final Power DefaultPower = new Power(0, 1);

    /**
     * Low Voltage		| 低圧
     */
    public static final int LV = 32;
    /**
     * Medium Voltage	| 中圧
     */
    public static final int MV = 128;
    /**
     * High Voltage	| 高圧
     */
    public static final int HV = 512;
    /**
     * Extreme Voltage	| 超高圧
     */
    public static final int EV = 2048;

    private final int level;
    private final int production;

    public Power(final int level, final int production) {
        this.level = level;
        this.production = production;
    }

    /**
     * 総電力 → 電圧 (IC2 exp) {1=LV, 2=MV, 3=HV, 4=EV, 5=EV}
     */
    public static int getTierFromProduction(final int prod) {
        return (log2(prod) >> 1) + 1;
    }

    private static int log2(int bits) {// returns 0 for bits=0
        int log = 0;
        if ((bits & 0xffff0000) != 0) {
            bits >>>= 16;
            log = 16;
        }
        if (bits >= 256) {
            bits >>>= 8;
            log += 8;
        }
        if (bits >= 16) {
            bits >>>= 4;
            log += 4;
        }
        if (bits >= 4) {
            bits >>>= 2;
            log += 2;
        }
        return log + (bits >>> 1);
    }

    /**
     * 发电等级
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * 最大产出EU / Tick
     */
    public int getProduction() {
        return this.production;
    }

    /**
     * 电压 (IC2 exp) {1=LV, 2=MV, 3=HV, 4=EV, 5=EV}
     */
    public int getTier() {
        return getTierFromProduction(getProduction());
    }
}
