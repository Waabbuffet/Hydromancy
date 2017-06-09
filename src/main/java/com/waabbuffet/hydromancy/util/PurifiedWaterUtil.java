package com.waabbuffet.hydromancy.util;

import net.minecraft.block.BlockAir;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PurifiedWaterUtil 
{
	
	public static boolean hasPurifyingSource(World world, BlockPos startingPos, int range)
	{
		BlockPos new_pos = startingPos.west(range).north(range);
		for(int x = 0; x < range; x ++)
		{
			for(int z = 0; z < range; z ++)
			{
				if(world.getBlockState(new_pos.east(x).south(z)).getBlock() instanceof BlockAir)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static int getNearbyPurifyingSource(World world)
	{
		return 0;
	}
	
	public static void drainNearbyLiquids(World world)
	{
		
	}
	
			
}
