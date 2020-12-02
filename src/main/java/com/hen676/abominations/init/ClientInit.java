package com.hen676.abominations.init;

import com.hen676.abominations.client.gui.ReservoirGui;
import com.hen676.abominations.client.item.CapsuleColors;
import com.hen676.abominations.client.render.entity.GeminiRender;
import com.hen676.abominations.item.CapsuleItem;
import com.hen676.abominations.util.LocationUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientInit {
    public static void init() {
        //Capsule colors
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        itemColors.register(new CapsuleColors(), ItemInit.CAPSULE.get());
        //Capsule overrides
        ItemModelsProperties.registerProperty(ItemInit.CAPSULE.get(), new ResourceLocation("full"), CapsuleItem::hasEntity);

        //Entities Renderer
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GEMINI_ENTITY.get(), GeminiRender::new);
        //Containers
        ScreenManager.registerFactory(ContainerInit.RESERVOIR_CONTAINER.get(), ReservoirGui::new);
    }
}
