package committee.nova.plg.common.blockEntity.init;

import com.mojang.datafixers.DSL;
import committee.nova.plg.PLG;
import committee.nova.plg.common.block.base.PLGType;
import committee.nova.plg.common.block.init.BlockInit;
import committee.nova.plg.common.blockEntity.base.PLGBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class BlockEntityInit {

    public static final Map<PLGType, RegistryObject<BlockEntityType<PLGBlockEntity>>> PLG_TILE = new HashMap<>();
    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, PLG.MODID);

    public static void init() {
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        final PLGType[] types = PLGType.values();
        for (PLGType plgType : types) {
            PLG_TILE.put(plgType, TILES.register(plgType.getName(), () -> BlockEntityType.Builder.of((BlockPos pos, BlockState state) -> new PLGBlockEntity(plgType, pos, state), BlockInit.PLG_BLOCK.get(plgType).get()).build(DSL.remainderType())));
        }
    }


}
