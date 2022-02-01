package committee.nova.plg.common.blockentity.base;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public abstract class AbstractPLBlockEntity extends BlockEntity {
    private final long generation;
    private long maxExtract;
    private long capacity;
    public SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(capacity, 0, maxExtract) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public AbstractPLBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, long generation, long maxExtract, long capacity) {
        super(type, pos, state);
        this.generation = generation;
        this.maxExtract = maxExtract;
        this.capacity = capacity;
    }

    public static void tick(World world, BlockPos pos, BlockState state, AbstractPLBlockEntity be) {
        if (world == null) {
            return;
        }
        final long capacity = be.capacity;
        if (!world.isClient && be.energyStorage.amount <= capacity) {
            be.energyStorage.amount += be.generation;
            try (final Transaction transaction = Transaction.openOuter()) {
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
