package committee.nova.plg.client.handler;

import committee.nova.plg.PLG;
import committee.nova.plg.common.block.base.PLGType;
import committee.nova.plg.common.block.init.BlockInit;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = PLG.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        final PLGType[] types = PLGType.values();
        for (PLGType plgType : types) {
            //ClientRegistry.bindTileEntityRenderer(ModTileEntities.PLG_TILE.get(plgType).get(), PlgTileRender::new);
            ItemBlockRenderTypes.setRenderLayer(BlockInit.PLG_BLOCK.get(plgType).get(), RenderType.cutout());
        }


    }
}
