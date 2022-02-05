package committee.nova.plg.common.block.init;

import committee.nova.plg.common.block.impl.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;

public class PLGBlockInit {
    public static final PL2Block PL2 = new PL2Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL8Block PL8 = new PL8Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL32Block PL32 = new PL32Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL128Block PL128 = new PL128Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL512Block PL512 = new PL512Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL2048Block PL2048 = new PL2048Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL8192Block PL8192 = new PL8192Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL32768Block PL32768 = new PL32768Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL131072Block PL131072 = new PL131072Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
    public static final PL532480Block PL532480 = new PL532480Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());
}
