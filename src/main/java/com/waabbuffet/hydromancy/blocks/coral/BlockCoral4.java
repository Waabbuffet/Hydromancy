package com.waabbuffet.hydromancy.blocks.coral;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCoral4 extends BlockBush {

	public BlockCoral4() {
		super(Material.cactus);
		// TODO Auto-generated constructor stub
		 this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean isOpaqueCube() {
		// TODO Auto-generated method stub
		return false;
	}
}
