package com.hen676.abominations.item;

import com.hen676.abominations.recipe.EntityRecipe;
import com.hen676.abominations.recipe.RecipeTypeEntity;
import com.hen676.abominations.util.LoggerUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class SceptreItem extends Item{
    public SceptreItem(Properties properties) {
        super(properties
                .maxStackSize(1)
        );
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote && context.getPlayer() != null) {
            EntityRecipe recipe = context.getWorld().getRecipeManager().getRecipesForType(new RecipeTypeEntity()).get(0);
            LoggerUtil.LOGGER.info(recipe);
        }
        return super.onItemUse(context);
    }
}
