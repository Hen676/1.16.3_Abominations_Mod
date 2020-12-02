package com.hen676.abominations.item;

import com.hen676.abominations.crafting.EntityRecipe;
import com.hen676.abominations.init.RecipeInit;
import com.hen676.abominations.util.LoggerUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

import java.util.List;

public class SceptreItem extends Item{
    public SceptreItem(Properties properties) {
        super(properties
                .maxStackSize(1)
        );
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote && context.getPlayer() != null) {
            List<EntityRecipe> recipe = context.getWorld().getRecipeManager().getRecipesForType(RecipeInit.ENTITY_RECIPE);
            LoggerUtil.LOGGER.info(recipe);
        }
        return super.onItemUse(context);
    }
}
