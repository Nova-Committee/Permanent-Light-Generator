package committee.nova.plg.init;

import committee.nova.plg.Plg;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

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

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.DIRT);
    }


}