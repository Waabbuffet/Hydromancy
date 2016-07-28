package com.waabbuffet.hydromancy.proxy;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.tileentity.HydromancyTileEntityHandler;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	
	
	public void PreInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
		HydromancyBlocksHandler.init();
		HydromancyBlocksHandler.register();
		
		
		HydromancyItemsHandler.init();
		HydromancyItemsHandler.register();
		
		
		HydromancyPacketHandler.init();
		
		HydromancyTileEntityHandler.register();
	}

	public void Init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
		
	}

	public void PostInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
		
	}

	
	
	
}
