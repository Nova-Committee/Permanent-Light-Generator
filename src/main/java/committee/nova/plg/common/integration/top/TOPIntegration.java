package committee.nova.plg.common.integration.top;

import committee.nova.plg.Plg;
import committee.nova.plg.common.tiles.PlgTileEntity;
import committee.nova.plg.utils.FormatUtil;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.BlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.function.Function;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/29 10:40
 * Version: 1.0
 */
public class TOPIntegration implements Function<ITheOneProbe, Void> {
    @Override
    public Void apply(ITheOneProbe iTheOneProbe) {
        iTheOneProbe.registerProvider(new PlgInfo());
        return null;
    }

    public static class PlgInfo implements IProbeInfoProvider{

        @Override
        public String getID() {
            return Plg.MODID;
        }

        @Override
        public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {

            final TileEntity tile = world.getBlockEntity(iProbeHitData.getPos());
            if(tile instanceof PlgTileEntity){
                final PlgTileEntity plg = (PlgTileEntity) tile;
                iProbeInfo.text(I18n.get("message.plg.ctrl_info", FormatUtil.compact(plg.getEnergy())));

                if(playerEntity.isCrouching()) {
                    iProbeInfo.text(I18n.get("message.plg.ctrl_info", plg.getEnergy()));
                    final int generation = (int) plg.getPlgType().getPower().getProduction();
                    final int transfer = generation * 2;
                    final int capacity = generation * 1000;
                    iProbeInfo.text(I18n.get("message.plg.shift_info", generation, transfer, capacity));

                }
            }
        }
    }


}
