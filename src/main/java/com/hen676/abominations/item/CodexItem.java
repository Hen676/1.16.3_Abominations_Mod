package com.hen676.abominations.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CodexItem extends Item {
    public CodexItem(Properties properties) {
        super(properties
                .maxStackSize(1)
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.getEntity().sendMessage(new StringTextComponent("You Opened the Codex"),playerIn.getUniqueID());
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
