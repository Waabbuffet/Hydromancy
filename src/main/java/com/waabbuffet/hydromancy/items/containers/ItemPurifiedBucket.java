package com.waabbuffet.hydromancy.items.containers;

import java.util.List;

import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPurifiedBucket extends ItemBucket {

	public ItemPurifiedBucket(Block p_i45331_1_) {
		super(p_i45331_1_);

		this.setCreativeTab(CreativeTabs.tabMisc);
	}


	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List information, boolean NoIdea) {

		
		if(stack.hasTagCompound())
		{
			information.add("Purity: " + stack.getTagCompound().getInteger("Purity") + "%");
		}
				
		super.addInformation(stack, player, information, NoIdea);
	}



}
