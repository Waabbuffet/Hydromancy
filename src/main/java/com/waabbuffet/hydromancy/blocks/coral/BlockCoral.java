package com.waabbuffet.hydromancy.blocks.coral;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockCoral extends BlockBush {

	public BlockCoral() {
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
