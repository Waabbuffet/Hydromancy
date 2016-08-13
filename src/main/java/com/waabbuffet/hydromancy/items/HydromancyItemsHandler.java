package com.waabbuffet.hydromancy.items;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.items.containers.ItemCanteen;
import com.waabbuffet.hydromancy.items.containers.ItemPurifiedBucket;
import com.waabbuffet.hydromancy.items.lexicon.ItemDecipheringStone;
import com.waabbuffet.hydromancy.items.lexicon.ItemLexicon;
import com.waabbuffet.hydromancy.items.lexicon.ItemLexiconLostPage;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class HydromancyItemsHandler 
{
	public static Item lexicon;
	public static Item purified_bucket, canteen, deciphering_Stone, Lost_Page;

	public static void init()
	{

		lexicon = new ItemLexicon().setUnlocalizedName("lexicon").setTextureName(Reference.MODID + ":ItemLexicon");
		purified_bucket = new ItemPurifiedBucket(HydromancyBlocksHandler.Block_Purified_Water).setUnlocalizedName("purified_bucket").setTextureName(Reference.MODID + ":bucket_purified_water");
		canteen = new ItemCanteen().setUnlocalizedName("canteen").setTextureName(Reference.MODID + ":canteen_1");
		deciphering_Stone =  new ItemDecipheringStone().setUnlocalizedName("decipheringStone").setTextureName(Reference.MODID + ":Deciphering Stone");
		Lost_Page =  new ItemLexiconLostPage().setUnlocalizedName("Lost_Page").setTextureName(Reference.MODID + ":LostPage");
	}

	public static void register()
	{
		GameRegistry.registerItem(lexicon, lexicon.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(purified_bucket, purified_bucket.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(canteen, canteen.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(deciphering_Stone, deciphering_Stone.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(Lost_Page, Lost_Page.getUnlocalizedName().substring(5));
		
		
	}
}
