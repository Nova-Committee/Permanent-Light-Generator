package committee.nova.plg.init.events;

import committee.nova.plg.Plg;
import committee.nova.plg.client.render.PlgTileRender;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.init.ModTileEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 20:59
 * Version: 1.0
 */
@Mod.EventBusSubscriber(modid = Plg.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {

        for(PlgType plgType: PlgType.values()){
            //ClientRegistry.bindTileEntityRenderer(ModTileEntities.PLG_TILE.get(plgType).get(), PlgTileRender::new);
        }
        //tile


    }
}
