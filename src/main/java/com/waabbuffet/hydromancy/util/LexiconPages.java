package com.waabbuffet.hydromancy.util;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageNormalCraftingRecipe;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageText;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageTextWithPicture;

public class LexiconPages {


	public static class GenerationPages
	{
		public static LexiconEntry PurifierPage = new LexiconEntry("Purifier", "basic_hydromancy", HydromancyBlocksHandler.Block_Purifier,Reference.GenerationCategory_ID,
				new PageText(Reference.LexiconData.Purifier_Page_1_Text), 
				new PageNormalCraftingRecipe(new ItemStack[]{new ItemStack(HydromancyBlocksHandler.Block_Coral), new ItemStack(Items.bucket), new ItemStack(HydromancyBlocksHandler.Block_Coral1), new ItemStack(HydromancyBlocksHandler.Block_Coral2), new ItemStack(Blocks.furnace), new ItemStack(HydromancyBlocksHandler.Block_Coral3), new ItemStack(HydromancyBlocksHandler.Block_Coral4), new ItemStack(Items.bucket) ,new ItemStack(HydromancyBlocksHandler.Block_Coral5)}, new ItemStack(HydromancyBlocksHandler.Block_Purifier), Reference.LexiconData.Purifier_Page_3_Text, -65),
				new PageText(Reference.LexiconData.Purifier_Page_2_Text));
	}

	public static class WorldGenerationPages
	{
		public static LexiconEntry CoralBasePage = new LexiconEntry("Coral", "who_lives_in_a_pineaple_uner_the_sea", HydromancyBlocksHandler.Block_Coral5, Reference.WorldGenerationCategory_ID,
				new PageTextWithPicture(new ResourceLocation(Reference.MODID + ":textures/gui/AllCoralPicture.png"), 0, 120, 110, 100, 110 , 100, Reference.LexiconData.Coral_Page_1_Text, 0),
				new PageTextWithPicture(new ItemStack(HydromancyBlocksHandler.Block_Coral), 45, 13, Reference.LexiconData.Orange_Coral_Page_1_Text, 20), 
				new PageTextWithPicture(new ResourceLocation(Reference.MODID + ":textures/gui/Test Coral Picture.png"), 0, 10, 110, 100, 110 ,100, Reference.LexiconData.Orange_Coral_Page_2_Text, 110), 
				new PageTextWithPicture(new ItemStack(HydromancyBlocksHandler.Block_Coral2), 45, 83, Reference.LexiconData.Purple_Coral_Page_1_Text, 0), 
				new PageTextWithPicture(new ResourceLocation(Reference.MODID + ":textures/gui/LastCoralPieces.png"), 0, 10, 110, 100, 110 ,100, Reference.LexiconData.Orange_Coral_Page_3_Text, 110), 
				new PageTextWithPicture(new ItemStack(HydromancyBlocksHandler.Block_Coral3), 45, 13, Reference.LexiconData.Blue_Coral_Page_1_Text, 20));			
	}

	public static class TransportationPages
	{
		public static LexiconEntry CoralPumpPage = new LexiconEntry("Coral Pump", "lets_make_use_of_it", HydromancyBlocksHandler.Coral_Pump, Reference.TransportationCategory_ID,
				new PageText(Reference.LexiconData.CoralPump_Page_1_Text),  
				new PageNormalCraftingRecipe(new ItemStack[]{new ItemStack(HydromancyBlocksHandler.Block_Coral6), new ItemStack(Items.compass), new ItemStack(HydromancyBlocksHandler.Block_Coral6), new ItemStack(Items.gold_ingot), new ItemStack(Blocks.piston), new ItemStack(Items.gold_ingot), new ItemStack(HydromancyBlocksHandler.Block_Coral6), new ItemStack(Items.redstone) ,new ItemStack(HydromancyBlocksHandler.Block_Coral6)}, new ItemStack(HydromancyBlocksHandler.Coral_Pump),  Reference.LexiconData.CoralPump_Page_2_Text, -65));
	}

}
