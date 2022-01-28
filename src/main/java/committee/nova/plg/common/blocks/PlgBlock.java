package committee.nova.plg.common.blocks;

import committee.nova.plg.common.tiles.PlgTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 11:32
 * Version: 1.0
 */
public class PlgBlock extends HorizontalBlock {

    private final PlgType type;



    public PlgBlock(PlgType plgType) {
            super(Properties.of(Material.METAL).noOcclusion().strength(3F, 10F).sound(SoundType.METAL));
            this.type = plgType;
    }

    public PlgType getType() {
        return type;
    }

    @Override
    public IFormattableTextComponent getName() {
        return super.getName();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext pContext) {
        return defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, pContext.getHorizontalDirection().getOpposite()).setValue(BlockStateProperties.POWERED, false);
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PlgTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
