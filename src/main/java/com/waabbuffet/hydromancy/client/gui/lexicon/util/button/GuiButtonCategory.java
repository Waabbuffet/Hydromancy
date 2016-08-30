package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
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
		drawTexturedModalRect(xPosition - 5, yPosition - 6, zLevel, 0, 0, 32, 32,  1F / 32F,  1F / 32F);
	}

	public static void drawTexturedModalRect(int par1, int par2, float z, int par3, int par4, int par5, int par6, float f, float f1) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(par1 + 0, par2 + par6, z, (par3 + 0) * f, (par4 + par6) * f1);
		tessellator.addVertexWithUV(par1 + par5, par2 + par6, z, (par3 + par5) * f, (par4 + par6) * f1);
		tessellator.addVertexWithUV(par1 + par5, par2 + 0, z, (par3 + par5) * f, (par4 + 0) * f1);
		tessellator.addVertexWithUV(par1 + 0, par2 + 0, z, (par3 + 0) * f, (par4 + 0) * f1);
		tessellator.draw();
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
