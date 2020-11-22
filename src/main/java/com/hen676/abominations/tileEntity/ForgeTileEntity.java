package com.hen676.abominations.tileEntity;

import com.hen676.abominations.config.ForgeRecipeConfig;
import com.hen676.abominations.init.TileEntityInit;
import com.hen676.abominations.util.LoggerUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ForgeTileEntity extends TileEntity implements ITickableTileEntity {

    private List<String> recipes = ForgeRecipeConfig.Recipes;

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

            List<LivingEntity> livingEntities = world.getEntitiesWithinAABB(LivingEntity.class , new AxisAlignedBB(A, B));

            if(!livingEntities.isEmpty()) {
                List<String> livingEntityNames = new ArrayList<String>();

                for (LivingEntity livingEntity : livingEntities) {
                    livingEntityNames.add(Objects.requireNonNull(livingEntity.getType().getRegistryName()).toString());
                }

                livingEntityNames.sort(null);

                String output = compareEntitiesWithRecipe(livingEntityNames);

                if(output != null) {
                    EntityType<?> Result = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(output));
                    if (Result != null) {
                        for (LivingEntity livingEntity : livingEntities) {
                            livingEntity.remove();
                        }
                        Result.spawn((ServerWorld) world, null, null, pos.up(), SpawnReason.MOB_SUMMONED, true, true);
                    }
                }
            }
        }
    }

    private String compareEntitiesWithRecipe(List<String> livingEntityNames) {
        for (String recipe : recipes) {
            String[] ingredients = recipe.split("/");
            LoggerUtil.LOGGER.info(Arrays.equals(Arrays.copyOfRange(ingredients, 1, ingredients.length - 1),livingEntityNames.toArray()));

            if (Arrays.equals(Arrays.copyOfRange(ingredients, 1, ingredients.length),livingEntityNames.toArray())) {
                return ingredients[0];
            }
        }
        return null;
    }
}
