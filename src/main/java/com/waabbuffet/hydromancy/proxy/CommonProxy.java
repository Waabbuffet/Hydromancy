package com.waabbuffet.hydromancy.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.client.gui.HydromancyGuiHandler;
import com.waabbuffet.hydromancy.events.CommonEventHandler;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.tileentity.HydromancyTileEntityHandler;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

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
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Hydromancy.instance, new HydromancyGuiHandler());
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
	
		
	}

	public void PostInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
		
	}

	
	
	
}
