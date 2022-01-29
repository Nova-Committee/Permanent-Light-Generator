package committee.nova.plg.common.net;

import committee.nova.plg.Plg;
import committee.nova.plg.common.net.packets.UpdatePlgPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/29 9:17
 * Version: 1.0
 */
public class PacketHandler {

    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void init() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Plg.MODID, "main_networking"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        INSTANCE.registerMessage(nextID(),
                UpdatePlgPacket.class,
                UpdatePlgPacket::toBytes,
                UpdatePlgPacket::new,
                UpdatePlgPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT)
        );
    }
}
