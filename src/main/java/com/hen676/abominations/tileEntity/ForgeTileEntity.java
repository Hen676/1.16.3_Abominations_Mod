package com.hen676.abominations.tileEntity;

import com.hen676.abominations.init.RecipeInit;
import com.hen676.abominations.init.TileEntityInit;
import com.hen676.abominations.recipe.EntityRecipe;
import com.hen676.abominations.util.RecipeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ForgeTileEntity extends TileEntity implements ITickableTileEntity {

    private List<ResourceLocation> livingEntityNames = new ArrayList<ResourceLocation>();

    public ForgeTileEntity() {
        super(TileEntityInit.FORGE_TILE_ENTITY.get());
    }

    @Override
    public void tick() {
        if(world.isRemote()) return;
        if(world.isBlockPowered(pos)) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            BlockPos A = new BlockPos(x-2, y-1, z-2);
            BlockPos B = new BlockPos(x+2, y+2, z+2);

            // Get Entities
            List<LivingEntity> livingEntities = world.getEntitiesWithinAABB(LivingEntity.class , new AxisAlignedBB(A, B));

            if(!livingEntities.isEmpty()) {
                // Get Registry Names
                for (LivingEntity livingEntity : livingEntities) {
                    livingEntityNames.add(Objects.requireNonNull(livingEntity.getType()).getRegistryName());
                }

                // Sort Entities
                livingEntityNames.sort(new RecipeUtil.ResourceLocationComparator());
                ResourceLocation output = compareEntitiesWithRecipe(livingEntityNames);

                // Spawn Entity if recipe matches
                if(output != null) {
                    EntityType<?> Result = ForgeRegistries.ENTITIES.getValue(output);
                    if (Result != null) {
                        for (LivingEntity livingEntity : livingEntities) {
                            livingEntity.remove();
                        }
                        Result.spawn((ServerWorld) world, null, null, pos.up(), SpawnReason.MOB_SUMMONED, true, true);
                    }
                }
            }
        }
        livingEntityNames.clear();
    }

    private ResourceLocation compareEntitiesWithRecipe(List<ResourceLocation> livingEntityNames) {
        for (final IRecipe<?> recipe : Minecraft.getInstance().world.getRecipeManager().getRecipesForType(RecipeInit.RECIPE_TYPE)) {
            if (recipe instanceof EntityRecipe) {
                final EntityRecipe entityRecipe = (EntityRecipe) recipe;
                if (entityRecipe.matches(livingEntityNames)) {
                    return entityRecipe.getResult();
                }
            }
        }
        return null;
    }
}
