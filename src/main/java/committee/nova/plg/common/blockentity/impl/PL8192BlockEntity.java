package committee.nova.plg.common.blockentity.impl;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL8192_BLOCK_ENTITY;

public class PL8192BlockEntity extends BlockEntity {
    private final Long generation = 8192L;
    private final Long maxExtract = generation * 2;
    private final Long capacity = generation * 1000;

    public PL8192BlockEntity(BlockPos pos, BlockState state) {
        super(PL8192_BLOCK_ENTITY, pos, state);
    }

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(capacity, 0, maxExtract) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public static void tick(World world, BlockPos pos, BlockState state, PL8192BlockEntity be) {
        if (world == null) {
            return;
        }
        if (!world.isClient && be.energyStorage.amount <= be.capacity) {
            be.energyStorage.amount += be.generation;
            try (Transaction transaction = Transaction.openOuter()) {
                final long amountExtracted = be.energyStorage.extract(be.maxExtract, transaction);
                if (amountExtracted == be.maxExtract) {
                    transaction.commit();
                }
            }
            be.markDirty();
        }
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        tag.putLong("amount", energyStorage.amount);
        super.writeNbt(tag);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        energyStorage.amount = tag.getLong("amount");
    }
}
