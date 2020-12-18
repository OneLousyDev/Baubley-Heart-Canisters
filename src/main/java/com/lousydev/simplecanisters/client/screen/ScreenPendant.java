package com.lousydev.simplecanisters.client.screen;

import com.lousydev.simplecanisters.container.ContainerPendant;
import com.lousydev.simplecanisters.Reference;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ScreenPendant extends ContainerScreen<ContainerPendant>
{

    private static final ResourceLocation PENDAT_BACKGROUND = new ResourceLocation(Reference.MODID, "textures/gui/heart_pendant.png");

    public ScreenPendant(ContainerPendant inventorySlotsIn, PlayerInventory inventory, ITextComponent title)
    {
        super(inventorySlotsIn, inventory, title);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack stack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.getMinecraft().getTextureManager().bindTexture(PENDAT_BACKGROUND);
        int x = (this.width - this.xSize)/2;
        int y = (this.height - this.ySize)/2;

        this.blit(stack, x, y, 0, 0, this.xSize, this.ySize, 176, 166);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack stack, int mouseX, int mouseY)
    {
        //this.fontRenderer.drawString(I18n.format("container.bhc.heart_pendant"), 3, 3, 4210752);
        super.drawGuiContainerForegroundLayer(stack, mouseX, mouseY);
    }
}
