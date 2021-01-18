package com.hen676.abominations.init;

import com.hen676.abominations.Abominations;
import com.hen676.abominations.block.ForgeBlock;
import com.hen676.abominations.block.IncineratorBlock;
import com.hen676.abominations.block.ReservoirBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Abominations.MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /**
     * Blocks
     */
    public static final RegistryObject<Block> FORGE = BLOCKS.register("forge", ForgeBlock::new);
    public static final RegistryObject<Block> RESERVOIR = BLOCKS.register("reservoir", ReservoirBlock::new);
    public static final RegistryObject<Block> INCINERATOR = BLOCKS.register("incinerator", IncineratorBlock::new);
}
