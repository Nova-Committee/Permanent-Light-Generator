package committee.nova.plg.init;

import committee.nova.plg.Plg;
import committee.nova.plg.common.blocks.PlgType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:19
 * Version: 1.0
 */
public class ModItemGroups extends ItemGroup {
    public ModItemGroups() {
        super(Plg.MODID);
    }


    public static final ModItemGroups INSTANCE = new ModItemGroups();

    @Nonnull
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ModBlocks.PLG_BLOCK.get(PlgType.PermanentLight532480).get());
    }
}