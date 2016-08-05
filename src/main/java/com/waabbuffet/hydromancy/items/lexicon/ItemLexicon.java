package com.waabbuffet.hydromancy.items.lexicon;

import com.waabbuffet.hydromancy.Hydromancy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLexicon extends Item{

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
	
		if(world.isRemote)
		{
			player.openGui(Hydromancy.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		
		return super.onItemRightClick(stack, world, player);
	}
	
	
	
	
	
	
	
	
	
	
}
