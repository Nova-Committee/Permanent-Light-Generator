package committee.nova.plg.common.utils;

import committee.nova.plg.common.block.base.PLGType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PLGUtil {

    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static <T> T get(@Nonnull LazyOptional<T> lazyOptional) {
        return lazyOptional.orElse(null);
    }

    public static float computeSunIntensity(Level world, BlockPos pos, PLGType type) {
        if (!world.canSeeSkyFromBelowWater(pos)) {
            return 0;
        }
        final float angle = world.getSunAngle(1.0f);
        final float celestialAngleRadians = (angle > Math.PI) ? (2 * 3.141592f - angle) : angle;
        final float rawIntensity1 = 1.5F * Mth.cos(celestialAngleRadians / 1.2F);
        final float rawIntensity2 = (rawIntensity1 < 0) ? 0 : ((rawIntensity1 > 1) ? 1 : rawIntensity1);
        final float rawIntensity3 = (rawIntensity2 > 0 && type == PLGType.PermanentLight2) ? 1 : rawIntensity2;
        return rawIntensity3 * ((world.isRaining()) ? 0.4F : 1.0F) * ((world.isThundering()) ? 0.2F : 1.0F);
    }
}
