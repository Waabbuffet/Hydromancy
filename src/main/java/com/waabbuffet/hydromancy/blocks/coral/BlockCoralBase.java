package com.waabbuffet.hydromancy.blocks.coral;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockCoralBase extends BlockBush{

	public BlockCoralBase() {
		
		super(Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	/**
	 * This method spawns both the requirements and the actual coral!
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	
	public void spawnCoralRequirements(World world, int x, int y, int z)
	{
		
	}

}
