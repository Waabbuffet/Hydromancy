package com.waabbuffet.hydromancy.blocks.coral;

import java.util.Random;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.util.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockCoral1 extends BlockCoralBase {

	
	@Override
	public boolean isOpaqueCube() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean canBlockStay(World world, int p_149718_2_, int p_149718_3_, int p_149718_4_) {

		return this.PlacementRequirements(world, p_149718_2_, p_149718_3_, p_149718_4_);
	}


	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {

		return true;
	}

	public boolean PlacementRequirements(World world, int x, int y, int z)
	{
		int NumberOfWater = 0;
	
		for(int i = 0; i < 3; i ++)
		{
			for(int j = 0; j < 3; j ++)
			{
				
				if(world.getBlock(x + i - 1, y, z + j - 1).equals(Blocks.water))
				{
					NumberOfWater++;
				}
			}
		}
		
		if(NumberOfWater > 5)
		{
			
			return true;
		}
		
	
		
		return false;
	}
	

	
	@Override
	public void spawnCoralRequirements(World world, int x, int y, int z) {
	
		world.setBlock(x, y, z, HydromancyBlocksHandler.Block_Coral1);
		
		
		super.spawnCoralRequirements(world, x, y, z);
	}
}
