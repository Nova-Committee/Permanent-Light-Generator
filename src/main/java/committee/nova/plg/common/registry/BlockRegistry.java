package committee.nova.plg.common.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static committee.nova.plg.common.block.init.PLGBlockInit.*;

public class BlockRegistry {
    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl2"), PL2);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl8"), PL8);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl32"), PL32);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl128"), PL128);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl512"), PL512);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl2048"), PL2048);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl8192"), PL8192);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl32768"), PL32768);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl131072"), PL131072);
        Registry.register(Registry.BLOCK, new Identifier("plg", "pl532480"), PL532480);
    }
}
