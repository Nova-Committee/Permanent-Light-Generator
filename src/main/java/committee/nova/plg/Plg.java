package committee.nova.plg;

import committee.nova.plg.common.net.PacketHandler;
import committee.nova.plg.init.ModBlocks;
import committee.nova.plg.init.ModItems;
import committee.nova.plg.init.ModTileEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Plg.MODID)
public class Plg {

    public static final String MODID = "plg";
    //public static final Logger LOGGER = LogManager.getLogger("PLG");

    public Plg() {
        ModBlocks.init();
        ModItems.init();
        ModTileEntities.init();
        PacketHandler.init();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
