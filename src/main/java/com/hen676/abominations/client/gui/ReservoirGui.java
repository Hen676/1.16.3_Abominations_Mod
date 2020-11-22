package com.hen676.abominations.client.gui;

import com.hen676.abominations.inventory.ReservoirContainer;
import com.hen676.abominations.util.LocationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ReservoirGui extends ContainerScreen<ReservoirContainer> {



    //TODO: Add scroll bar
    //TODO: Add Capability
    private ResourceLocation GUI = LocationUtil.location("textures/gui/reservoir.png");

    public ReservoirGui(ReservoirContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 276;
        this.titleX = 7;
        this.titleY = 7;
        this.playerInventoryTitleX = 107;
        this.playerInventoryTitleY = 68;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        int i = ((this.width - this.xSize) / 2) -50;
        int j = (this.height - this.ySize) / 2;
        //drawString(matrixStack, Minecraft.getInstance().fontRenderer,"Reservoir",0xffffff,6,6);
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(matrixStack, i, j, 0.0F, 0.0F, this.xSize, this.ySize, 512, 256);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
}
