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
				+ "exciting thing to do.     What if we wrapped a piston with some coral and a diamond?                   "
				+ "This pump can pull a liquid from 1 block behind the pump and will push liquids in front of the pump. "
				+ "However every liquid pushed will have increased impurities."
				;
		
		public static final String CoralPump_Page_2_Text = "The coral pump will auto push any liquid up to 5 blocks in front of the pump. The pump "
				+ "can push only one type of liquid and you set the type of liquid it will push by putting a source block of the desired liquid underneath the block."; 
		
		public static final String Coral_Page_1_Text = "";
		
		
	}
	
}
