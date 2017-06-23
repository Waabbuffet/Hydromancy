package com.waabbuffet.hydromancy.util;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;

public abstract class Cathegory {
	protected static List<LexiconEntry> cathegory = new ArrayList<LexiconEntry>();
	
	public static List<LexiconEntry> getCathegory()
	{
		return cathegory;
	}
}
