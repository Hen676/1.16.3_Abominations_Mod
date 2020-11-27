package com.hen676.abominations.init;

import com.hen676.abominations.client.gui.ReservoirGui;
import com.hen676.abominations.client.item.CapsuleColors;
import com.hen676.abominations.client.render.entity.GeminiRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientInit {
    public static void init() {
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        itemColors.register(new CapsuleColors(), ItemInit.CAPSULE.get());

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GEMINI_ENTITY.get(), GeminiRender::new);

        ScreenManager.registerFactory(ContainerInit.RESERVOIR_CONTAINER.get(), ReservoirGui::new);
    }
}
