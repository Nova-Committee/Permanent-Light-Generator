package committee.nova.plg.init;

import committee.nova.plg.Plg;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.common.tiles.PlgTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:34
 * Version: 1.0
 */
public class ModTileEntities {

    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Plg.MODID);

    public static final Map<PlgType, RegistryObject<TileEntityType<PlgTileEntity>>> PLG_TILE = new HashMap<>();


    public static void init(){
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());

        for(PlgType plgType : PlgType.values()){
            PLG_TILE.put(plgType, TILES.register(plgType.getName(), () -> TileEntityType.Builder.of(() -> new PlgTileEntity(plgType), ModBlocks.PLG_BLOCK.get(plgType).get()).build(null)));

        }
    }



}
