package com.waabbuffet.hydromancy.items;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.items.EntityEggs.ItemSpawnMermaid;
import com.waabbuffet.hydromancy.items.containers.ItemCanteen;
import com.waabbuffet.hydromancy.items.containers.ItemPurifiedBucket;
import com.waabbuffet.hydromancy.items.lexicon.ItemDecipheringStone;
import com.waabbuffet.hydromancy.items.lexicon.ItemLexicon;
import com.waabbuffet.hydromancy.items.lexicon.ItemLexiconLostPage;
import com.waabbuffet.hydromancy.items.lexicon.ItemTranslatedPage;
import com.waabbuffet.hydromancy.items.spells.ItemMagicSpellCaster;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class HydromancyItemsHandler 
{
	public static Item lexicon, spawnMermaid;
	public static Item purified_bucket, canteen, decipheringStone, lostFragment, translatedPage;
	public static Item magic_spell_caster;
	

	public static void init()
	{
		spawnMermaid = new ItemSpawnMermaid().setUnlocalizedName("spawnMermaid").setTextureName(Reference.MODID + "");
		lexicon = new ItemLexicon().setUnlocalizedName("lexicon").setTextureName(Reference.MODID + ":ItemLexicon");
		purified_bucket = new ItemPurifiedBucket(HydromancyBlocksHandler.Block_Purified_Water).setUnlocalizedName("purified_bucket").setTextureName(Reference.MODID + ":bucket_purified_water");
		canteen = new ItemCanteen().setUnlocalizedName("canteen").setTextureName(Reference.MODID + ":canteen_1");
		decipheringStone =  new ItemDecipheringStone().setUnlocalizedName("decipheringStone").setTextureName(Reference.MODID + ":Deciphering Stone");
		lostFragment =  new ItemLexiconLostPage().setUnlocalizedName("lostFragment").setTextureName(Reference.MODID + ":LostPage");
		translatedPage = new ItemTranslatedPage().setUnlocalizedName("translatedPage").setTextureName(Reference.MODID + ":TranslatedPage");
		
		magic_spell_caster = new ItemMagicSpellCaster().setUnlocalizedName("magic_spell_caster").setTextureName(Reference.MODID + ":LostPage");;
	}

	public static void register()
	{
		GameRegistry.registerItem(spawnMermaid, spawnMermaid.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(lexicon, lexicon.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(purified_bucket, purified_bucket.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(canteen, canteen.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(decipheringStone, decipheringStone.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(lostFragment, lostFragment.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(translatedPage, translatedPage.getUnlocalizedName().substring(5));
		
		GameRegistry.registerItem(magic_spell_caster, magic_spell_caster.getUnlocalizedName().substring(5));
		
		
		
		
	}
}
