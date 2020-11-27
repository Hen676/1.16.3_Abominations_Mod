package com.hen676.abominations.item;

import com.hen676.abominations.client.render.item.EntityItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EntityItem extends Item {
    public EntityItem(Properties properties) {
        super(properties
                .setISTER(() -> EntityItemRenderer::new)
        );
    }
}
