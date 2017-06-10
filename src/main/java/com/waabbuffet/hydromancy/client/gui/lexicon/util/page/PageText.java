package com.waabbuffet.hydromancy.client.gui.lexicon.util.page;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.google.common.base.Joiner;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;


public class PageText extends LexiconPageBase implements ILexiconPage{	
	private int pages;
	
	public PageText(String unlocalizedText) {
		this.unLocalizedText = unlocalizedText;
	}

	@net.minecraftforge.fml.relauncher.SideOnly(net.minecraftforge.fml.relauncher.Side.CLIENT)
	public void renderScreen(GuiLexicon gui) {
		int width = 115;
		int guiX = (gui.width - gui.xSize) / 2;
		int guiY = (gui.height - gui.ySize) / 2;
		
		
		int x = guiX + 15;
		int y = guiY;

		if(pages%2 == 1){
			renderText(x + gui.xSize/2, y, width, gui.height, 10, this.unLocalizedText);
		}else{
			renderText(x - gui.xSize/2, y, width, gui.height, 10, this.unLocalizedText);
		}
	}

	@Override
	public void setPage(int page) {
		this.pages = page;
		
	}
}
