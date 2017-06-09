package com.waabbuffet.hydromancy.potion;

public class HydromancyPotionHandler 
{
	
	public static PotionWhisperingThoughts whispering_thoughts;
	
	
	public static void initPotions()
	{
		whispering_thoughts = new PotionWhisperingThoughts(false, 0, 0, 20, "whispering_thoughts");
	}
}
