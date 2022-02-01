package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL2048_BLOCK_ENTITY;

public class PL2048BlockEntity extends AbstractPLBlockEntity {
    public PL2048BlockEntity(BlockPos pos, BlockState state) {
        super(PL2048_BLOCK_ENTITY, pos, state, 2048L, 4096L, 2048000L);
    }
}
