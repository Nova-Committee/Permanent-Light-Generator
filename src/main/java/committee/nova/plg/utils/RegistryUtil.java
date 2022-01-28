package committee.nova.plg.utils;

import committee.nova.plg.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:20
 * Version: 1.0
 */
public class RegistryUtil {

    public static <T extends Enum<T> & IStringSerializable> Block[] registerEnumBlock(IForgeRegistry<Block> registry, T[] types, Function<String, String> nameFactory, Function<T, Block> factory) {
        Block[] blocks = new Block[types.length];
        for (T type : types) {
            blocks[type.ordinal()] = factory.apply(type).setRegistryName(nameFactory.apply(type.getSerializedName()));
        }
        registry.registerAll(blocks);
        return blocks;
    }

    public static void registerEnumBlockItems(IForgeRegistry<Item> registry, Block[] blocks) {
        for (Block block : blocks) {
            registry.register(blockItem(block));
        }
    }

    public static Item blockItem(Block block) {
        return blockItem(block, new Item.Properties().tab(ModItemGroups.INSTANCE));
    }

    private static Item blockItem(Block block, Item.Properties properties) {
        return new BlockItem(block, properties).setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }



    @SuppressWarnings("unchecked")
    public static <T extends TileEntity> TileEntityType<T> build(Supplier<T> factory, String registryName, Block... block) {
        //noinspection ConstantConditions
        return (TileEntityType<T>) TileEntityType.Builder.of(factory, block).build(null).setRegistryName(registryName);
    }
}
