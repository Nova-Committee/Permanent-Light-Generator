package committee.nova.plg.common.blockentity.impl;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import static committee.nova.plg.common.blockentity.init.PLGBlockEntityInit.PL532480_BLOCK_ENTITY;

public class PL532480BlockEntity extends BlockEntity {
    private final Long generation = 532480L;
    private final Long maxExtract = generation * 2;
    private final Long capacity = generation * 1000;

    public PL532480BlockEntity(BlockPos pos, BlockState state) {
        super(PL532480_BLOCK_ENTITY, pos, state);
    }

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(capacity, 0, maxExtract) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public static void tick(World world, BlockPos pos, BlockState state, PL532480BlockEntity be) {
        assert world != null;
        if (!world.isClient && be.energyStorage.amount <= be.capacity) {
            be.energyStorage.amount += be.generation;
            try (Transaction transaction = Transaction.openOuter()) {
                long amountExtracted = be.energyStorage.extract(be.maxExtract, transaction);
                if (amountExtracted == be.maxExtract) {
                    transaction.commit();
                } else {
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
