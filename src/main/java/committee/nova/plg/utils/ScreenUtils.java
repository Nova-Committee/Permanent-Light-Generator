package committee.nova.plg.utils;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 20:57
 * Version: 1.0
 */
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
