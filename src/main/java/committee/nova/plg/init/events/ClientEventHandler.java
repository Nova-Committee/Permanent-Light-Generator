package committee.nova.plg.init.events;

import committee.nova.plg.Plg;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

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
        initRenderType();
    }

    @SubscribeEvent
    public static void finish(final FMLConstructModEvent event) {
        initResourcePack();
    }

    public static void initRenderType() {
        final PlgType[] types = PlgType.values();
        for (PlgType plgType : types) {
            //ClientRegistry.bindTileEntityRenderer(ModTileEntities.PLG_TILE.get(plgType).get(), PlgTileRender::new);
            RenderTypeLookup.setRenderLayer(ModBlocks.PLG_BLOCK.get(plgType).get(), RenderType.cutout());
        }
    }

    public static void initResourcePack() {
        try {
            final File resourcePacks = Minecraft.getInstance().getResourcePackDirectory().getCanonicalFile();
            final File textures = new File(resourcePacks + "/VisibleRay Enthusiasts Art/assets/plg/textures/blocks/generator");
            final File models = new File(resourcePacks + "/VisibleRay Enthusiasts Art/assets/plg/models/block");
            final File[] directories = new File[]{textures, models};
            for (final File file : directories) {
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            final String[] numbers = new String[]{"2", "8", "32", "128", "512", "2048", "8192", "32768", "131072", "532480"};
            generateFile("packs/enthusiast_art/pack.mcmeta", "pack.mcmeta", resourcePacks.getAbsolutePath() + "/VisibleRay Enthusiasts Art");
            //generateFile("data/enthusiast/pack.png", "pack.png", resourcePacks.getAbsolutePath() + "/VisibleRay Enthusiasts Art");
            for (final String number : numbers) {
                //models
                generateFile(
                        MessageFormat.format("packs/enthusiast_art/assets/plg/models/block/pl{0}.json", number),
                        MessageFormat.format("pl{0}.json", number),
                        models.getAbsolutePath());
                //textures
                generateFile(
                        MessageFormat.format("packs/enthusiast_art/assets/plg/textures/blocks/generator/cepermanentlight{0}_bottom.png", number),
                        MessageFormat.format("cepermanentlight{0}_bottom.png", number),
                        textures.getAbsolutePath());
                generateFile(
                        MessageFormat.format("packs/enthusiast_art/assets/plg/textures/blocks/generator/cepermanentlight{0}_bottom_s.png", number),
                        MessageFormat.format("cepermanentlight{0}_bottom_s.png", number),
                        textures.getAbsolutePath());
                generateFile(
                        MessageFormat.format("packs/enthusiast_art/assets/plg/textures/blocks/generator/cepermanentlight{0}_top.png", number),
                        MessageFormat.format("cepermanentlight{0}_top.png", number),
                        textures.getAbsolutePath());
                generateFile(
                        MessageFormat.format("packs/enthusiast_art/assets/plg/textures/blocks/generator/cepermanentlight{0}_top_s.png", number),
                        MessageFormat.format("cepermanentlight{0}_top_s.png", number),
                        textures.getAbsolutePath());
                generateFile(
                        MessageFormat.format("packs/enthusiast_art/assets/plg/textures/blocks/generator/cepermanentlight{0}_top_e.png", number),
                        MessageFormat.format("cepermanentlight{0}_top_e.png", number),
                        textures.getAbsolutePath());
                generateFile(
                        MessageFormat.format("packs/enthusiast_art/assets/plg/textures/blocks/generator/cepermanentlight{0}_side.png", number),
                        MessageFormat.format("cepermanentlight{0}_side.png", number),
                        textures.getAbsolutePath());
                generateFile(
                        MessageFormat.format("packs/enthusiast_art/assets/plg/textures/blocks/generator/cepermanentlight{0}_side_s.png", number),
                        MessageFormat.format("cepermanentlight{0}_side_s.png", number),
                        textures.getAbsolutePath());
            }
        } catch (IOException ignore) {
        }
    }

    public static void generateFile(String input, String name, String path) {
        try {
            final File file = new File(path + "/" + name);

            if (!file.exists()) {
                final InputStream inputStream = ClientEventHandler.class.getClassLoader().getResourceAsStream(input);
                final FileOutputStream outputStream = new FileOutputStream(file);
                if (inputStream != null) {
                    int i;
                    while ((i = inputStream.read()) != -1) {
                        outputStream.write(i);
                    }

                    inputStream.close();
                    outputStream.close();
                }
            }
        } catch (IOException ignore) {
        }
    }

}
