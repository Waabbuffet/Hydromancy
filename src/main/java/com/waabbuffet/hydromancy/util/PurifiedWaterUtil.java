package com.waabbuffet.hydromancy.util;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<BlockPos> getAllWithin(BlockPos pos, int range)
	{
		List<BlockPos> all_blocks = new ArrayList<BlockPos>();
		
		for(int y = 0; y < 2; y ++)
		{
			for(int x = pos.getX() - range; x <= pos.getX() + range; x ++)
			{
				for(int z = pos.getZ() - range; z <= pos.getZ() + range; z ++)
				{
					all_blocks.add(new BlockPos(x, pos.getY() + y, z));
				}
			}
		}
		return all_blocks;
	}
	

	public static int getNearbyPurifyingSource(World world)
	{
		return 0;
	}
	
	public static void drainNearbyLiquids(World world)
	{
		
	}
	
			
}
