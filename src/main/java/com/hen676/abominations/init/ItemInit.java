package com.hen676.abominations.init;

import com.hen676.abominations.Abominations;
import com.hen676.abominations.item.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Abominations.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /**
     * Items
     */
    // Block Items
    public static final RegistryObject<Item> FORGE_ITEM = ITEMS.register("forge",() -> new BlockItem(BlockInit.FORGE.get(),Abominations.GROUP));
    public static final RegistryObject<Item> RESERVOIR_ITEM = ITEMS.register("reservoir",() -> new BlockItem(BlockInit.RESERVOIR.get(),Abominations.GROUP));
    public static final RegistryObject<Item> INCINERATOR_ITEM = ITEMS.register("incinerator",() -> new BlockItem(BlockInit.INCINERATOR.get(),Abominations.GROUP));
    // Default Items
    public static final RegistryObject<Item> LASER = ITEMS.register("laser",() -> new Item(Abominations.GROUP));
    public static final RegistryObject<Item> ENTITY = ITEMS.register("entity",() -> new EntityItem(Abominations.GROUP));
    // Consumables Items
    public static final RegistryObject<Item> GROWTH_PILL = ITEMS.register("growth_pill",() -> new GrowthPillItem(Abominations.GROUP));
    // Tool Items
    public static final RegistryObject<Item> SCEPTRE = ITEMS.register("sceptre",() -> new SceptreItem(Abominations.GROUP));
    public static final RegistryObject<Item> CODEX = ITEMS.register("codex",() -> new CodexItem(Abominations.GROUP));
    public static final RegistryObject<Item> CAPSULE = ITEMS.register("capsule",() -> new CapsuleItem(Abominations.GROUP));
    // Mob Eggs
    public static final RegistryObject<Item> GEMINI_MOB_EGG = ITEMS.register("gemini_mob_egg", () -> new SpawnEggItem(EntityInit.GEMINI_ENTITY_TYPE, 0xde16a2,0xeef520,Abominations.GROUP));

}
