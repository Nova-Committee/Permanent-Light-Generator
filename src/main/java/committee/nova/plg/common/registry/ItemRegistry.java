package committee.nova.plg.common.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static committee.nova.plg.common.block.init.PLGBlockInit.*;
import static committee.nova.plg.common.itemgroup.PLGItemGroup.ITEM_GROUP;

public class ItemRegistry {
    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("plg", "pl2"), new BlockItem(PL2, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl8"), new BlockItem(PL8, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl32"), new BlockItem(PL32, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl128"), new BlockItem(PL128, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl512"), new BlockItem(PL512, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl2048"), new BlockItem(PL2048, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl8192"), new BlockItem(PL8192, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl32768"), new BlockItem(PL32768, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl131072"), new BlockItem(PL131072, new FabricItemSettings().group(ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("plg", "pl532480"), new BlockItem(PL532480, new FabricItemSettings().group(ITEM_GROUP)));
    }
}