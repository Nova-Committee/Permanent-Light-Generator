package committee.nova.plg.init;

import committee.nova.plg.Plg;
import committee.nova.plg.common.tiles.PlgTileEntity;
import committee.nova.plg.utils.RegistryUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:34
 * Version: 1.0
 */
@Mod.EventBusSubscriber(modid = Plg.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities {

    public static TileEntityType<PlgTileEntity> PLG;


    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        final IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();


        registry.registerAll(

                PLG = RegistryUtil.build(PlgTileEntity::new,"plg_tile", ModBlocks.PLG)
        );



    }



}
