package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL131072_BLOCK_ENTITY;

public class PL131072BlockEntity extends AbstractPLBlockEntity {
    public PL131072BlockEntity(BlockPos pos, BlockState state) {
        super(PL131072_BLOCK_ENTITY, pos, state, 131072L, 262144L, 131072000L);
    }
}
