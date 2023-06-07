package committee.nova.plg.common.blockEntity.base;

import committee.nova.plg.api.energy.PLGEnergyStorage;
import committee.nova.plg.common.block.base.PLGType;
import committee.nova.plg.common.blockEntity.init.BlockEntityInit;
import committee.nova.plg.common.network.PacketHandler;
import committee.nova.plg.common.network.packets.UpdatePLGPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

public class PLGBlockEntity extends BlockEntity {
    private final int energyGeneration;
    private final int maxEnergyOutput;//生产 输出
    private final PLGType plgType;//种类
    public final int maxEnergy;//容量
    /**
     * 数据
     */
    private final LazyOptional<PLGEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    public int energyClient, energyProductionClient;//通信同步

    public PLGBlockEntity(PLGType type, BlockPos pos, BlockState state) {
        super(BlockEntityInit.PLG_TILE.get(type).get(), pos, state);
        this.plgType = type;
        this.energyGeneration = plgType.getPower().getProduction();
        this.maxEnergyOutput = energyGeneration * 2;
        this.maxEnergy = energyGeneration * 1000;
        this.energyClient = energyProductionClient = -1;
    }

    public PLGType getPlgType() {
        return plgType;
    }

    private int getMaxEnergy() {
        return getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
    }

    public int getEnergy() {
        return getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    @Nonnull
    private PLGEnergyStorage createEnergy() {
        return new PLGEnergyStorage(maxEnergyOutput, maxEnergy);
    }

    public void tickServer(BlockState state) {
        if (level != null && !level.isClientSide) {
            energy.ifPresent(e -> e.generatePower(currentAmountEnergyProduced()));
            sendEnergy();
            if (energyClient != getEnergy() || energyProductionClient != currentAmountEnergyProduced()) {
                int energyProduced = (getEnergy() != getMaxEnergy()) ? currentAmountEnergyProduced() : 0;
                PacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new UpdatePLGPacket(getBlockPos(), getEnergy(), energyProduced));
            }
        }
    }


    private int currentAmountEnergyProduced() {
        // * PlgUtil.computeSunIntensity(level, worldPosition, plgType)//删除环境限制
        return energyGeneration;
    }

    private void sendEnergy() {
        if (level == null) {
            return;
        }
        energy.ifPresent(energy -> {
            final AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            final int len = Direction.values().length;
            for (int i = 0; (i < len) && (capacity.get() > 0); i++) {
                final Direction facing = Direction.values()[i];
                if (facing != Direction.UP) {
                    final BlockEntity tileEntity = level.getBlockEntity(worldPosition.relative(facing));
                    if (tileEntity != null) {
                        tileEntity.getCapability(ForgeCapabilities.ENERGY, facing.getOpposite()).ifPresent(handler -> {
                            if (handler.canReceive()) {
                                final int received = handler.receiveEnergy(Math.min(capacity.get(), maxEnergyOutput), false);
                                capacity.addAndGet(-received);
                                energy.consumePower(received);
                            }
                        });
                    }
                }
            }
        });
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction facing) {
        if (capability == ForgeCapabilities.ENERGY && facing != Direction.UP) {
            return energy.cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void load(CompoundTag compound) {
        final CompoundTag energyTag = compound.getCompound("energy");
        energy.ifPresent(h -> h.deserializeNBT(energyTag));
        super.load(compound);
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag compound) {
        energy.ifPresent(h -> {
            final CompoundTag tag = h.serializeNBT();
            compound.put("energy", tag);
        });
        super.saveAdditional(compound);
    }


}
