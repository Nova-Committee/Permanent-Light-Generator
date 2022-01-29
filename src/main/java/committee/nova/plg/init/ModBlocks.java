package committee.nova.plg.init;

import committee.nova.plg.Plg;
import committee.nova.plg.common.blocks.PlgBlock;
import committee.nova.plg.common.blocks.PlgType;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 11:37
 * Version: 1.0
 */
public class ModBlocks {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Plg.MODID);


    public static final Map<PlgType, RegistryObject<PlgBlock>> PLG_BLOCK = new HashMap<>();



    public static void init(){
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());

        for(PlgType plgType : PlgType.values()){
            PLG_BLOCK.put(plgType, BLOCKS.register(plgType.getName(), () -> new PlgBlock(plgType)));

        }
    }







}
