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

	public static List<List<String>> all_pages = new ArrayList<List<String>>();
	public static Map<String, LexiconPage> name_stack = new HashMap<String, LexiconPage>();

	public static void init()
	{
		for(EnumCategoryType cate : EnumCategoryType.values())
		{
			all_pages.add(new ArrayList<String>());  
		}

		registerPage(EnumCategoryType.GENERATION, new LexiconPage("Coral", LexiconPage.PageInfo.coral_description, null, 0, 0, new ItemStack(HydromancyItemHandler.item_canteen)));
		registerPage(EnumCategoryType.TRANSPORTATION, new LexiconPage("Canteen", LexiconPage.PageInfo.canteen_description, "Coral", 2, 2, new ItemStack(HydromancyItemHandler.item_canteen)));

	}

	private static void registerPage(EnumCategoryType category, LexiconPage page)
	{
		all_pages.get(category.getID()).add(page.getName());
		name_stack.put(page.getName(), page);
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
				System.out.println("" + cap.hasUnlockedPage(name));
				if(!cap.hasUnlockedPage(name))
				{
					cap.unlockPage(name);
					return name;
				}
			}
		}

		return null;
	}


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
}
