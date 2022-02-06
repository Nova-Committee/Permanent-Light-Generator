package committee.nova.plg.init.packs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DownloadingPackFinder;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.client.ClientModLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/2/6 10:21
 * Version: 1.0
 */
@OnlyIn(Dist.CLIENT)
@Mixin(ClientModLoader.class)
public class ClientModLoaderMixin {



    @Inject(method = "begin", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/datafix/codec/DatapackCodec;addModPacks(Ljava/util/List;)V", shift = At.Shift.BEFORE))
    private static void begin(Minecraft minecraft, ResourcePackList defaultResourcePacks, IReloadableResourceManager mcResourceManager, DownloadingPackFinder metadataSerializer, CallbackInfo ci){
        ModLoader.get().postEvent(new AddPackFindersEvent(defaultResourcePacks::addPackFinder));
    }

}
