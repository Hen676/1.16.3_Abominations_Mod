package com.hen676.abominations.init;

import com.hen676.abominations.client.gui.ReservoirGui;
import com.hen676.abominations.client.item.CapsuleColors;
import com.hen676.abominations.client.render.GeminiRender;
import com.hen676.abominations.entity.GeminiEntity;
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

        itemColors.register(new CapsuleColors(), RegisterInit.CAPSULE.get());

        RenderingRegistry.registerEntityRenderingHandler(RegisterInit.GEMINI_ENTITY.get(), GeminiRender::new);

        ScreenManager.registerFactory(RegisterInit.RESERVOIR_CONTAINER.get(), ReservoirGui::new);
    }
}
