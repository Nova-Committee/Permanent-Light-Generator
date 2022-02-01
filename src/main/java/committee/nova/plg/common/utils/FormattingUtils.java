package committee.nova.plg.common.utils;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

// Hmm, pretty much stolen from Tech Reborn
public class FormattingUtils {
    private static final Formatting instructColour = Formatting.BLUE;
    private static final Formatting infoColour = Formatting.GOLD;

    public static void addInfo(String inKey, List<Text> list, boolean useShift) {
        addInfo(inKey, list, true, useShift);
    }

    public static void addInfo(String inKey, List<Text> list, boolean hidden, boolean useShift) {
        final String key = ("plg.message.info." + inKey);
        if (useShift) {
            if (I18n.hasTranslation(key)) {
                if (!hidden || Screen.hasShiftDown()) {
                    final String info = I18n.translate(key);
                    final String[] infoLines = info.split("\\r?\\n");

                    for (String infoLine : infoLines) {
                        list.add(1, new LiteralText(infoColour + infoLine));
                    }
                } else {
                    list.add(new LiteralText(instructColour + I18n.translate("plg.tooltip.more_info_shift")));
                }
            }
        } else {
            if (I18n.hasTranslation(key)) {
                if (!hidden || Screen.hasControlDown()) {
                    final String info = I18n.translate(key);
                    final String[] infoLines = info.split("\\r?\\n");

                    for (String infoLine : infoLines) {
                        list.add(1, new LiteralText(infoColour + infoLine));
                    }
                } else {
                    list.add(new LiteralText(instructColour + I18n.translate("plg.tooltip.more_info_ctrl")));
                }
            }
        }
    }
}
