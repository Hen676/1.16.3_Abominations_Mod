package com.hen676.abominations.crafting;

import net.minecraft.item.crafting.IRecipeType;

public class RecipeTypeEntity implements IRecipeType<EntityRecipe> {
    @Override
    public String toString() {
        return "abominations:entity_recipe";
    }
}
