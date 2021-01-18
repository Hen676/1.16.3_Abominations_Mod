package com.hen676.abominations.client.render.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.model.animation.Animation;

import javax.annotation.Nonnull;

public class EntityItemRenderer extends ItemStackTileEntityRenderer {

    public EntityItemRenderer() { }

    @Override
    public void func_239207_a_(ItemStack stack, @Nonnull ItemCameraTransforms.TransformType p_239207_2_,@Nonnull MatrixStack matrixStack,@Nonnull IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        if(!stack.hasTag())
            renderEntity(EntityType.PIG.getRegistryName().toString(), matrixStack, buffer, combinedLight);
        else if (stack.getTag().contains("entity"))
            renderEntity(stack.getTag().getString("entity"), matrixStack, buffer, combinedLight);
    }

    private void renderEntity(String entityResourceLocation, MatrixStack matrixStack,IRenderTypeBuffer buffer, int combinedLight) {
        final EntityType<?> entityType = EntityType.byKey(entityResourceLocation).orElse(null);
        ClientWorld world = Minecraft.getInstance().world;
        if(entityType == null || world == null) return;
        Entity entity = entityType.create(world);
        if(entity == null) return;
        entity.rotationPitch = 0;
        entity.rotationYaw = 0;
        matrixStack.push();

        float f = 0.53125F;
        final float f1 = Math.max(entity.getWidth(), entity.getHeight());
        if ((double)f1 > 1.0D) f /= f1;
        matrixStack.scale(f, f, f);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(-30.0F));
        matrixStack.rotate(Vector3f.XP.rotationDegrees(-30.0F));
        Minecraft.getInstance().getRenderManager().renderEntityStatic(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, matrixStack, buffer, combinedLight);

        matrixStack.pop();
    }
}
