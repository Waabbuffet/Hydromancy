package com.waabbuffet.hydromancy.blocks.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockCoral extends BlockBush
{
	public BlockCoral(Material materialIn) 
	{
		super(materialIn);
	}
	
	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) 
	{
		return canSurvive();
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) 
	{
		
		return super.canPlaceBlockAt(worldIn, pos);
	}
	
	public abstract boolean canSurvive();
	
}
