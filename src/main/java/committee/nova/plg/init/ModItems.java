package committee.nova.plg.init;

import committee.nova.plg.Plg;
import committee.nova.plg.utils.RegistryUtil;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 18:14
 * Version: 1.0
 */
@Mod.EventBusSubscriber(modid = Plg.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll();

        RegistryUtil.registerEnumBlockItems(registry, ModBlocks.PLG);
    }

}
