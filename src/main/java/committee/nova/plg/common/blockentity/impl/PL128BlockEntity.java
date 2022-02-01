package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL128_BLOCK_ENTITY;

public class PL128BlockEntity extends AbstractPLBlockEntity {
    public PL128BlockEntity(BlockPos pos, BlockState state) {
        super(PL128_BLOCK_ENTITY, pos, state, 128L, 256L, 128000L);
    }
}
