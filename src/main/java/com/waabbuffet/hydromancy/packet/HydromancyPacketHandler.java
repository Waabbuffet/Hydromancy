package com.waabbuffet.hydromancy.packet;

import com.waabbuffet.hydromancy.packet.packets.CSyncHydromancyPlayerProperties;
import com.waabbuffet.hydromancy.packet.packets.PacketSyncLexicon;
import com.waabbuffet.hydromancy.packet.packets.SSyncHydromancyPlayerProperties;
import com.waabbuffet.hydromancy.packet.packets.SUpdateTranslationTable;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HydromancyPacketHandler 
{
	private static int packetId = 0;
	
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID); 

	public static void init() 
	{
		INSTANCE.registerMessage(PacketSyncLexicon.class, PacketSyncLexicon.class, packetId++, Side.CLIENT);
		INSTANCE.registerMessage(CSyncHydromancyPlayerProperties.class, CSyncHydromancyPlayerProperties.class, packetId++, Side.CLIENT);
		
		INSTANCE.registerMessage(SSyncHydromancyPlayerProperties.class, SSyncHydromancyPlayerProperties.class, packetId++, Side.SERVER);
		INSTANCE.registerMessage(SUpdateTranslationTable.class, SUpdateTranslationTable.class, packetId++, Side.SERVER);
	}	
}
