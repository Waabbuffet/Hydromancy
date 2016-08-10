package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.client.events.ClientTickEventHandler;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiButtonEntry extends GuiButton {

	public LexiconEntry Entry;
	public GuiLexicon Lexicon;
	
	public GuiButtonEntry(int ID, int x, int y, LexiconEntry entry, GuiLexicon gui) {
		super(ID, x, y, 125, 10, "-" + entry.EntryName);
		
		this.Entry = entry;
		this.Lexicon = gui;
		
		
	}
	
	
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my) {

		boolean hovering = (mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height);
		
		if(hovering)
		{
			drawCenteredString(minecraft.fontRenderer, "- "+this.Entry.EntryName, this.xPosition + 50, this.yPosition, Color.CYAN.getRGB());
			
		}else
		{
			drawCenteredString(minecraft.fontRenderer, "- "+this.Entry.EntryName, this.xPosition + 50, this.yPosition, Color.WHITE.getRGB());
		}
		
		
		Lexicon.drawItemStack(new ItemStack(Entry.IconLocation), this.xPosition + 5, this.yPosition - 3, "");
	
	}
	
	
	
}
