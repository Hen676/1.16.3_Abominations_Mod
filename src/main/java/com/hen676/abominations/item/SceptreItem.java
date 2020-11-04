package com.hen676.abominations.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class SceptreItem extends Item{
    public SceptreItem(Properties properties) {
        super(properties
                .maxStackSize(1)
        );
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, PlayerEntity playerIn) {

        super.onCreated(stack, worldIn, playerIn);
    }
}
