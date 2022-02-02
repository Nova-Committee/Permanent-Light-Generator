package committee.nova.plg;

import committee.nova.plg.common.registry.BlockEntityRegistry;
import committee.nova.plg.common.registry.BlockRegistry;
import committee.nova.plg.common.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PLG implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("Permanent Light Generator");

	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
		BlockEntityRegistry.register();
		LOGGER.info("Initialized.");
	}
}
