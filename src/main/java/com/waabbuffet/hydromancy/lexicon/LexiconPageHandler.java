package com.waabbuffet.hydromancy.lexicon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.waabbuffet.hydromancy.capability.lexiconPages.CapabilityLexiconPages;
import com.waabbuffet.hydromancy.capability.lexiconPages.IPlayerLexiconPages;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.util.EnumCategoryType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.TupleIntJsonSerializable;

public class LexiconPageHandler 
{
	//public static LexiconStatistics stat_manager = new LexiconStatistics();
	
	//this is what gets transmitted over packets
	public static List<List<String>> all_pages = new ArrayList<List<String>>();
	
	//this is how we recreate our object from string name
	public static Map<String, LexiconEntry> name_entry = new HashMap<String, LexiconEntry>();
	

	public static void init()
	{
		for(EnumCategoryType cate : EnumCategoryType.values())
		{
			all_pages.add(new ArrayList<String>());  
		}

		//registerPage(new LexiconEntry("Coral", "Coral", new ItemStack(HydromancyItemHandler.item_canteen), EnumCategoryType.WORLDGENERATION));
		registerPage(new LexiconEntry("Canteen", "Canteen", new ItemStack(HydromancyItemHandler.item_canteen), EnumCategoryType.TRANSPORTATION, LexiconPages.TransportationPages.page_canteen));

	}

	private static void registerPage(LexiconEntry page)
	{
		all_pages.get(page.CategoryID).add(page.EntryName);
		name_entry.put(page.EntryName, page);
	}

	public static List<String> getCategory(EnumCategoryType category)
	{
		return all_pages.get(category.getID());
	}

	public static List<String> getCategory(int category)
	{
		return all_pages.get(category);
	}

	public static String getRandomPage(EntityPlayer player, int category)
	{
		List<String> strings_to_choose =  getCategory(category);
		IPlayerLexiconPages cap = player.getCapability(CapabilityLexiconPages.BOTW_CAP, null);
		Random rand = new Random();

		if(strings_to_choose.size() > 0)
		{
			int random = rand.nextInt(strings_to_choose.size());

			for(int i = 0; i < strings_to_choose.size(); i ++)
			{
				String name = strings_to_choose.get(random);
				if(!cap.hasUnlockedPage(name))
				{
					cap.unlockPage(name);
					return name;
				}
			}
		}
		return null;
	}

/*
	public static List<Achievement> createListFromMap(Map<String, EnumResearchState> map)
	{
		if(map != null)
		{
			List<Achievement> achieve_to_believe = new ArrayList<Achievement>();
			Iterator<Entry<String, EnumResearchState>> itr = map.entrySet().iterator();

			while(itr.hasNext())
			{
				Entry<String, EnumResearchState> entry = itr.next();
				LexiconPage page = name_stack.get(entry.getKey());

				if(page != null)
				{
					achieve_to_believe.add(new Achievement(page.getName(), page.getName(), page.getRow(), page.getColumn(), page.getStack(), null));
				}
			}
			return achieve_to_believe;
		}
		return null;
	}
	*/
}
