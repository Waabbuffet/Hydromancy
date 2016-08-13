package com.waabbuffet.hydromancy.packet;



import com.waabbuffet.hydromancy.packet.packets.PlaceAirBlock;
import com.waabbuffet.hydromancy.packet.packets.PlaceBlock;
import com.waabbuffet.hydromancy.packet.packets.PlayerLexiconUpdate;
import com.waabbuffet.hydromancy.packet.packets.SyncLexicon;
import com.waabbuffet.hydromancy.packet.packets.UpdateFluidPurity;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class HydromancyPacketHandler {

	 public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID); 
	    
	 public static void init() 
	 {
		  INSTANCE.registerMessage(PlaceBlock.class, PlaceBlock.class, 0, Side.SERVER);
	      INSTANCE.registerMessage(UpdateFluidPurity.class, UpdateFluidPurity.class, 1, Side.SERVER);
	      INSTANCE.registerMessage(PlaceAirBlock.class, PlaceAirBlock.class, 2, Side.SERVER);
	      INSTANCE.registerMessage(SyncLexicon.class, SyncLexicon.class, 3, Side.CLIENT);
	      INSTANCE.registerMessage(PlayerLexiconUpdate.class, PlayerLexiconUpdate.class, 4, Side.CLIENT);
	      
	 }	
}
