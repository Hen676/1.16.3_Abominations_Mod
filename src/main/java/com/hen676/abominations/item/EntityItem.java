package com.hen676.abominations.item;

import com.hen676.abominations.client.render.item.EntityItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EntityItem extends Item {
    public EntityItem(Properties properties) {
        super(properties
                .setISTER(() -> EntityItemRenderer::new)
        );
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        if(!stack.hasTag()) return super.getDisplayName(stack);
        if(stack.getTag().contains("entity")) {
            IFormattableTextComponent base = new TranslationTextComponent("item.abominations.entity").appendString(" ");
            return base.append(new TranslationTextComponent("entity." + stack.getTag().getString("entity").replace(":", ".")));
        }
        return super.getDisplayName(stack);
    }
}
