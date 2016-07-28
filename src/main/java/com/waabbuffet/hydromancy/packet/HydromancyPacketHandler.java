package com.waabbuffet.hydromancy.packet;



import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class HydromancyPacketHandler {

	 public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID); 
	    
	 public static void init() 
	 {
	    
		
	        
	 }	
}
