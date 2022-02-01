package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL8_BLOCK_ENTITY;

public class PL8BlockEntity extends AbstractPLBlockEntity {
    public PL8BlockEntity(BlockPos pos, BlockState state) {
        super(PL8_BLOCK_ENTITY, pos, state, 8L, 16L, 8000L);
    }
}
