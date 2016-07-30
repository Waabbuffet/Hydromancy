package com.waabbuffet.hydromancy.blocks.generation;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockPurifiedWater extends BlockFluidClassic{

	public BlockPurifiedWater(Fluid fluid, Material material) {
		super(fluid, material);
		
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setBlockUnbreakable();
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
