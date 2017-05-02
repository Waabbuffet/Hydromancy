package com.waabbuffet.hydromancy.items.lexicon;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.util.BlockPos;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLexicon extends Item{

	
	
	public ItemLexicon() {
		this.setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
	
		// If player not sneaking, open the inventory gui
		if (player.isSneaking())
			player.openGui(Hydromancy.instance, 4, world, 0, 0, 0);
		if(world.isRemote)
		{
			if (!player.isSneaking())
				player.openGui(Hydromancy.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		
		return super.onItemRightClick(stack, world, player);
	}
	
	
	/*
	public static boolean isSufficientToSpawn(BlockPos position, World world)
	{
		
		if(world.getBlock(position.getX(), position.getY(), position.getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
		{
			if(world.getBlock(position.north().getX(), position.getY(), position.north().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater) )
			{
				if(world.getBlock(position.north().west().getX(), position.getY(), position.north().west().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
				{
					if(world.getBlock(position.west().getX(), position.getY(), position.west().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
					{
						return true;
					}
				}
			}
		}else if(world.getBlock(position.getX(), position.getY(), position.getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
		{
			if(world.getBlock(position.north().getX(), position.getY(), position.north().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater) )
			{
				if(world.getBlock(position.north().east().getX(), position.getY(), position.north().east().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
				{
					if(world.getBlock(position.east().getX(), position.getY(), position.east().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
					{
						return true;
					}
				}
			}
		}else if(world.getBlock(position.getX(), position.getY(), position.getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
		{
			if(world.getBlock(position.south().getX(), position.getY(), position.south().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater) )
			{
				if(world.getBlock(position.south().east().getX(), position.getY(), position.south().east().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
				{
					if(world.getBlock(position.east().getX(), position.getY(), position.east().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
					{
						return true;
					}
				}
			}
		}else if(world.getBlock(position.getX(), position.getY(), position.getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
		{
			if(world.getBlock(position.south().getX(), position.getY(), position.south().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater) )
			{
				if(world.getBlock(position.south().east().getX(), position.getY(), position.south().east().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
				{
					if(world.getBlock(position.east().getX(), position.getY(), position.east().getZ()).equals(HydromancyBlocksHandler.FluidPurifiedWater))
					{
						return true;
					}
				}
			}
		}
		

		return false;
	}
	
	*/
	

	
	
	
	
}
