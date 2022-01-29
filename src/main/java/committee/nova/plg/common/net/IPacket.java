package committee.nova.plg.common.net;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class IPacket {


    public IPacket() {}

    public abstract void toBytes(PacketBuffer buffer);

    public abstract void handle(Supplier<NetworkEvent.Context> ctx);
}
