package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;

public class GuiButtonChosenWords extends GuiButton {


	String Text;
	
	public GuiButtonChosenWords(int ID, int x, int y, String Text) {
		super(ID, x, y, Text.length() * 5 + 10, 10, "");
		
		this.Text = Text;
		
		
		
	}
	
	
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my) {

		boolean hovering = (mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height);
		FontRenderer fontrenderer = minecraft.standardGalacticFontRenderer; 
		int k1 = 6839882;
		
		if(hovering)
		{
			fontrenderer = minecraft.fontRenderer;
			fontrenderer.drawSplitString(this.Text, this.xPosition + 5, this.yPosition, 70, Color.GRAY.getRGB());

		}else
		{
			fontrenderer.drawSplitString(this.Text, this.xPosition + 5, this.yPosition, 70, (k1 & 16711422) >> 1);
		}
		
	}
	
	
	
}