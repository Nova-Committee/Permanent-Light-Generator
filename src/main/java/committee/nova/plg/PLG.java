package committee.nova.plg;

import committee.nova.plg.common.registry.*;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PLG implements ModInitializer {
    public static final String MODID = "plg";
    public static final Logger LOGGER = LogManager.getLogger("Permanent Light Generator");

    @Override
    public void onInitialize() {
        BlockRegistry.register();
        ItemRegistry.register();
        BlockEntityRegistry.register();
        RecipeRegistry.register();
        ResourcePackRegistry.register();

        LOGGER.info("Initialized.");
    }
}
