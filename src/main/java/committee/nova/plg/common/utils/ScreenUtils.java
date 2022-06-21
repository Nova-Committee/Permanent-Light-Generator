package committee.nova.plg.common.utils;

public class ScreenUtils {


    public static float getRed(int colour) {
        return (float) (colour >> 16 & 255) / 255.0F;
    }

    public static float getGreen(int colour) {
        return (float) (colour >> 8 & 255) / 255.0F;
    }

    public static float getBlue(int colour) {
        return (float) (colour & 255) / 255.0F;
    }
}
