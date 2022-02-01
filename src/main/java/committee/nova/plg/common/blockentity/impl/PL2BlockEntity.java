package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL2_BLOCK_ENTITY;

public class PL2BlockEntity extends AbstractPLBlockEntity {
    public PL2BlockEntity(BlockPos pos, BlockState state) {
        super(PL2_BLOCK_ENTITY, pos, state, 2L, 4L, 2000L);
    }
}
