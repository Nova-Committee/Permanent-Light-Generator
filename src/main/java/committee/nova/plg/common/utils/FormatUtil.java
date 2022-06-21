package committee.nova.plg.common.utils;

import committee.nova.plg.common.block.base.PLGType;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.regex.Pattern;

public class FormatUtil {

    private static final double[] COMPACT_SCALE = new double[]{0.001D, 0.000_001D, 0.000_000_001D, 0.000_000_000_001D,
            0.000_000_000_000_001D, 0.000_000_000_000_000_001D};
    private static final Pattern COMPILE = Pattern.compile("@", Pattern.LITERAL);

    private FormatUtil() {
    }

    /**
     * 缩写 (像 3.5M)
     *
     * @param in 数值
     * @return 缩写
     */
    public static String compact(long in) {
        if (in < 1000) {
            return Long.toString(in);
        }
        final int level = (int) (Math.log10(in) / 3) - 1;
        final char pre = "kMGTPE".charAt(level);//k- 千，m- 兆。。。
        return String.format("%.1f%c", in * COMPACT_SCALE[level], pre);
    }

    public static String compact(long in, String suffix) {
        if (in < 1000) {
            return in + " " + suffix;
        }
        final int level = (int) (Math.log10(in) / 3) - 1;
        final char pre = "kMGTPE".charAt(level);
        return String.format("%.1f %c%s", in * COMPACT_SCALE[level], pre, suffix);
    }

    public static void showInfoShift(PLGType type, List<Component> tooltip) {
        if (Screen.hasShiftDown()) {
            final int generation = type.getPower().getProduction();
            final int transfer = generation * 2;
            final int capacity = generation * 1000;

            addInformationLocalized(tooltip, "message.plg.shift_info", generation, transfer, capacity);
        } else
            addInformationLocalized(tooltip, "message.plg.hold_shift");
    }

    public static void showInfoCtrl(int energy, List<Component> tooltip) {
        if (Screen.hasControlDown())
            addInformationLocalized(tooltip, "message.plg.ctrl_info", energy);
        else
            addInformationLocalized(tooltip, "message.plg.hold_ctrl");
    }

    private static void addInformationLocalized(List<Component> tooltip, String key, Object... parameters) {
        final String translated = COMPILE.matcher(I18n.get(key, parameters)).replaceAll("\u00a7");
        final String[] formatted = translated.split("\n");
        for (String line : formatted)
            tooltip.add(Component.translatable(line));
    }
}
