package committee.nova.plg.init;

import committee.nova.plg.Plg;
import committee.nova.plg.common.blocks.PlgBlock;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.utils.RegistryUtil;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 11:37
 * Version: 1.0
 */
@Mod.EventBusSubscriber(modid = Plg.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    public static Block[] PLG;



    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        PLG = RegistryUtil.registerEnumBlock(registry, PlgType.values(), it -> it + "plg", PlgBlock::new);

    }









}
