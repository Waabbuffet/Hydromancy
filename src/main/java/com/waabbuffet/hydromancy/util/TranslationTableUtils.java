package com.waabbuffet.hydromancy.util;

import java.util.Random;

import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;

import net.minecraft.entity.player.EntityPlayer;

public class TranslationTableUtils {

	static Random rand = new Random();
	
	static String[] namePartsArray = "The curse of noodles coral tries to take over the world while undiscovered TITANS beneath ocean full of sea creatures with Mounted Spider Horns and the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale".split(" ");
	
	
	public static String generateRandomText()
	{
		
		//generate random text for any Lost Page max 11 words
		int i = rand.nextInt(8) + 3;
        String s = "";

        for (int j = 0; j < i; ++j)
        {
            if (j > 0)
            {
                s = s + " ";
            }

            s = s + namePartsArray[rand.nextInt(namePartsArray.length)];
        }

        return s;
		
	}
	
	
	public static String getTextBasedOnPageNumber(int PageNumber)
	{
		//can hard code sentences for cool effects!
		
		return "NYI";
	}
	
	
	public static String getClueBasedOnPageNumber(int PageNumber)
	{
		//tell the player how to get the stone for each page number
		
		return "NYI";
	}
	
	
	public static int getUnknownPageNumber(EntityPlayer player)
	{
		//Assign a unknown page number to a lost page and when completed will get info on that page
		
		
		return 0;
	}
	
	
	public static String[] getNamePartsArray() {
		return namePartsArray;
	}
	
	
	
	
	
}
