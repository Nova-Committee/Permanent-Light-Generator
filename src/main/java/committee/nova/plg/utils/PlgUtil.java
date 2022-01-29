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
        float sunIntensity = 0;

        if(world.canSeeSkyFromBelowWater(pos))
        {
            float multiplicator = 1.5f;
            float displacement = 1.2f;
            // Celestial angle == 0 at zenith.
            float celestialAngleRadians = world.getSunAngle(1.0f);
            if(celestialAngleRadians > Math.PI)
            {
                celestialAngleRadians = (2 * 3.141592f - celestialAngleRadians);
            }

            sunIntensity = multiplicator * MathHelper.cos(celestialAngleRadians / displacement);
            sunIntensity = Math.max(0, sunIntensity);
            sunIntensity = Math.min(1, sunIntensity);

            if(sunIntensity > 0)
            {
                if(type == PlgType.PermanentLight2)
                    sunIntensity = 1;

                if(world.isRaining())
                    sunIntensity *= 0.4;

                if(world.isThundering())
                    sunIntensity *= 0.2;
            }
        }

        return sunIntensity;
    }
}
