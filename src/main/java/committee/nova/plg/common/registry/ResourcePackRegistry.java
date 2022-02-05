package committee.nova.plg.common.registry;

import committee.nova.plg.PLG;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class ResourcePackRegistry {
    public static void register() {
        ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(PLG.MODID, "enthusiast_art"), FabricLoader.getInstance().getModContainer(PLG.MODID).orElseThrow(), ResourcePackActivationType.NORMAL);
    }
}
