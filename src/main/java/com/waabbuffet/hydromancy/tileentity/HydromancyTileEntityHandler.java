package com.waabbuffet.hydromancy.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;

public class HydromancyTileEntityHandler {

	
	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityPurifier.class, "hydromancerPurifier");
	
	
	}
}
