package com.waabbuffet.hydromancy.items.lexicon;

import java.util.List;

import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemDecipheringStone extends Item {




	public ItemDecipheringStone() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List information, boolean NoIdea) {

		//When the page numbers match...it will show the text in the gui

		if(stack.hasTagCompound())
		{ 
			information.add("Page Number: " + stack.getTagCompound().getInteger("PageNumber") + "/100");
		}

		super.addInformation(stack, player, information, NoIdea);
	}



/*
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		//Add stack information here only give pages that the player has not discovered
		if(!stack.hasTagCompound())
		{

			NBTTagCompound tag = new NBTTagCompound();

			int textNumber = TranslationTableUtils.getUnknownPageNumber(player);

			tag.setInteger("PageNumber",  10); 
			stack.setTagCompound(tag);
		}


		return super.onItemRightClick(stack, world, player);
	}
*/

}
