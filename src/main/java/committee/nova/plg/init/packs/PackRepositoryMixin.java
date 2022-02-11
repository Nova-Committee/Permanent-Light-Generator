package committee.nova.plg.init.packs;

import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.ResourcePackList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/2/6 10:09
 * Version: 1.0
 */
@Mixin(ResourcePackList.class)
public class PackRepositoryMixin {


    @Shadow @Final private Set<IPackFinder> sources;

    @Inject(method = "<init>([Lnet/minecraft/resources/IPackFinder;)V", at = @At("TAIL"))
    public void inject(IPackFinder[] finders, CallbackInfo ci){
        net.minecraftforge.fml.ModLoader.get().postEvent(new AddPackFindersEvent(sources::add));
    }
}
