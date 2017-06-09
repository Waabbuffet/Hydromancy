package com.waabbuffet.hydromancy.packet;

import com.waabbuffet.hydromancy.util.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HydromancyPacketHandler 
{

	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID); 

	public static void init() 
	{
		INSTANCE.registerMessage(PacketSyncLexicon.class, PacketSyncLexicon.class, 0, Side.CLIENT);
	}	
}
