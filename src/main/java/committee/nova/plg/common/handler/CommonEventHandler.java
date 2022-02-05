package committee.nova.plg.common.handler;

import committee.nova.plg.PLG;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.resource.PathResourcePack;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid = PLG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventHandler {
    @SubscribeEvent
    public static void onAddPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            final Path resourcePath = ModList.get().getModFileById(PLG.MODID).getFile().findResource("packs/enthusiast_art");
            final PathResourcePack pack = new PathResourcePack(ModList.get().getModFileById(PLG.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath);
            final PackMetadataSection metadataSection = new PackMetadataSection(new TranslatableComponent("pack.plg.enthusiast.desc"), 8);
            event.addRepositorySource((packConsumer, packConstructor) ->
                    packConsumer.accept(packConstructor.create(
                            "builtin/visible_ray_generator_legacy_art", new TextComponent("Enthusiast's Art."), false,
                            () -> pack, metadataSection, Pack.Position.TOP, PackSource.BUILT_IN, false)));
        }
    }
}
