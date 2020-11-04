package com.hen676.abominations.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.AnimationStateMachine;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;

public class AsmUtil {
    public static IAnimationStateMachine load(ResourceLocation location,ImmutableMap<String, ITimeValue> customParameters) {
        return AnimationStateMachine.load(Minecraft.getInstance().getResourceManager(),location,customParameters);
    }
}
