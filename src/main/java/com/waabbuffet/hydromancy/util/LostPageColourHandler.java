package com.waabbuffet.hydromancy.util;

import com.waabbuffet.hydromancy.items.ItemLostPage;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class LostPageColourHandler implements  IItemColor {

	
	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) 
	{		
		ItemLostPage page = (ItemLostPage) stack.getItem();
		return tintIndex == 0 ? page.getRBG(stack.getItemDamage()) : 0;
	}
}
