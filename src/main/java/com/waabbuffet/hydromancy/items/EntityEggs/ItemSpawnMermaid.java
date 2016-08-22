package com.waabbuffet.hydromancy.items.EntityEggs;

import com.waabbuffet.hydromancy.entity.water.EntityMermaid;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpawnMermaid extends Item{

	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		//Add stack information here only give pages that the player has not discovered
		if(!world.isRemote)
		{
			EntityMermaid mer = new EntityMermaid(world);
			mer.posX = player.posX;
			mer.posY = player.posY;
			mer.posZ = player.posZ;
			world.spawnEntityInWorld(mer);
		}

		return super.onItemRightClick(stack, world, player);
	}
	
	
	
}
