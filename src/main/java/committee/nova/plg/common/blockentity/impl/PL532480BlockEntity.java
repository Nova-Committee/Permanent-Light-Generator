package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL532480_BLOCK_ENTITY;

public class PL532480BlockEntity extends AbstractPLBlockEntity {
    public PL532480BlockEntity(BlockPos pos, BlockState state) {
        super(PL532480_BLOCK_ENTITY, pos, state, 532480L, 1064960L, 532480000L);
    }
}
