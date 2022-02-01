package committee.nova.plg.common.tiles;

import committee.nova.plg.api.energy.ModEnergyStorage;
import committee.nova.plg.common.blocks.PlgType;
import committee.nova.plg.common.net.PacketHandler;
import committee.nova.plg.common.net.packets.UpdatePlgPacket;
import committee.nova.plg.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 12:30
 * Version: 1.0
 */
public class PlgTileEntity extends TileEntity implements ITickableTileEntity {





    /** 数据 */
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    private final int energyGeneration;
    private final int maxEnergyOutput;//生产 输出
    public int maxEnergy;//容量

    private final PlgType plgType;//种类

    public int energyClient, energyProductionClient;//通信同步

    public PlgTileEntity(PlgType type) {
        super(ModTileEntities.PLG_TILE.get(type).get());
        this.plgType = type;
        this.energyGeneration = (int) plgType.getPower().getProduction();
        this.maxEnergyOutput = energyGeneration * 2;
        this.maxEnergy = energyGeneration * 1000;
        this.energyClient = energyProductionClient = -1;
    }

    public PlgType getPlgType() {
        return plgType;
    }

    private int getMaxEnergy()
    {
        return getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
    }

    public int getEnergy()
    {
        return getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    @Nonnull
    private IEnergyStorage createEnergy() {
        return new ModEnergyStorage(maxEnergyOutput, maxEnergy);
    }

    @Override
    public void tick()
    {
        if (level != null && !level.isClientSide) {
            energy.ifPresent(e -> ((ModEnergyStorage) e).generatePower(currentAmountEnergyProduced()));
            sendEnergy();
            if (energyClient != getEnergy() || energyProductionClient != currentAmountEnergyProduced()) {
                int energyProduced = (getEnergy() != getMaxEnergy()) ? currentAmountEnergyProduced() : 0;
                PacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new UpdatePlgPacket(getBlockPos(), getEnergy(), energyProduced));
            }
        }
    }


    private int currentAmountEnergyProduced()
    {
        return (int) (energyGeneration
               // * PlgUtil.computeSunIntensity(level, worldPosition, plgType)//删除环境限制
        );
    }

    private void sendEnergy()
    {
        energy.ifPresent(energy -> {
            final AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());

            for (int i = 0; (i < Direction.values().length) && (capacity.get() > 0); i++) {
                final Direction facing = Direction.values()[i];
                if (facing != Direction.UP) {
                    final TileEntity tileEntity = (level != null) ? level.getBlockEntity(worldPosition.relative(facing)) : null;
                    if (tileEntity != null) {
                        tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).ifPresent(handler -> {
                            if (handler.canReceive()) {
                                final int received = handler.receiveEnergy(Math.min(capacity.get(), maxEnergyOutput), false);
                                capacity.addAndGet(-received);
                                ((ModEnergyStorage) energy).consumePower(received);
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
        if(capability == CapabilityEnergy.ENERGY && facing != Direction.UP) {
            return energy.cast();
        }
        return super.getCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void load(@Nonnull BlockState state, CompoundNBT compound) {
        final CompoundNBT energyTag = compound.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
        super.load(state, compound);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    public CompoundNBT save(CompoundNBT compound)
    {
        energy.ifPresent(h -> {
            CompoundNBT tag = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            compound.put("energy", tag);
        });
        return super.save(compound);
    }



}
