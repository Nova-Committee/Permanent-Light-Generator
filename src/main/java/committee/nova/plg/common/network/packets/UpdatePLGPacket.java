package committee.nova.plg.common.network.packets;

import committee.nova.plg.common.blockEntity.base.PLGBlockEntity;
import committee.nova.plg.common.network.IPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdatePLGPacket extends IPacket {


    private final BlockPos pos;
    private final int currentEnergy;
    private final int currentProduction;

    public UpdatePLGPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
        currentEnergy = buf.readInt();
        currentProduction = buf.readInt();
    }

    public UpdatePLGPacket(BlockPos pos, int currentEnergy, int currentProduction) {
        this.pos = pos;
        this.currentEnergy = currentEnergy;
        this.currentProduction = currentProduction;
    }

    @Override
    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos);
        buffer.writeInt(currentEnergy);
        buffer.writeInt(currentProduction);
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
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
        });
        ctx.get().setPacketHandled(true);
    }
}
