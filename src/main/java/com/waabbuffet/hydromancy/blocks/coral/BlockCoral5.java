package com.waabbuffet.hydromancy.blocks.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockCoral5 extends BlockCoralBase{

	
	
	@Override
	public boolean isOpaqueCube() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	@Override
	public boolean canBlockStay(World world, int p_149718_2_, int p_149718_3_, int p_149718_4_) {

		return true;
	}
	
	
	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_,
			int p_149742_3_, int p_149742_4_) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	@Override
	public void spawnCoralRequirements(World world, int x, int y, int z) {
	
		world.setBlock(x, y, z, HydromancyBlocksHandler.Block_Coral5);
		
		
		super.spawnCoralRequirements(world, x, y, z);
	}
}
