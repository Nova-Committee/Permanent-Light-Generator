package committee.nova.plg.common.blockentity.impl;

import committee.nova.plg.common.blockentity.base.AbstractPLBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL32_BLOCK_ENTITY;

public class PL32BlockEntity extends AbstractPLBlockEntity {
    public PL32BlockEntity(BlockPos pos, BlockState state) {
        super(PL32_BLOCK_ENTITY, pos, state, 32L, 64L, 32000L);
    }
}
