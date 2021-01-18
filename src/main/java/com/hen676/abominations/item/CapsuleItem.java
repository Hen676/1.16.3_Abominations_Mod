package com.hen676.abominations.item;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;

public class CapsuleItem extends Item {
    public CapsuleItem(Properties properties) {
        super(properties
                .maxStackSize(1)
        );
    }

    @Override
    @Nonnull
    public ActionResultType itemInteractionForEntity(@Nonnull ItemStack stack,@Nonnull  PlayerEntity playerIn, LivingEntity target,@Nonnull  Hand hand) {
        if(target.getEntityWorld().isRemote) return ActionResultType.FAIL;
        if(target instanceof PlayerEntity || !target.isNonBoss() || !target.isAlive()) return ActionResultType.FAIL;
        if(!hasEntity(stack)) {
            String entityID = EntityType.getKey(target.getType()).toString();
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("entity", entityID);
            target.writeWithoutTypeId(nbt);
            stack.setTag(nbt);
            playerIn.swingArm(hand);
            playerIn.setHeldItem(hand, stack);

            target.remove(true);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext context) {
        ItemStack stack = context.getItem();
        BlockPos pos = context.getPos();
        if(context.getWorld().isRemote) return ActionResultType.FAIL;
        if(hasEntity(stack)) {
            String entityId = stack.getTag().getString("entity");
            EntityType<?> entitytype = EntityType.byKey(entityId).orElse(null);
            if(entitytype != null)
            {
                Entity entity = entitytype.create(context.getWorld());
                entity.read(stack.getTag());

                entity.setPosition(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
                stack.setTag(new CompoundNBT());
                context.getWorld().addEntity(entity);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    public static boolean hasEntity(ItemStack stack) {
        if(!stack.isEmpty() && stack.hasTag())
            return stack.getTag().contains("entity");
        return false;
    }

    public static float hasEntity(ItemStack stack, ClientWorld clientWorld, LivingEntity livingEntity) {
        if(hasEntity(stack))
            return 1F;
        return 0F;
    }
}
