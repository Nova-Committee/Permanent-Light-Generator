package committee.nova.plg.common.tiles;

import committee.nova.plg.api.energy.IEnergyHandler;
import committee.nova.plg.common.blocks.PlgBlock;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 21:20
 * Version: 1.0
 */
public class BaseTileEntity extends TileEntity implements ITickableTileEntity, IEnergyHandler {

    private boolean changed;
    private int changeStateTicks;
    private LazyOptional<?> thisOptional;
    public boolean active;

    public double energy;
    public double energyCapacity;

    private boolean burnt;
    public UUID placerId = Util.NIL_UUID;
    public String placerName = "";

    public BaseTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
        energy = 0;
        thisOptional = null;
        active = false;
        changeStateTicks = 0;
        burnt = false;

    }

    public void writeData(CompoundNBT tag) {
        tag.putDouble("Energy", energy);



        if (burnt) {
            tag.putBoolean("Burnt", true);
        }

        if (!placerId.equals(Util.NIL_UUID)) {
            tag.putUUID("PlacerId", placerId);
            tag.putString("PlacerName", placerName);
        }
    }

    public void readData(CompoundNBT tag) {
        energy = tag.getDouble("Energy");


        burnt = tag.getBoolean("Burnt");

        if (tag.hasUUID("PlacerId")) {
            placerId = tag.getUUID("PlacerId");
            placerName = tag.getString("PlacerName");
        } else {
            placerId = Util.NIL_UUID;
            placerName = "";
        }
    }

    @Override
    public void load(BlockState state, CompoundNBT tag ) {
        super.load(state, tag);
        readData(tag);
        initProperties();
        upgradesChanged();
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);
        writeData(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        initProperties();
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        return tag;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        initProperties();
    }


    @Override
    public void onLoad() {
        initProperties();

        if (level != null && !level.isClientSide()) {
            upgradesChanged();
        }

        if (level != null && level.isClientSide() && !tickClientSide()) {
            level.tickableBlockEntities.remove(this);
        }

        super.onLoad();
    }

    public boolean tickClientSide() {
        return false;
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();

        if (thisOptional != null) {
            thisOptional.invalidate();
            thisOptional = null;
        }
    }

    public LazyOptional<?> getThisOptional() {
        if (thisOptional == null) {
            thisOptional = LazyOptional.of(() -> this);
        }

        return thisOptional;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ) {
            return getThisOptional().cast();
        }

        return super.getCapability(cap, side);
    }


    protected void handleChanges() {
        if (changeStateTicks > 0) {
            changeStateTicks--;
        }

        if (changeStateTicks <= 0) {


            changeStateTicks = 6;

            if (changed) {
                setChangedNow();
            }
        }
    }

    @Override
    public void tick() {
        handleChanges();
    }

    @Override
    public void setChanged() {
        changed = true;
    }

    public void setChangedNow() {
        changed = false;
        level.blockEntityChanged(worldPosition, this);
    }

    @Override
    public double getEnergyCapacity() {
        return energyCapacity;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public void setEnergyRaw(double energy) {
        this.energy = energy;
    }



    public void initProperties() {

    }

    /**
     * In every instance initProperties() should be called first
     */
    public void upgradesChanged() {
    }

    public void consumeEnergy(double energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

}
