package com.waabbuffet.hydromancy.items;

import com.waabbuffet.hydromancy.items.containers.ItemPurifiedBucket;
import com.waabbuffet.hydromancy.items.lexicon.ItemLexicon;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class HydromancyItemsHandler 
{
	public static Item lexicon;

	public static void init()
	{
		lexicon = new ItemLexicon().setUnlocalizedName("lexicon").setTextureName(Reference.MODID + ":ItemLexicon");
	}

	public static void register()
	{
		GameRegistry.registerItem(lexicon, lexicon.getUnlocalizedName().substring(5));
	}
}
