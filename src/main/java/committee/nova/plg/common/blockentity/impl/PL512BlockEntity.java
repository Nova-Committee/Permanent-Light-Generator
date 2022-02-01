package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL512_BLOCK_ENTITY;

public class PL512BlockEntity extends AbstractPLBlockEntity {
    public PL512BlockEntity(BlockPos pos, BlockState state) {
        super(PL512_BLOCK_ENTITY, pos, state, 512L, 1024L, 512000L);
    }
}
