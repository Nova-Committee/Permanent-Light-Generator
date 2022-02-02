package committee.nova.plg.common.itemgroup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static committee.nova.plg.common.block.init.PLGBlockInit.PL8;

public class PLGItemGroup {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("plg", "main"),
            () -> new ItemStack(PL8));
}
