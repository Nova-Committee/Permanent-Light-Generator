package committee.nova.plg.common.net.packets;

import committee.nova.plg.common.net.IPacket;
import committee.nova.plg.common.tiles.PlgTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/29 9:37
 * Version: 1.0
 */
public class UpdatePlgPacket extends IPacket {



    private final BlockPos pos;
    private final int currentEnergy;
    private final int currentProduction;

    public UpdatePlgPacket(PacketBuffer buf)
    {
        pos = buf.readBlockPos();
        currentEnergy = buf.readInt();
        currentProduction = buf.readInt();
    }

    public UpdatePlgPacket(BlockPos pos, int currentEnergy, int currentProduction)
    {
        this.pos = pos;
        this.currentEnergy = currentEnergy;
        this.currentProduction = currentProduction;
    }

    @Override
    public void toBytes(PacketBuffer buffer) {
        buffer.writeBlockPos(pos);
        buffer.writeInt(currentEnergy);
        buffer.writeInt(currentProduction);
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            World world = Minecraft.getInstance().level;
            if(world.isLoaded(pos))
            {
                TileEntity te = world.getBlockEntity(pos);
                if(te instanceof PlgTileEntity)
                {
                    PlgTileEntity solar = (PlgTileEntity) te;
                    solar.energyClient = currentEnergy;
                    solar.energyProductionClient = currentProduction;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
