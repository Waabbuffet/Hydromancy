package com.waabbuffet.hydromancy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;

import net.minecraft.entity.player.EntityPlayer;

public class TranslationTableUtils {

	static Random rand = new Random();

	static String[] namePartsArray = "Would you want to drink that water the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale".split(" ");

	public static List<List<TranslationTablePage>> Lexiconpages = new ArrayList<List<TranslationTablePage>>();

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


	public static String getTextBasedOnPageNumber(int Page, int categoryNumber) // Number in hundreds is category
	{
		//can hard code sentences for cool effects!
		String Text = null;

		if(Lexiconpages.get(categoryNumber).get(Page).Text != null)
		{
			Text = Lexiconpages.get(categoryNumber).get(Page).Text;
		}

		return Text;
	}


	public static String getClueBasedOnPageNumber(int Page, int categoryNumber)// Number in hundreds is category
	{
		//tell the player how to get the stone for each page number
		String Text = null;



		if(Lexiconpages.get(categoryNumber).get(Page).Clue != null)
			Text = Lexiconpages.get(categoryNumber).get(Page).Clue;

		return Text;
	}
	
	public static String getNameBasedOnPageNumber(int Page, int categoryNumber)// Number in hundreds is category
	{
		//tell the player how to get the stone for each page number
		String Text = null;



		if(Lexiconpages.get(categoryNumber).get(Page).Clue != null)
			Text = Lexiconpages.get(categoryNumber).get(Page).LexiconPage.EntryName;

		return Text;
	}



	public static int[] getUnknownPageNumber(EntityPlayer player, int CategoryNumber) // Number in hundreds is category
	{
		//Assign a unknown page number to a lost page and when completed will get info on that page
	
		int[] page = new int[2];
		
		
		for(int i = 0; i < Lexiconpages.get(CategoryNumber).size(); i ++)
		{
			if(!hasPageNumber(player, CategoryNumber, i))
			{
				page[0] = CategoryNumber;
				page[1] = i;
				return page;
			}
		}
		

		return page;
	}

	public static boolean hasPageNumber(EntityPlayer player, int Page, int categoryNumber)
	{

		boolean unlockedPages[][] = HydromancyPlayerProperties.get(player).getLexiconPages();
		
		return unlockedPages[categoryNumber][Page];
	}


	public static String[] getNamePartsArray() {
		return namePartsArray;
	}


	public static void initPages()
	{
		//THIS HAS TO MATCH THE ORDER IN THE REFERENCE CLASS!!
		Lexiconpages.add(initGeneratingCatgory());
		Lexiconpages.add(initTransportationCatgory());
		Lexiconpages.add(initWorldGenerationCatgory());
		
		
	}

	public static List<TranslationTablePage> initGeneratingCatgory()
	{
		/*
		 * clue - clue to where the translation stone can be found
		 * text - Hard coded text for lost pages (CAN BE NULL)
		 * page - The corresponding page in the lexicon 
		 */
		List<TranslationTablePage> pages = new ArrayList<TranslationTablePage>();

		//Purifier
		pages.add(new TranslationTablePage("Hit that chicken with a...", "Would you drink that water", LexiconPages.GenerationPages.PurifierPage)); 
		
		
		

		return pages;
	}
	
	public static List<TranslationTablePage> initTransportationCatgory()
	{
		/*
		 * clue - clue to where the translation stone can be found
		 * text - Hard coded text for lost pages (CAN BE NULL)
		 * page - The corresponding page in the lexicon 
		 */
		List<TranslationTablePage> pages = new ArrayList<TranslationTablePage>();

		pages.add(new TranslationTablePage("Be a man fight a squid", "Push it to the limit", LexiconPages.TransportationPages.CoralPumpPage)); 


		return pages;
	}
	
	public static List<TranslationTablePage> initWorldGenerationCatgory()
	{
		/*
		 * clue - clue to where the translation stone can be found
		 * text - Hard coded text for lost pages (CAN BE NULL)
		 * page - The corresponding page in the lexicon 
		 */
		List<TranslationTablePage> pages = new ArrayList<TranslationTablePage>();

		pages.add(new TranslationTablePage("Life Under the sea", null, LexiconPages.WorldGenerationPages.CoralBasePage)); // Purifier
		pages.add(new TranslationTablePage("I touched the butt", null, LexiconPages.WorldGenerationPages.CoralOrangePage)); 
		

		return pages;
	}
	
	
	
}
