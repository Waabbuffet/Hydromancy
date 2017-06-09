package com.waabbuffet.hydromancy.items;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.util.LostPageColourHandler;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HydromancyItemHandler 
{
	public static Item item_canteen, item_lostPage, item_lexicon;

	public static void init()
	{
		item_canteen = new ItemCanteen("item_canteen");
		item_lostPage = new ItemLostPage("item_lostPage");
		item_lexicon = new ItemLexicon("item_lexicon");
	}

	public static void registerRenders()
	{
		registerRenders(item_canteen);
		registerRenders(item_lexicon);
		
		registerColorHandler(item_lostPage);
	}

	private static void registerRenders(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5),"inventory"));
	}
	
	private static void registerColorHandler(Item item)
	{
		List<ItemStack> allitems = new ArrayList<ItemStack>();
		item.getSubItems(item, null, allitems);

		for(int i = 0; i < allitems.size() + 1; i ++)
		{
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5),"inventory"));
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new LostPageColourHandler(), item);
		}	
	}
}
