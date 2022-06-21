package committee.nova.plg.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class AbstractPacket {
    public AbstractPacket() {
    }

    public abstract void toBytes(FriendlyByteBuf buffer);

    public abstract void handle(Supplier<NetworkEvent.Context> ctx);
}
