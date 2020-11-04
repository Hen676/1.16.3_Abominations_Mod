package com.hen676.abominations.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class ForgeRecipeConfig {
    public static ForgeConfigSpec.ConfigValue<List<String>> RECIPES;

    public static List<String> Recipes = new ArrayList<>();

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
        char split = '/';
        Recipes.add("minecraft:giant" + split + "minecraft:zombie" + split + "minecraft:zombie" + split + "minecraft:zombie");
        Recipes.add("minecraft:ravager" + split + "minecraft:llama" + split + "minecraft:pillager" + split + "minecraft:polar_bear" );

        common.comment("Recipe Config");
        RECIPES = common.comment("First Entity is output, any proceding is input").define("RECIPES", Recipes);
    }
}
