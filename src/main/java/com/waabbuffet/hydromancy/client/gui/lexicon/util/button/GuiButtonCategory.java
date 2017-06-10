package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import java.awt.Color;

import org.lwjgl.opengl.GL11;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiButtonCategory extends GuiButton{


	boolean isHovering;
	ResourceLocation Icon; 

	public GuiButtonCategory(int ID, int x, int y, String IconName) {
		super(ID, x, y, 25, 25, IconName);
		// TODO Auto-generated constructor stub

		Icon = new ResourceLocation(Reference.MODID + ":" + IconName);
	}

	@Override
	public void drawButton(Minecraft minecraft, int mx, int my) {

		this.setHovering(mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height);

		minecraft.getTextureManager().bindTexture(Icon);		
		this.drawModalRectWithCustomSizedTexture(xPosition - 5, yPosition - 6, 0, 0, 32, 32,  32F,  32);
	}

	public boolean isHovering() {
		return isHovering;
	}

	public void setHovering(boolean isHovering) {
		this.isHovering = isHovering;
	}

	public ResourceLocation getIcon() {
		return Icon;
	}

	public void setIcon(ResourceLocation icon) {
		Icon = icon;
	}

}
