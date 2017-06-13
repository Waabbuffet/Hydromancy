package com.waabbuffet.hydromancy.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PurifiedWaterUtil 
{
	public static boolean hasPurifyingSource(World world, BlockPos startingPos, int range)
	{
		boolean flag = false;
		List<BlockPos> all_pos = getAllWithin(startingPos, range);

		for(BlockPos pos : all_pos)
		{
			Block block = world.getBlockState(pos).getBlock();
			if(block instanceof IUsePurifiedWater)
			{
				if(((IUsePurifiedWater) block).isPreserver())
				{
					flag = true;
				}
			}
		}
		return flag;
	}

	public static List<BlockPos> getAllWithin(BlockPos pos, int range)
	{
		List<BlockPos> all_blocks = new ArrayList<BlockPos>();
		for(int y = pos.getY() - 2; y < pos.getY() + 2; y ++)
		{
			for(int x = pos.getX() - range; x <= pos.getX() + range; x ++)
			{
				for(int z = pos.getZ() - range; z <= pos.getZ() + range; z ++)
				{
					all_blocks.add(new BlockPos(x, y, z));
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
	
	
	
	@Nullable
    @SideOnly(Side.CLIENT)
    public static RayTraceResult rayTrace(EntityPlayer player, double blockReachDistance, float partialTicks, boolean stopOnLiquid)
    {
        Vec3d vec3d = player.getPositionEyes(partialTicks);
        Vec3d vec3d1 = player.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.xCoord * blockReachDistance, vec3d1.yCoord * blockReachDistance, vec3d1.zCoord * blockReachDistance);
        return player.worldObj.rayTraceBlocks(vec3d, vec3d2, stopOnLiquid, false, true);
    }

}
