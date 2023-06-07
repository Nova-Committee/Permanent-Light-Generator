package committee.nova.plg.client.network;

import committee.nova.plg.common.blockEntity.base.PLGBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ClientNetworkUtils {
    public static Runnable updatePlg(BlockPos pos, int currentEnergy, int currentProduction) {
        return () -> {
            final ClientLevel world = Minecraft.getInstance().level;
            if (world == null) {
                return;
            }
            if (world.isLoaded(pos)) {
                final BlockEntity te = world.getBlockEntity(pos);
                if (te instanceof PLGBlockEntity solar) {
                    solar.energyClient = currentEnergy;
                    solar.energyProductionClient = currentProduction;
                }
            }
        };
    }
}
