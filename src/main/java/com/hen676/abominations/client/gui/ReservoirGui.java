package com.hen676.abominations.client.gui;

import com.hen676.abominations.inventory.container.ReservoirContainer;
import com.hen676.abominations.util.LocationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class ReservoirGui extends ContainerScreen<ReservoirContainer> {
    //TODO: Add Capability
    private ResourceLocation GUI = LocationUtil.location("textures/gui/reservoir2.png");

    public ReservoirGui(ReservoirContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 176;
        this.ySize = 207;
        this.titleX = 7;
        this.titleY = 7;
        this.playerInventoryTitleX = 7;
        this.playerInventoryTitleY = 114;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(@Nonnull MatrixStack matrixStack, int x, int y) {
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        //drawString(matrixStack, Minecraft.getInstance().fontRenderer,"Reservoir",0xffffff,6,6);
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(matrixStack, i, j, 0.0F, 0.0F, this.xSize, this.ySize, 256, 256);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
}
