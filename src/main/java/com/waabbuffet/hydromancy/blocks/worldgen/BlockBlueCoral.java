package com.waabbuffet.hydromancy.blocks.worldgen;

import net.minecraft.block.material.Material;

public class BlockBlueCoral extends BlockCoral{

	public BlockBlueCoral(Material materialIn) 
	{
		super(materialIn);
		
	}

	@Override
	public boolean canSurvive() 
	{
		return false;
	}

}
