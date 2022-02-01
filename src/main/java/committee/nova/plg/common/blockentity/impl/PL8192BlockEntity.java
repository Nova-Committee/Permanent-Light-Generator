package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL8192_BLOCK_ENTITY;

public class PL8192BlockEntity extends AbstractPLBlockEntity {
    public PL8192BlockEntity(BlockPos pos, BlockState state) {
        super(PL8192_BLOCK_ENTITY, pos, state, 8192L, 16384L, 8192000L);
    }
}
