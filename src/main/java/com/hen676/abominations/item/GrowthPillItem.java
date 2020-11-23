package com.hen676.abominations.item;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class GrowthPillItem extends Item {
    public GrowthPillItem(Properties properties) {
        super(properties
                .maxStackSize(16)
        );
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        boolean toggle = false;
        if(!playerIn.world.isRemote()) {
            if (!target.isChild() || !target.isAlive() || !(target instanceof AgeableEntity)) {
                return ActionResultType.FAIL;
            }
            AgeableEntity ageTarget = (AgeableEntity) target;
            if (!playerIn.abilities.isCreativeMode) {
                stack.shrink(1);
            }
            ageTarget.ageUp(-ageTarget.getGrowingAge() + 1, true);
            playerIn.swingArm(hand);
            toggle = true;
        }
        if(toggle) {
            playerIn.world.addParticle(ParticleTypes.HAPPY_VILLAGER,target.getPosX(),target.getPosY(),target.getPosZ(),0,0,0);
            return ActionResultType.CONSUME;
        }
        return ActionResultType.FAIL;
    }
}
