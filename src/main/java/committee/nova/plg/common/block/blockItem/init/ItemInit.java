package committee.nova.plg.common.block.blockItem.init;

import committee.nova.plg.PLG;
import committee.nova.plg.common.tab.init.TabInit;
import committee.nova.plg.common.block.base.PLGType;
import committee.nova.plg.common.block.init.BlockInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class ItemInit {

    public static final Map<PLGType, RegistryObject<Item>> PLG_ITEM = new HashMap<>();
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PLG.MODID);
    private static final Item.Properties property = new Item.Properties().tab(TabInit.INSTANCE);


    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        final PLGType[] types = PLGType.values();
        for (PLGType plgType : types) {
            PLG_ITEM.put(plgType, ITEMS.register(plgType.getName(), () -> new BlockItem(BlockInit.PLG_BLOCK.get(plgType).get(), property)));
        }
    }


}
