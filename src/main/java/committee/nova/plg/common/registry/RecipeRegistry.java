package committee.nova.plg.common.registry;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import static committee.nova.plg.common.utils.DynamicRecipeUtil.createShapedRecipeJson;

public class RecipeRegistry {
    public static JsonObject PL2_RECIPE = null;
    public static JsonObject PL8_RECIPE = null;
    public static JsonObject PL32_RECIPE = null;
    public static JsonObject PL128_RECIPE = null;
    public static JsonObject PL512_RECIPE = null;
    public static JsonObject PL2048_RECIPE = null;
    public static JsonObject PL8192_RECIPE = null;
    public static JsonObject PL32768_RECIPE = null;
    public static JsonObject PL131072_RECIPE = null;
    public static JsonObject PL532480_RECIPE = null;

    public static void register() {
        if (FabricLoader.getInstance().isModLoaded("techreborn")) {
            PL2_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("techreborn", "quantum_solar_panel"), new Identifier("techreborn","lapotronic_orb")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            " # ",
                            "#|#",
                            " # "
                    ), //The crafting pattern.
                    new Identifier("plg:pl2") //The crafting output
            );
            PL8_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl2"), new Identifier("techreborn","superconductor")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl8") //The crafting output
            );
            PL32_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl8"), new Identifier("techreborn","superconductor")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl32") //The crafting output
            );
            PL128_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl32"), new Identifier("techreborn","superconductor")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl128") //The crafting output
            );
            PL512_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl128"), new Identifier("techreborn","superconductor")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl512") //The crafting output
            );
            PL2048_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl512"), new Identifier("techreborn","superconductor")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl2048") //The crafting output
            );
            PL8192_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl2048"), new Identifier("techreborn","superconductor")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl8192") //The crafting output
            );
            PL32768_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl8192"), new Identifier("techreborn","superconductor")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl32768") //The crafting output
            );
            PL131072_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl32768"), new Identifier("techreborn","superconductor")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl131072") //The crafting output
            );
            PL532480_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl131072"), new Identifier("techreborn","superconductor_cable")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl532480") //The crafting output
            );
        } else {
            PL2_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|',
                            '-',
                            '='
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("minecraft", "dragon_egg"), new Identifier("minecraft","nether_star"), new Identifier("minecraft","amethyst_block"), new Identifier("minecraft","netherite_block")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item", "item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "---",
                            "#|#",
                            "=#="
                    ), //The crafting pattern.
                    new Identifier("plg:pl2") //The crafting output
            );
            PL8_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl2"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl8") //The crafting output
            );
            PL32_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl8"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl32") //The crafting output
            );
            PL128_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl32"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl128") //The crafting output
            );
            PL512_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl128"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl512") //The crafting output
            );
            PL2048_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl512"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl2048") //The crafting output
            );
            PL8192_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl2048"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl8192") //The crafting output
            );
            PL32768_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl8192"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl32768") //The crafting output
            );
            PL131072_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl32768"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl131072") //The crafting output
            );
            PL532480_RECIPE = createShapedRecipeJson(
                    Lists.newArrayList(
                            '#',
                            '|'
                    ), //The keys we are using for the input items/tags.
                    Lists.newArrayList(new Identifier("plg", "pl131072"), new Identifier("minecraft","dragon_egg")), //The items/tags we are using as input.
                    Lists.newArrayList("item", "item"), //Whether the input we provided is a tag or an item.
                    Lists.newArrayList(
                            "|#|",
                            "#|#",
                            "|#|"
                    ), //The crafting pattern.
                    new Identifier("plg:pl532480") //The crafting output
            );
        }
    }
}
