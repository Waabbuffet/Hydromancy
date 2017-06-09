package com.waabbuffet.hydromancy.tileEntity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class HydromancyTileEntityHandler 
{

	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityPurifiedWater.class, "TileEntityPurifiedWater");
	}

}
