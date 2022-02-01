package committee.nova.plg.common.block.impl;

import committee.nova.plg.common.blockentity.impl.PL32BlockEntity;
import committee.nova.plg.common.blockentity.init.PLGBlockEntityInit;
import committee.nova.plg.common.utils.FormattingUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class PL32Block extends BlockWithEntity {
    public PL32Block() {
        super(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        FormattingUtils.addInfo("pl32_1", tooltip, true);
        //FormattingUtils.addInfo("pl32_2", tooltip, false);
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PL32BlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, PLGBlockEntityInit.PL32_BLOCK_ENTITY, PL32BlockEntity::tick);
    }
}
