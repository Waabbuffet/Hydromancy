package com.waabbuffet.hydromancy.lexicon;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.LexiconPageBase;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageNormalCraftingRecipe;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageTextWithPicture;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.util.Reference;

public class LexiconPages 
{
	public static class GenerationPages
	{
		/*
		public static LexiconEntry PurifierPage = new LexiconEntry("Purifier", "basic_hydromancy", HydromancyBlocksHandler.Block_Purifier,Reference.GenerationCategory_ID,
				new PageText(Reference.LexiconData.Purifier_Page_1_Text), 
				new PageNormalCraftingRecipe(new ItemStack[]{new ItemStack(HydromancyBlocksHandler.Block_Coral), new ItemStack(Items.bucket), new ItemStack(HydromancyBlocksHandler.Block_Coral1), new ItemStack(HydromancyBlocksHandler.Block_Coral2), new ItemStack(Blocks.furnace), new ItemStack(HydromancyBlocksHandler.Block_Coral3), new ItemStack(HydromancyBlocksHandler.Block_Coral4), new ItemStack(Items.bucket) ,new ItemStack(HydromancyBlocksHandler.Block_Coral5)}, new ItemStack(HydromancyBlocksHandler.Block_Purifier), Reference.LexiconData.Purifier_Page_3_Text, -65),
				new PageText(Reference.LexiconData.Purifier_Page_2_Text));
				*/
	}

	public static class WorldGenerationPages
	{
		
		public static LexiconPageBase[] CoralBasePage =
			{
				new PageTextWithPicture(new ResourceLocation(Reference.MODID + ":textures/gui/AllCoralPicture.png"), 0, 120, 110, 100, 110 , 100, LexiconPageData.Coral_Page_1_Text, 0),
				new PageTextWithPicture(new ItemStack(HydromancyBlockHandler.coral_orange), 45, 13, LexiconPageData.Orange_Coral_Page_1_Text, 20), 
				new PageTextWithPicture(new ResourceLocation(Reference.MODID + ":textures/gui/Test Coral Picture.png"), 0, 10, 110, 100, 110 ,100, LexiconPageData.Orange_Coral_Page_2_Text, 110), 
				new PageTextWithPicture(new ItemStack(HydromancyBlockHandler.coral_purple), 45, 83, LexiconPageData.Purple_Coral_Page_1_Text, 0), 
				new PageTextWithPicture(new ResourceLocation(Reference.MODID + ":textures/gui/LastCoralPieces.png"), 0, 10, 110, 100, 110 ,100, LexiconPageData.Orange_Coral_Page_3_Text, 110), 
				new PageTextWithPicture(new ItemStack(HydromancyBlockHandler.coral_blue), 45, 13, LexiconPageData.Blue_Coral_Page_1_Text, 20)
			};			
	
	}

	public static class TransportationPages
	{
		/*
		public static LexiconEntry CoralPumpPage = new LexiconEntry("Coral Pump", "lets_make_use_of_it", HydromancyBlocksHandler.Coral_Pump, Reference.TransportationCategory_ID,
				new PageText(Reference.LexiconData.CoralPump_Page_1_Text),  
				new PageNormalCraftingRecipe(new ItemStack[]{new ItemStack(HydromancyBlocksHandler.Block_Coral6), new ItemStack(Items.compass), new ItemStack(HydromancyBlocksHandler.Block_Coral6), new ItemStack(Items.gold_ingot), new ItemStack(Blocks.piston), new ItemStack(Items.gold_ingot), new ItemStack(HydromancyBlocksHandler.Block_Coral6), new ItemStack(Items.redstone) ,new ItemStack(HydromancyBlocksHandler.Block_Coral6)}, new ItemStack(HydromancyBlocksHandler.Coral_Pump),  Reference.LexiconData.CoralPump_Page_2_Text, -65));
		*/
		
		public static LexiconPageBase[] page_canteen = 
			{
			   new PageTextWithPicture(new ItemStack(HydromancyItemHandler.item_canteen), 45, 13, LexiconPageData.canteen_description, 25),
			   new PageNormalCraftingRecipe(new ItemStack[]{new ItemStack(HydromancyBlockHandler.coral_blue), 
					   new ItemStack(Items.IRON_INGOT), new ItemStack(HydromancyBlockHandler.coral_cyan), new ItemStack(Items.LEATHER), new ItemStack(Items.BUCKET), new ItemStack(Items.LEATHER), new ItemStack(HydromancyBlockHandler.coral_purple), new ItemStack(Items.LEATHER) ,new ItemStack(HydromancyBlockHandler.coral_pink)}, new ItemStack(HydromancyItemHandler.item_canteen),  "I wonder what the water would taste like?", -65),
			};
	}
	
	
	public static class LexiconPageData
	{
		public static final String canteen_description = 
				"I finally found a way to transport this water. All I have to do is cover a iron bucket with some of this natural coral"
				+ "and I am able to prevent the water from transforming back to normal water";
	
	
		public static final String Coral_Page_1_Text = "Who would of known that life could exist at the bottom of the ocean? "
				+ "Apparently the person who wrote this book! After some exploring "
				+ "There seems to be 7 different type of colourful coral that can grow. "
				+ "They seem to be named after their color and have unknown properties.";
		
		public static final String Orange_Coral_Page_1_Text = "This is the first of the magical coral that lives under the sea. At first "
				+ "it looks like an ordinary coral but for some reason it glows in the dark. It also cant seem to survive alone and has to be next to "
				+ "a purple, the blue, or the bright green coral.";
		
		public static final String Orange_Coral_Page_2_Text = "A Hand drawn picture of the orange coral living next to the purple coral in harmony!";
		public static final String Orange_Coral_Page_3_Text = "Some more pieces of coral living next to each other!";
		
		
		public static final String Purple_Coral_Page_1_Text = "The purple coral seems to not care about what coral it is living next to as long as it has"
				+ " 5 blocks of water (including corners) surrounding the coral. It will die if these conditions are not met!                                           The pink coral is the most interesting"
				+ " of them all. For some reason it is only seen in the wild spawning next to four other pieces of coral and will not survive alone";
		
		public static final String Blue_Coral_Page_1_Text = "The blue coral can only live if there is a max of two coral pieces are next to it. "
				+ "In other words this piece of coral likes it space, and if it doesnt have it, the coral will die! The rest of the coral do not seem to have a unique function...atleast for now!";

	
	}

}
