package com.waabbuffet.hydromancy.items.lexicon;

import java.util.List;
import java.util.Random;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemTranslatedPage extends Item {
	public ItemTranslatedPage() {
		this.setMaxStackSize(1);
	}
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List information, boolean NoIdea) {

		//When the page numbers match...it will show the text in the gui

		if(stack.hasTagCompound())
		{ 
			information.add("Research: " + stack.getTagCompound().getString("research"));
		}

		super.addInformation(stack, player, information, NoIdea);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(world.isRemote)
		{
			//System.out.println(stack.stackTagCompound.getString("research"));
			if(stack.hasTagCompound()){
				player.openGui(Hydromancy.instance, 3, world, (int)player.posX, (int)player.posY, (int)player.posZ);
			}
		}
		
		return super.onItemRightClick(stack, world, player);
	}
}
