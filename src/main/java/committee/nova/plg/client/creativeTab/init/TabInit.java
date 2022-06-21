package committee.nova.plg.client.creativeTab.init;

import committee.nova.plg.PLG;
import committee.nova.plg.common.block.base.PLGType;
import committee.nova.plg.common.block.blockItem.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class TabInit extends CreativeModeTab {
    public static final TabInit INSTANCE = new TabInit();


    public TabInit() {
        super(PLG.MODID);
    }

    @Nonnull
    @Override
    public ItemStack makeIcon() {
        return ItemInit.PLG_ITEM.get(PLGType.PermanentLight532480).get().getDefaultInstance();
    }

}