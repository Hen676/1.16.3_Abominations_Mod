package com.hen676.abominations.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class ForgeRecipeConfig {
    public static ForgeConfigSpec.ConfigValue<List<String>> RECIPES;

    public static List<String> Recipes = new ArrayList<>();

    /**
     * Initiate Recipes
     */
    public static void init(ForgeConfigSpec.Builder server) {
        char split = '/';
        // Basic recipes
        Recipes.add("minecraft:giant" + split + "minecraft:zombie" + split + "minecraft:zombie" + split + "minecraft:zombie");
        Recipes.add("minecraft:ravager" + split + "minecraft:llama" + split + "minecraft:pillager" + split + "minecraft:polar_bear" );
        Recipes.add("minecraft:illusion_illager" + split + "minecraft:ravager" + split + "minecraft:pillager" + split + "minecraft:witch" );
        Recipes.add("minecraft:zombie_horse" + split + "minecraft:horse" + split + "minecraft:zombie" );
        Recipes.add("minecraft:skeleton_horse" + split + "minecraft:horse" + split + "minecraft:skeleton" );
        Recipes.add("minecraft:shulker" + split + "minecraft:enderman" + split + "minecraft:skeleton" + split + "minecraft:slime");

        server.comment("Recipe Config");
        RECIPES = server.comment("First Entity is output, any preceding is input").define("RECIPES", Recipes);
    }
}
