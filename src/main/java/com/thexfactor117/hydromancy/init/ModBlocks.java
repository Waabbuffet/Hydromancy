package com.thexfactor117.hydromancy.init;

import com.thexfactor117.hydromancy.blocks.BlockPurifier;
import com.thexfactor117.hydromancy.helpers.RegisterHelper;
import com.thexfactor117.hydromancy.tiles.TilePurifier;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * 
 * @author TheXFactor117
 *
 */
public class ModBlocks 
{
	public static Block purifier = new BlockPurifier("purifier");
	
	public static void registerBlocks()
	{
		RegisterHelper.registerBlock(purifier);
		
		registerTileEntities();
	}
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TilePurifier.class, "purifier");
	}
}
