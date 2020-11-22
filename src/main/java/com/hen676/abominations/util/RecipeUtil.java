package com.hen676.abominations.util;

import net.minecraft.util.ResourceLocation;

import java.util.Comparator;

public class RecipeUtil {
    public static class ResourceLocationComparator implements Comparator<ResourceLocation> {
        @Override
        public int compare(ResourceLocation o1, ResourceLocation o2) {
            return o1.compareTo(o2);
        }
    }
}
