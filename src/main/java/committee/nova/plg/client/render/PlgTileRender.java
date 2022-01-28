package committee.nova.plg.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import committee.nova.plg.common.tiles.PlgTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;

import javax.annotation.Nonnull;

/**
 * Description:
 * Author: cnlimiter
 * Date: 2022/1/28 20:51
 * Version: 1.0
 */
public class PlgTileRender extends TileEntityRenderer<PlgTileEntity> {

    private static final float START_X = 2.0f / 16.0f;
    private static final float START_Y = 2.0f / 16.0f;
    private static final float OFFSET_Z = 1.0f / 16.0f;
    private static final float WIDTH = 12.0f / 16.0f;
    private static final float HEIGHT = 13.0f / 16.0f;

    private static final float ALPHA = 150.0f / 255.0f;

    private static final int FULL_LIGHT = 0x00f000f0; // (240, 240)

    public PlgTileRender(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(PlgTileEntity pBlockEntity, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pCombinedLight, int pCombinedOverlay) {
//        int color = pBlockEntity.brColor;
//        float r = ScreenUtils.getRed(color);
//        float g = ScreenUtils.getGreen(color);
//        float b = ScreenUtils.getBlue(color);
//        render(pMatrixStack, pBuffer.getBuffer(FluxStorageRenderType.getType()), r, g, b,
//                pCombinedOverlay, pBlockEntity.getTransferBuffer(), pBlockEntity.getMaxTransferLimit());
    }


    static void render(MatrixStack matrix, IVertexBuilder builder, float r, float g, float b, int overlay, long energy, long capacity) {
        if (energy <= 0 || capacity <= 0) {
            return;
        }
        float fillPercentage = (float) Math.min((double) energy / capacity, 1.0);
        float renderHeight = HEIGHT * fillPercentage;

        renderSide(matrix, builder, Direction.NORTH, START_X, START_Y, OFFSET_Z, WIDTH, renderHeight, r, g, b, overlay, fillPercentage);
        renderSide(matrix, builder, Direction.SOUTH, START_X, START_Y, OFFSET_Z, WIDTH, renderHeight, r, g, b, overlay, fillPercentage);
        renderSide(matrix, builder, Direction.EAST, START_X, START_Y, OFFSET_Z, WIDTH, renderHeight, r, g, b, overlay, fillPercentage);
        renderSide(matrix, builder, Direction.WEST, START_X, START_Y, OFFSET_Z, WIDTH, renderHeight, r, g, b, overlay, fillPercentage);
        if (fillPercentage < 1.0f) {
            renderSide(matrix, builder, Direction.DOWN, 1.0f / 16.0f, 1.0f / 16.0f, OFFSET_Z + HEIGHT - renderHeight,
                    14.0f / 16.0f, 14.0f / 16.0f, r, g, b, overlay, fillPercentage);
        }
    }

    private static void renderSide(@Nonnull MatrixStack matrix, @Nonnull IVertexBuilder builder, @Nonnull Direction dir,
                                   float x, float y, float z, float width, float height,
                                   float r, float g, float b, int overlay, float maxV) {
        matrix.pushPose();
        matrix.translate(0.5, 0.5, 0.5);
        matrix.mulPose(dir.getRotation());
        matrix.mulPose(new Quaternion((float) (Math.PI * -0.5), 0, 0, false));
        matrix.translate(-0.5, -0.5, -0.5);
        Matrix4f matrix4f = matrix.last().pose();
        Matrix3f normal = matrix.last().normal();
        builder.vertex(matrix4f, x, y + height, z).color(r, g, b, PlgTileRender.ALPHA)
                .uv(0, maxV).overlayCoords(overlay).uv2(PlgTileRender.FULL_LIGHT).normal(normal, 0, 0, 0).endVertex();
        builder.vertex(matrix4f, x + width, y + height, z).color(r, g, b, PlgTileRender.ALPHA)
                .uv(1, maxV).overlayCoords(overlay).uv2(PlgTileRender.FULL_LIGHT).normal(normal, 0, 0, 0).endVertex();
        builder.vertex(matrix4f, x + width, y, z).color(r, g, b, PlgTileRender.ALPHA)
                .uv(1, 0).overlayCoords(overlay).uv2(PlgTileRender.FULL_LIGHT).normal(normal, 0, 0, 0).endVertex();
        builder.vertex(matrix4f, x, y, z).color(r, g, b, PlgTileRender.ALPHA)
                .uv(0, 0).overlayCoords(overlay).uv2(PlgTileRender.FULL_LIGHT).normal(normal, 0, 0, 0).endVertex();
        matrix.popPose();
    }


}
