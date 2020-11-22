package com.hen676.abominations;

import com.hen676.abominations.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class AbominationsGroup extends ItemGroup {

    public AbominationsGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemInit.SCEPTRE.get());
    }
}

