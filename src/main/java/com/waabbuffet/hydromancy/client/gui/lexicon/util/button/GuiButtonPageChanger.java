package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

public class GuiButtonPageChanger extends GuiButton{

	public final ResourceLocation pageChanger = new ResourceLocation(Reference.MODID + ":textures/gui/lexiconGUI.png");
	
	GuiLexicon lexicon;
	boolean Front;
	
	public GuiButtonPageChanger(int ID, int x, int y, boolean Front,GuiLexicon gui)
	{
		super(ID, x, y, 24, 12, "");
		
		this.Front = Front;
		this.lexicon = gui;
	}
	
	
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my) {
	
		boolean hovered = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height;
		
		GL11.glColor4f(1f, 1f, 1f, 1f);
		minecraft.getTextureManager().bindTexture(pageChanger);
		drawTexturedModalRect(xPosition, yPosition, 58, this.Front ? 215: 230, 24, 11); //82 226
		
	}
}
