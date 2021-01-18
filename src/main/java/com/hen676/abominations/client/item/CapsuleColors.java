package com.hen676.abominations.client.item;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class CapsuleColors implements IItemColor {
    @Override
    public int getColor(@Nonnull ItemStack stack, int tintIndex) {
        if(tintIndex == 0 || tintIndex > 2) return 0xFFFFFFF;
        if (hasEntity(stack)) {
            String entityId = stack.getTag().getString("entity");
            return getEntityColor(entityId, tintIndex);
        }
        return 0xFFFFFFF;
    }

    private boolean hasEntity(ItemStack stack) {
        return stack.hasTag() && stack.getTag() != null && stack.getTag().contains("entity");
    }

    private int getEntityColor(String entityID, int tintIndex) {
        EntityType<?> entity = EntityType.byKey(entityID).orElse(null);
        if(entity != null) {
            if (SpawnEggItem.getEgg(entity) != null)
                return SpawnEggItem.getEgg(entity).getColor(tintIndex-1);
        }
        return 0xFFFFFF;
    }
}
