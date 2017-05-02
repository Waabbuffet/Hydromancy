package com.waabbuffet.hydromancy.client.gui.lexicon.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.LexiconPageBase;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageNormalCraftingRecipe;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageText;

public class LexiconEntry {

	
	//A lexicon entry will have a Name, a Sequence of types (meaning all pages will have a variable type), and an Item Icon
	
	public LexiconPageBase[] PageSequence;
	public Block IconLocation;
	public String EntryName;
	public String ResearchName;
	public int CategoryID;
	
	/**
	 * 
	 * @param entryName
	 * @param pageSequence
	 * @param icon
	 */
	public LexiconEntry(String entryName, String researchName, Block iconlocation, int CategoryID, LexiconPageBase...lexiconPageBases) {

		EntryName = entryName;
		IconLocation = iconlocation;
		PageSequence = lexiconPageBases;
		this.CategoryID = CategoryID;
		ResearchName = researchName;
	}

	
	
}




