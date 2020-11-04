package com.hen676.abominations;

import com.hen676.abominations.config.MainConfig;
import com.hen676.abominations.entity.GeminiEntity;
import com.hen676.abominations.init.ClientInit;
import com.hen676.abominations.init.RegisterInit;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("abominations")
public class Abominations {
    public static String MOD_ID = "abominations";
    public static String MOD_NAME = "Abominations";

    private static final AbominationsGroup ABOMI_GROUP = new AbominationsGroup("abominations");
    public static final Item.Properties GROUP = new Item.Properties().group(ABOMI_GROUP);


    public Abominations() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MainConfig.COMMON_CONFIG,"abomi-config-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, MainConfig.CLIENT_CONFIG,"abomi-config-client.toml");

        MainConfig.loadConfig(MainConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("abomi-config-common.toml").toString());
        MainConfig.loadConfig(MainConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("abomi-config-client.toml").toString());

        // Registry Initiation
        RegisterInit.init();

        // Setup Bus
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        modBus.addListener(this::setup);
        modBus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Setup Init
        GlobalEntityTypeAttributes.put(RegisterInit.GEMINI_ENTITY.get(), GeminiEntity.setCustomAttributes().create());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Client Init
        ClientInit.init();
    }

}