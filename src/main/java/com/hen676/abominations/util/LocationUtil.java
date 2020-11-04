package com.hen676.abominations.util;

import com.hen676.abominations.Abominations;
import net.minecraft.util.ResourceLocation;

public class LocationUtil {
    public static ResourceLocation location(String name)
    {
        return new ResourceLocation(Abominations.MOD_ID, name);
    }
}

