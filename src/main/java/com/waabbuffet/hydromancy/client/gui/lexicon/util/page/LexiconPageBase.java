package com.waabbuffet.hydromancy.client.gui.lexicon.util.page;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class LexiconPageBase {
	public String unLocalizedText;
	
	public void addIcon(int xCoord, int yCoord, ItemStack icon, GuiLexicon gui)
	{
		
	}
	
	public void addIconWithText(int xCoord, int yCoord, ItemStack icon, String unlocalizedText, GuiLexicon gui)
	{
	//	PageText.renderText(this.IconToDisplay[i].getxCoord(), this.IconToDisplay[i].getxCoord(), 115, gui.height, this.IconToDisplay[i].getUnlocalizedText());
//		gui.drawItemStack(this.IconToDisplay[i].getIcon(), this.IconToDisplay[i].getxCoord(), this.IconToDisplay[i].getxCoord(), "");
	}
	

	@SideOnly(Side.CLIENT)
	public void renderText(int x, int y, int width, int height, int paragraphSize, String unlocalizedText) {
		x += 2;
		y += 10;
		width -= 4;

		FontRenderer font = Minecraft.getMinecraft().fontRenderer;
	//	FontRenderer font = Minecraft.getMinecraft().standardGalacticFontRenderer;
		boolean unicode = font.getUnicodeFlag();
		font.setUnicodeFlag(true);
		String text = StatCollector.translateToLocal(unlocalizedText).replaceAll("&", "\u00a7");
		String[] textEntries = text.split("<br>");

		
		List<List<String>> lines = new ArrayList();

		String controlCodes = "";
		for(String s : textEntries) {
			List<String> words = new ArrayList();
			String lineStr = "";
			String[] tokens = s.split(" ");
			for(String token : tokens) {
				String prev = lineStr;
				
				String spaced = token + " ";
				lineStr += spaced;

				controlCodes = toControlCodes(getControlCodes(prev));
				if(font.getStringWidth(lineStr) > width) {
					lines.add(words);
					lineStr = controlCodes + spaced;
					words = new ArrayList();
				}
				
				words.add(controlCodes + token);
			}

			if(!lineStr.isEmpty())
				lines.add(words);
			lines.add(new ArrayList());
		}

		int i = 0;
		for(List<String> words : lines) {
			words.size();
			int xi = x;
			int spacing = 4;
			int wcount = words.size();
			int compensationSpaces = 0;
			

			for(String s : words) {
				int extra = 0;
				if(compensationSpaces > 0) {
					compensationSpaces--;
					extra++;
				}
				font.drawString(s, xi, y, 0);
				xi += font.getStringWidth(s) + spacing + extra;
			}

			y += words.isEmpty() ? paragraphSize : 10;
			i++;
		}

		font.setUnicodeFlag(unicode);
	}

	public static String getControlCodes(String s) {
		String controls = s.replaceAll("(?<!\u00a7)(.)", "");
		String wiped = controls.replaceAll(".*r", "r");
		return wiped;
	}

	public static String toControlCodes(String s) {
		return s.replaceAll(".", "\u00a7$0");
	}
}
