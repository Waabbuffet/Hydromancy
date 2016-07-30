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

	public static Item purified_bucket;
	
	
	public static void init()
	{
		FluidPurifiedWater = new FluidPurifiedWater("fluid_purified_water").setUnlocalizedName("fluid_purified_water");
		FluidRegistry.registerFluid(FluidPurifiedWater);
		
		Block_Purifier = new BlockPurifier("purifier");
		Block_Purified_Water = new BlockPurifiedWater(FluidPurifiedWater, Material.water).setBlockName("block_purified_water").setBlockTextureName(Reference.MODID + ":purified_water");
	
		purified_bucket = new ItemPurifiedBucket(Block_Purified_Water).setUnlocalizedName("purified_bucket").setTextureName(Reference.MODID + ":purified_bucket");
		
	}
	
	public static void register()
	{
		GameRegistry.registerItem(purified_bucket, purified_bucket.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(Block_Purifier, Block_Purifier.getUnlocalizedName().substring(5));	
		GameRegistry.registerBlock(Block_Purified_Water, Block_Purified_Water.getUnlocalizedName().substring(5));
	}
}
