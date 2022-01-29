package committee.nova.plg.common.block.init;

import committee.nova.plg.PLG;
import committee.nova.plg.common.block.base.PLGBlock;
import committee.nova.plg.common.block.base.PLGType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class BlockInit {

    public static final Map<PLGType, RegistryObject<PLGBlock>> PLG_BLOCK = new HashMap<>();
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PLG.MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        final PLGType[] types = PLGType.values();
        for (PLGType plgType : types) {
            PLG_BLOCK.put(plgType, BLOCKS.register(plgType.getName(), () -> new PLGBlock(plgType)));
        }
    }


}
