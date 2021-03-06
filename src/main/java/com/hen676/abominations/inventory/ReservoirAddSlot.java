package com.hen676.abominations.inventory;

import com.hen676.abominations.init.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ReservoirAddSlot extends SlotItemHandler {
    public ReservoirAddSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        if(stack.getItem() != ItemInit.CAPSULE.get()) return false;
        if(stack.hasTag())
            return stack.getTag().contains("entity");
        return false;
    }
}
