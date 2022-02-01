package committee.nova.plg.utils;

import committee.nova.plg.common.blocks.PlgType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 10:27
 * Version: 1.0
 */
public class PlgUtil {

    @SuppressWarnings("ConstantConditions")
    @Nullable
    public static <T> T get(@Nonnull LazyOptional<T> lazyOptional) {
        return lazyOptional.orElse(null);
    }

    public static float computeSunIntensity(World world, BlockPos pos, PlgType type)
    {
        if (!world.canSeeSkyFromBelowWater(pos)) {
            return 0;
        }
        final float angle = world.getSunAngle(1.0f);
        final float celestialAngleRadians = (angle > Math.PI) ? (2 * 3.141592f - angle) : angle;
        final float rawIntensity1 = 1.5F * MathHelper.cos(celestialAngleRadians / 1.2F);
        final float rawIntensity2 = (rawIntensity1 < 0) ? 0 : ((rawIntensity1 > 1) ? 1 : rawIntensity1);
        final float rawIntensity3 = (rawIntensity2 > 0 && type == PlgType.PermanentLight2) ? 1 : rawIntensity2;
        return rawIntensity3 * ((world.isRaining()) ? 0.4F : 1.0F) * ((world.isThundering()) ? 0.2F : 1.0F);
    }
}
