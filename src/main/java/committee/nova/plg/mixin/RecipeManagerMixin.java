package committee.nova.plg.mixin;

import com.google.gson.JsonElement;
import committee.nova.plg.PLG;
import committee.nova.plg.common.registry.RecipeRegistry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        if (RecipeRegistry.PL2_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl2"), RecipeRegistry.PL2_RECIPE);
        }
        if (RecipeRegistry.PL8_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl8"), RecipeRegistry.PL8_RECIPE);
        }
        if (RecipeRegistry.PL32_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl32"), RecipeRegistry.PL32_RECIPE);
        }
        if (RecipeRegistry.PL128_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl128"), RecipeRegistry.PL128_RECIPE);
        }
        if (RecipeRegistry.PL512_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl512"), RecipeRegistry.PL512_RECIPE);
        }
        if (RecipeRegistry.PL2048_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl2048"), RecipeRegistry.PL2048_RECIPE);
        }
        if (RecipeRegistry.PL8192_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl8192"), RecipeRegistry.PL8192_RECIPE);
        }
        if (RecipeRegistry.PL32768_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl32768"), RecipeRegistry.PL32768_RECIPE);
        }
        if (RecipeRegistry.PL131072_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl131072"), RecipeRegistry.PL131072_RECIPE);
        }
        if (RecipeRegistry.PL532480_RECIPE != null) {
            map.put(new Identifier(PLG.MODID, "pl532480"), RecipeRegistry.PL532480_RECIPE);
        }
    }
}
