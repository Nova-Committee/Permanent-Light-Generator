package committee.nova.plg;

import committee.nova.plg.common.block.blockItem.init.ItemInit;
import committee.nova.plg.common.block.init.BlockInit;
import committee.nova.plg.common.blockEntity.init.BlockEntityInit;
import committee.nova.plg.common.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(PLG.MODID)
public class PLG {
    public static final String MODID = "plg";

    public PLG() {
        BlockInit.init();
        ItemInit.init();
        BlockEntityInit.init();
        PacketHandler.init();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
