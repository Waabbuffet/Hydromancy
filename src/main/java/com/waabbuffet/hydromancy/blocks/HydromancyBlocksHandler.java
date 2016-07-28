package com.waabbuffet.hydromancy.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class HydromancyBlocksHandler {

	public static Block Block_Purifier;

	
	
	public static void init()
	{
		Block_Purifier = new BlockPurifier("purifier");
	
	
		
	}
	
	public static void register()
	{
		GameRegistry.registerBlock(Block_Purifier, Block_Purifier.getUnlocalizedName().substring(5));	
	}
}
