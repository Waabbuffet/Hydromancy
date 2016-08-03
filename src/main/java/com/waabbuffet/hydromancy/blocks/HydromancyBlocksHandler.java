package com.waabbuffet.hydromancy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.waabbuffet.hydromancy.blocks.fluids.FluidPurifiedWater;
import com.waabbuffet.hydromancy.blocks.generation.BlockPurifiedWater;
import com.waabbuffet.hydromancy.blocks.generation.BlockPurifier;
import com.waabbuffet.hydromancy.items.containers.ItemPurifiedBucket;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

public class HydromancyBlocksHandler {

	public static Block Block_Purifier, Block_Purified_Water;
	
	public static Fluid FluidPurifiedWater;	
	
	public static void init()
	{
		FluidPurifiedWater = new FluidPurifiedWater("fluid_purified_water").setUnlocalizedName("fluid_purified_water").setViscosity(1000).setDensity(500);
		FluidRegistry.registerFluid(FluidPurifiedWater);
		
		Block_Purifier = new BlockPurifier().setBlockName(Reference.Purifier_Block_Name).setBlockTextureName(Reference.Purifier_Texture);
		Block_Purified_Water = new BlockPurifiedWater(FluidPurifiedWater, Material.water).setBlockName("block_purified_water").setBlockTextureName(Reference.MODID + ":purified_water");
		
	}
	
	public static void register()
	{		
		GameRegistry.registerBlock(Block_Purifier, Block_Purifier.getUnlocalizedName().substring(5));	
		GameRegistry.registerBlock(Block_Purified_Water, Block_Purified_Water.getUnlocalizedName().substring(5));
	}
}
