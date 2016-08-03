package com.waabbuffet.hydromancy.client.gui.lexicon.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.LexiconPageBase;

public class LexiconEntry {

	
	//A lexicon entry will have a Name, a Sequence of types (meaning all pages will have a variable type), and an Item Icon
	
	public LexiconPageBase[] PageSequence;
	public ResourceLocation IconLocation;
	public String EntryName;
	
	/**
	 * 
	 * @param entryName
	 * @param pageSequence
	 * @param icon
	 */
	public LexiconEntry(String entryName, ResourceLocation iconlocation, LexiconPageBase...lexiconPageBases) {
		// TODO Auto-generated constructor stub
		
		EntryName = entryName;
		IconLocation = iconlocation;
		PageSequence = lexiconPageBases;
		
		
	}
	
}




