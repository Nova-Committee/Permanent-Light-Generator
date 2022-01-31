package committee.nova.plg.init.events;

import committee.nova.plg.Plg;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.init.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 20:59
 * Version: 1.0
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Plg.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        final PlgType[] types = PlgType.values();
        for (PlgType plgType : types) {
            //ClientRegistry.bindTileEntityRenderer(ModTileEntities.PLG_TILE.get(plgType).get(), PlgTileRender::new);
            RenderTypeLookup.setRenderLayer(ModBlocks.PLG_BLOCK.get(plgType).get(), RenderType.cutout());
        }
    }
}
