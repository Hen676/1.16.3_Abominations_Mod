package com.hen676.abominations.item;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class GrowthPillItem extends Item {
    public GrowthPillItem(Properties properties) {
        super(properties
                .maxStackSize(16)
        );
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if (!target.isChild() || !target.isAlive() || !(target instanceof AgeableEntity)) {
            return ActionResultType.FAIL;
        }
        if(!playerIn.world.isRemote()) {
            AgeableEntity ageTarget = (AgeableEntity) target;
            if (!playerIn.abilities.isCreativeMode) {
                stack.shrink(1);
            }
            ageTarget.ageUp(-ageTarget.getGrowingAge() + 1, true);
            playerIn.swingArm(hand);
        } else {
            playerIn.world.addParticle(ParticleTypes.HAPPY_VILLAGER,target.getPosXRandom(1D),target.getPosYRandom() + 0.5D,target.getPosZRandom(1D),0,0,0);
            playerIn.world.addParticle(ParticleTypes.HAPPY_VILLAGER,target.getPosXRandom(1D),target.getPosYRandom() + 0.5D,target.getPosZRandom(1D),0,0,0);
            playerIn.world.addParticle(ParticleTypes.HAPPY_VILLAGER,target.getPosXRandom(1D),target.getPosYRandom() + 0.5D,target.getPosZRandom(1D),0,0,0);
            playerIn.world.addParticle(ParticleTypes.HAPPY_VILLAGER,target.getPosXRandom(1D),target.getPosYRandom() + 0.5D,target.getPosZRandom(1D),0,0,0);
            playerIn.world.playSound(playerIn,target.getPosX(),target.getPosY(),target.getPosZ(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.NEUTRAL,0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        }
        return ActionResultType.CONSUME;
    }
}
