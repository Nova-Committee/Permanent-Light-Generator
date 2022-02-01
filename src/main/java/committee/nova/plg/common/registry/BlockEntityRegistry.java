package committee.nova.plg.common.registry;

import com.mojang.datafixers.DSL;
import committee.nova.plg.common.blockentity.impl.*;
import committee.nova.plg.common.blockentity.init.PLGBlockEntityInit;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.util.registry.Registry;
import team.reborn.energy.api.EnergyStorage;

import static committee.nova.plg.common.block.init.PLGBlockInit.*;

public class BlockEntityRegistry {
    public static void register() {
        PLGBlockEntityInit.PL2_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl2_be", FabricBlockEntityTypeBuilder.create(PL2BlockEntity::new, PL2).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL2_BLOCK_ENTITY);

        PLGBlockEntityInit.PL8_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl8_be", FabricBlockEntityTypeBuilder.create(PL8BlockEntity::new, PL8).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL8_BLOCK_ENTITY);

        PLGBlockEntityInit.PL32_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl32_be", FabricBlockEntityTypeBuilder.create(PL32BlockEntity::new, PL32).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL32_BLOCK_ENTITY);

        PLGBlockEntityInit.PL128_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl128_be", FabricBlockEntityTypeBuilder.create(PL128BlockEntity::new, PL128).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL128_BLOCK_ENTITY);

        PLGBlockEntityInit.PL512_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl512_be", FabricBlockEntityTypeBuilder.create(PL512BlockEntity::new, PL512).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL512_BLOCK_ENTITY);

        PLGBlockEntityInit.PL2048_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl2048_be", FabricBlockEntityTypeBuilder.create(PL2048BlockEntity::new, PL2048).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL2048_BLOCK_ENTITY);

        PLGBlockEntityInit.PL8192_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl8192_be", FabricBlockEntityTypeBuilder.create(PL8192BlockEntity::new, PL8192).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL8192_BLOCK_ENTITY);

        PLGBlockEntityInit.PL32768_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl32768_be", FabricBlockEntityTypeBuilder.create(PL32768BlockEntity::new, PL32768).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL32768_BLOCK_ENTITY);

        PLGBlockEntityInit.PL131072_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl131072_be", FabricBlockEntityTypeBuilder.create(PL131072BlockEntity::new, PL131072).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL131072_BLOCK_ENTITY);

        PLGBlockEntityInit.PL532480_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "plg:pl532480_be", FabricBlockEntityTypeBuilder.create(PL532480BlockEntity::new, PL532480).build(DSL.remainderType()));
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, PLGBlockEntityInit.PL532480_BLOCK_ENTITY);
    }
}
