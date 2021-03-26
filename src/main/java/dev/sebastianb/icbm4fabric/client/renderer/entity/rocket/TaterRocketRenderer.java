package dev.sebastianb.icbm4fabric.client.renderer.entity.rocket;

import dev.sebastianb.icbm4fabric.Constants;
import dev.sebastianb.icbm4fabric.api.missile.LaunchStage;
import dev.sebastianb.icbm4fabric.client.model.entity.rocket.TaterRocketModel;
import dev.sebastianb.icbm4fabric.entity.rocket.TaterRocketEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;

public class TaterRocketRenderer extends MobEntityRenderer<TaterRocketEntity, TaterRocketModel> {


    public TaterRocketRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new TaterRocketModel(), 0.0f);
    }

    @Override
    public Identifier getTexture(TaterRocketEntity entity) {
        return new Identifier(Constants.MOD_ID, "textures/entity/missile/tater_missile.png");
    }

//    @Override
//    protected void setupTransforms(TaterRocketEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta) {
//        super.setupTransforms(entity, matrices, animationProgress, bodyYaw, tickDelta);
//
//        matrices.push();
//
//        float normalYaw = (float) Math.toRadians(entity.yaw);
//        float normalPitch = (float) Math.toRadians(MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch));
//
//        matrices.multiply(Vector3f.POSITIVE_Z.getRadialQuaternion(90));
//        matrices.multiply(Vector3f.POSITIVE_Y.getRadialQuaternion(90));
//
//        matrices.pop();
//
//    }


    // pain
    @Override
    public void render(TaterRocketEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light) {

        float normalYaw = (float) Math.toRadians(entity.yaw);
        float normalPitch = (float) Math.toRadians(MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch));

        matrices.multiply(Vector3f.POSITIVE_Y.getRadialQuaternion(normalYaw));
        matrices.multiply(Vector3f.POSITIVE_X.getRadialQuaternion(normalPitch));

        super.render(entity, tickDelta, yaw, matrices, vertexConsumerProvider, light);

    }
}