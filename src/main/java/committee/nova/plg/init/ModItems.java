package committee.nova.plg.init;

import committee.nova.plg.Plg;
import committee.nova.plg.common.blocks.PlgType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 18:14
 * Version: 1.0
 */
public class ModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Plg.MODID);


    public static final Map<PlgType, RegistryObject<Item>> PLG_ITEM = new HashMap<>();

    private static final Item.Properties property = new Item.Properties().tab(ModItemGroups.INSTANCE);


    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        final PlgType[] types = PlgType.values();
        for (PlgType plgType : types) {
            PLG_ITEM.put(plgType, ITEMS.register(plgType.getName(), () -> new BlockItem(ModBlocks.PLG_BLOCK.get(plgType).get(), property)));
        }
    }




}
