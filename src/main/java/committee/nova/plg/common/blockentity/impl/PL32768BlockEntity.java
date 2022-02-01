package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL32768_BLOCK_ENTITY;

public class PL32768BlockEntity extends AbstractPLBlockEntity {
    public PL32768BlockEntity(BlockPos pos, BlockState state) {
        super(PL32768_BLOCK_ENTITY, pos, state, 32768L, 65536L, 32768000L);
    }
}
