package com.waabbuffet.hydromancy.packet;


import com.waabbuffet.hydromancy.packet.packets.CSyncHydromancyPlayerProperties;
import com.waabbuffet.hydromancy.packet.packets.SUpdateTranslationTable;
import com.waabbuffet.hydromancy.packet.packets.PlaceAirBlock;
import com.waabbuffet.hydromancy.packet.packets.PlaceBlock;
import com.waabbuffet.hydromancy.packet.packets.PlayerLexiconUpdate;
import com.waabbuffet.hydromancy.packet.packets.SyncLexicon;
import com.waabbuffet.hydromancy.packet.packets.SSyncHydromancyPlayerProperties;
import com.waabbuffet.hydromancy.packet.packets.SUpdateLexiconInventory;
import com.waabbuffet.hydromancy.packet.packets.UpdateFluidPurity;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class HydromancyPacketHandler {

	private static int packetId = 0;
	
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID); 
	    
	public static void init() 
	{
		  
		INSTANCE.registerMessage(PlaceBlock.class, PlaceBlock.class, packetId++, Side.SERVER);
		INSTANCE.registerMessage(UpdateFluidPurity.class, UpdateFluidPurity.class, packetId++, Side.SERVER);
		INSTANCE.registerMessage(PlaceAirBlock.class, PlaceAirBlock.class, packetId++, Side.SERVER);
		INSTANCE.registerMessage(SUpdateTranslationTable.class, SUpdateTranslationTable.class, packetId++, Side.SERVER);
		INSTANCE.registerMessage(SSyncHydromancyPlayerProperties.class, SSyncHydromancyPlayerProperties.class, packetId++, Side.SERVER);
		INSTANCE.registerMessage(CSyncHydromancyPlayerProperties.class, CSyncHydromancyPlayerProperties.class, packetId++, Side.CLIENT);
		INSTANCE.registerMessage(SyncLexicon.class, SyncLexicon.class, packetId++, Side.CLIENT);
		INSTANCE.registerMessage(PlayerLexiconUpdate.class, PlayerLexiconUpdate.class, packetId++, Side.CLIENT);
		INSTANCE.registerMessage(SUpdateLexiconInventory.class, SUpdateLexiconInventory.class, packetId++, Side.SERVER);
	}	
}
