package com.waabbuffet.hydromancy.util;

import java.awt.Color;
import java.util.List;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;

public class CathegoryType {
	private List<LexiconEntry> cathegory;
	private String cathegoryName;
	private Color rgb;
	
	public CathegoryType(String cathegoryName, List<LexiconEntry> cathegory, Color color)
	{
		this.cathegory = cathegory;
		this.cathegoryName = cathegoryName;
		rgb = color;
	}
	
	public int getRBG()
	{
		return rgb.getRGB();
	}
	
	public String getName() {
		return cathegoryName;
	}
	
	public List<LexiconEntry> getCathegory() {
		return cathegory;
	}
}
