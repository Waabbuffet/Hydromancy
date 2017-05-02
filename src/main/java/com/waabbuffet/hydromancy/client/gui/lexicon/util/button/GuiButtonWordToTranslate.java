package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

public class GuiButtonWordToTranslate extends GuiButton{
	public boolean hovering;
	public GuiButtonWordToTranslate(int x, int y, int width, int height, String text) {
		super(215, x, y, width, height, text);
	}

	@Override
	public void drawButton(Minecraft mc, int mx,int my)
	{
		hovering = (mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height);
		//super.drawButton(mc, mx, my); // only for testing purposes
	}
}
