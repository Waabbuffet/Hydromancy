package com.waabbuffet.hydromancy.items;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.items.containers.ItemCanteen;
import com.waabbuffet.hydromancy.items.containers.ItemPurifiedBucket;
import com.waabbuffet.hydromancy.items.lexicon.ItemLexicon;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class HydromancyItemsHandler 
{
	public static Item lexicon;
	public static Item purified_bucket, canteen;

	public static void init()
	{

		

		lexicon = new ItemLexicon().setUnlocalizedName("lexicon").setTextureName(Reference.MODID + ":ItemLexicon");
		purified_bucket = new ItemPurifiedBucket(HydromancyBlocksHandler.Block_Purified_Water).setUnlocalizedName("purified_bucket").setTextureName(Reference.MODID + ":bucket_purified_water");
		canteen = new ItemCanteen().setUnlocalizedName("canteen").setTextureName(Reference.MODID + ":ItemCanteen").setTextureName(Reference.MODID + ":canteen_1");
		
	}

	public static void register()
	{
		GameRegistry.registerItem(lexicon, lexicon.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(purified_bucket, purified_bucket.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(canteen, canteen.getUnlocalizedName().substring(5));
		
	}
}
