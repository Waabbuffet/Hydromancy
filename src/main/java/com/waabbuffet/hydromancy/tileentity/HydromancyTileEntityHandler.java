package com.waabbuffet.hydromancy.tileentity;

import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifiedWater;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.tileentity.transportation.TileEntityCoralPump;

import cpw.mods.fml.common.registry.GameRegistry;

public class HydromancyTileEntityHandler {


	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityPurifier.class, "hydromancerPurifier");
		GameRegistry.registerTileEntity(TileEntityPurifiedWater.class, "hydromancerPurifiedWater");
		GameRegistry.registerTileEntity(TileEntityCoralPump.class, "hydromancerCoralPump");
		GameRegistry.registerTileEntity(TileEntityTranslationTable.class, "hydromancerTranslationTable");
		
		
	}
}
