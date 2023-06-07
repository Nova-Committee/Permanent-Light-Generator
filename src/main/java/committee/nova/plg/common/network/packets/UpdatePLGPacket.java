package committee.nova.plg.common.network.packets;

import committee.nova.plg.client.network.ClientNetworkUtils;
import committee.nova.plg.common.network.AbstractPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdatePLGPacket extends AbstractPacket {
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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClientNetworkUtils.updatePlg(pos, currentEnergy, currentProduction)));
        ctx.get().setPacketHandled(true);
    }
}
