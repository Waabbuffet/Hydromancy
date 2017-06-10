package com.waabbuffet.hydromancy.client.gui.lexicon.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.LexiconPageBase;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageNormalCraftingRecipe;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageText;
import com.waabbuffet.hydromancy.util.EnumCategoryType;

public class LexiconEntry {

	public LexiconPageBase[] PageSequence;
	public ItemStack itemStack;
	public String EntryName;
	public String ResearchName;
	public int CategoryID;
	
	/**
	 * @param entryName
	 * @param pageSequence
	 * @param icon
	 */
	public LexiconEntry(String entryName, String researchName, ItemStack stack, EnumCategoryType CategoryID, LexiconPageBase...lexiconPageBases) {

		EntryName = entryName;
		itemStack = stack;
		PageSequence = lexiconPageBases;
		this.CategoryID = CategoryID.getID();
		ResearchName = researchName;
	}
}




