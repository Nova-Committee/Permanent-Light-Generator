/*package committee.nova.plg.common.integration.top;

import committee.nova.plg.PLG;
import committee.nova.plg.common.blockEntity.base.PLGBlockEntity;
import committee.nova.plg.common.utils.FormatUtil;
import mcjty.theoneprobe.api.*;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

public class TOPIntegration implements Function<ITheOneProbe, Void> {
    @Override
    public Void apply(ITheOneProbe iTheOneProbe) {
        iTheOneProbe.registerProvider(new PlgInfo());
        return null;
    }

    public static class PlgInfo implements IProbeInfoProvider{

        @Override
        public String getID() {
            return PLG.MODID;
        }

        @Override
        public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, Player playerEntity, Level world, BlockState blockState, IProbeHitData iProbeHitData) {

            BlockEntity tile = world.getBlockEntity(iProbeHitData.getPos());
            if(tile instanceof PLGBlockEntity plg){
                iProbeInfo.text(I18n.get("message.plg.ctrl_info", FormatUtil.compact(plg.getEnergy())));

                if(playerEntity.isCrouching()){
                    iProbeInfo.text(I18n.get("message.plg.ctrl_info", plg.getEnergy()));
                    int generation = plg.getPlgType().getPower().getProduction();
                    int transfer = generation * 2;
                    int capacity = generation * 1000;
                    iProbeInfo.text(I18n.get("message.plg.shift_info", generation, transfer, capacity));

                }
            }
        }
    }


}*/
