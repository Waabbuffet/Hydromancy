package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonChooseWord extends GuiButton{
	public boolean hovering;
	public GuiButtonChooseWord(int x, int y, int width, int height, String text) {
		super(215, x, y, width, height, text);
	}

	@Override
	public void drawButton(Minecraft mc, int mx, int my)
	{
		hovering = (mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height);
		
		FontRenderer renderer = mc.fontRenderer;
		renderer.drawString(displayString, xPosition, yPosition, 0xFFFFFF);
		//super.drawButton(mc, mx, my);
	}
}
