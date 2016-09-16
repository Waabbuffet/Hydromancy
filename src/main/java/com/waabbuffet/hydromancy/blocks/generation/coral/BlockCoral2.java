package com.waabbuffet.hydromancy.blocks.generation.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.util.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockCoral2 extends BlockCoralBase {



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

		return this.PlacementRequirements(world, x, y, z);
	}

	public boolean PlacementRequirements(World world, int x, int y, int z)
	{
		int NumberOfCoral = 0;

		for(int i = 0; i < 3; i ++)
		{
			for(int j = 0; j < 3; j ++)
			{
				if(world.getBlock(x + i - 1, y, z + j - 1) instanceof BlockCoralBase)
				{
					NumberOfCoral++;

					if(world.getBlock(x + i - 1, y, z + j - 1).equals(this) && (i != 1 && j != 1))
					{
						return false;
					}
				}
			}
		}

		if(NumberOfCoral >= 4)
		{
			return true;
		}

		return false;
	}


	@Override
	public void spawnCoralRequirements(World world, int x, int y, int z) {
		
		
		
		world.setBlock(x- 1, y, z, HydromancyBlocksHandler.Block_Coral6);
		world.setBlock(x + 1, y, z, HydromancyBlocksHandler.Block_Coral5);
		world.setBlock(x, y, z - 1, HydromancyBlocksHandler.Block_Coral5);
		world.setBlock(x, y, z + 1, HydromancyBlocksHandler.Block_Coral4);
		world.setBlock(x, y, z, HydromancyBlocksHandler.Block_Coral2);
		
	}
}
