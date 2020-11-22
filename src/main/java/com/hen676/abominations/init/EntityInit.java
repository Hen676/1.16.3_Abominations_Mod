package com.hen676.abominations.init;

import com.hen676.abominations.Abominations;
import com.hen676.abominations.entity.GeminiEntity;
import com.hen676.abominations.util.LocationUtil;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITIES, Abominations.MOD_ID);

    public static EntityType<GeminiEntity> GEMINI_ENTITY_TYPE = EntityType.Builder.create(GeminiEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F).immuneToFire().trackingRange(32).build(LocationUtil.location("gemini").toString());

    public static void init() {
        ENTITY_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /**
     * Entities
     */
    public static final RegistryObject<EntityType<GeminiEntity>> GEMINI_ENTITY = ENTITY_TYPE.register("gemini",() -> GEMINI_ENTITY_TYPE);

}
