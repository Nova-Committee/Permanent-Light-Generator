package committee.nova.plg.init.events;

import committee.nova.plg.Plg;
import committee.nova.plg.init.packs.AddPackFindersEvent;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.data.PackMetadataSection;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import net.minecraftforge.fml.packs.ModFileResourcePack;

import java.nio.file.Path;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/2/6 9:26
 * Version: 1.0
 */
@Mod.EventBusSubscriber(modid = Plg.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEventHandler {

    @SubscribeEvent
    public static void onAddPackFindersEvent(AddPackFindersEvent event) {
//        if (event.getPackType() == ResourcePackType.CLIENT_RESOURCES) {
            final Path resourcePath = ModList.get().getModFileById(Plg.MODID).getFile().findResource("enthusiast_art");
            final ModFileInfo modFileInfo = ModList.get().getModFileById(Plg.MODID);
            final ModFileResourcePack pack = new ModFileResourcePack(modFileInfo.getFile());
            final PackMetadataSection metadataSection = new PackMetadataSection(new TranslationTextComponent("pack.plg.enthusiast.desc"), 8);
            event.addRepositorySource((packConsumer, packConstructor) ->
                    packConsumer.accept(packConstructor.create(
                            "builtin/visible_ray_generator_legacy_art", false,
                            () -> pack,  pack, metadataSection, ResourcePackInfo.Priority.TOP, IPackNameDecorator.BUILT_IN)));
//        }
    }
}
