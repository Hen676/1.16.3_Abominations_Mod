package com.hen676.abominations.init;

import com.hen676.abominations.Abominations;
import com.hen676.abominations.recipe.EntityRecipe;
import com.hen676.abominations.recipe.RecipeTypeEntity;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeInit {

    private static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Abominations.MOD_ID);

    public static void init() {
        RECIPE_SERIALIZER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /**
     * Recipes
     */
    public static final RegistryObject<EntityRecipe.Serializer> ENTITY_RECIPE_SERIALIZER = RECIPE_SERIALIZER.register("entity_recipe",EntityRecipe.Serializer::new);

    /**
     * Register Recipe Type
     */
    public static final IRecipeType<EntityRecipe> ENTITY_RECIPE = new RecipeTypeEntity();
}
