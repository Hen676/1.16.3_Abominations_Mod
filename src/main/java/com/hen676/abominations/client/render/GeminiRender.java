package com.hen676.abominations.client.render;

import com.hen676.abominations.client.models.entities.GeminiModel;
import com.hen676.abominations.entity.GeminiEntity;
import com.hen676.abominations.util.LocationUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GeminiRender extends MobRenderer<GeminiEntity, GeminiModel<GeminiEntity>> {
    public GeminiRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GeminiModel<>(1.0f,false), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(GeminiEntity entity) {
        return LocationUtil.location("textures/entity/gemini.png");
    }
}
