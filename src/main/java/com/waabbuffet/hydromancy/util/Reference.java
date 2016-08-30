package com.waabbuffet.hydromancy.util;

public class Reference 
{
	public static final String MODID = "hydromancy";
	public static final String NAME = "Hydromancy";
	public static final String VERSION = "@VERSION@";
	public static final String BUILD_NUMBER = "@BUILDNUMBER@";
	public static final String MC_VERSION = "@MVERSION@";
	
	public static final String COMMON_PROXY = "com.waabbuffet.hydromancy.proxy.CommonProxy";
	public static final String CLIENT_PROXY = "com.waabbuffet.hydromancy.client.ClientProxy";

	
	

	public static final String Purifier_Block_Name = "purifier";
	public static final String Purifier_Texture = MODID +":purifier";
	
	public static final String CoralPump_Name = "CoralPump";
	public static final String CoralPump_Texture = MODID +":CoralPump";
	
	
	public static final int GenerationCategory_ID = 0;
	public static final int TransportationCategory_ID = 1;
	public static final int WorldGenerationCategory_ID = 2;
	
	
	
	
	
	
	public static class LexiconData
	{
		public static final String Purifier_Page_1_Text = 
				"You have attempted to perform basic Hydromancy, but no success thus far. "
				+ "The problem seems to be all of the impurities inside the water you are using. "
				+ "Now only if there was a way of removing these impurities, then the water would be useful! "
				+ "You think you might have discovered a way!            "
				+ "                     What if we wrapped a furnace with some coral and iron buckets?";
		
		public static final String Purifier_Page_2_Text = "This purifier is not perfect and you have found "
				+ "it to be very difficult to clean the water with the machine! The purifier takes about 2 minutes to run and "
				+ "requires some fuel to run. Hopefully this water will be able to perform and receive better results.";
		
		public static final String Purifier_Page_3_Text = "The purifier purifies water infront of the block in a 3 by 3 by 10% every "
				+ "2 minutes                      Requires: any fuel and water to function!";
		
		public static final String CoralPump_Page_1_Text = "Its hard to move water by hand and it isnt the most "
				+ "exciting thing to do.     What if we wrapped a piston with some coral and a compass?                   "
				+ "This pump can pull a liquid from 1 block behind the pump and will push liquids in front of the pump. "
				+ "However every liquid pushed will have increased impurities."
				;
		
		public static final String CoralPump_Page_2_Text = "The coral pump will auto push any liquid up to 5 blocks in front of the pump. The pump "
				+ "can push only one type of liquid and you set the type of liquid it will push by putting a source block of the desired liquid underneath the block."; 
		
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
