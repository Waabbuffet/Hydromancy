package com.waabbuffet.hydromancy.proxy;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.capability.lexiconPages.CapabilityLexiconPages;
import com.waabbuffet.hydromancy.client.gui.HydromancyGuiHandler;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.lexicon.LexiconPageHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionTypesHandler;
import com.waabbuffet.hydromancy.tileEntity.HydromancyTileEntityHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void PreInit(FMLPreInitializationEvent event) 
	{
		// TODO Auto-generated method stub
		HydromancyItemHandler.init();
		HydromancyBlockHandler.init();
		HydromancyTileEntityHandler.register();
		
		HydromancyPotionHandler.initPotions();
		HydromancyPotionTypesHandler.registerPotions();
		
		CapabilityLexiconPages.register();
		LexiconPageHandler.init();
		
		HydromancyPacketHandler.init();
	}

	public void Init(FMLInitializationEvent event) 
	{
		// TODO Auto-generated method stub
		NetworkRegistry.INSTANCE.registerGuiHandler(Hydromancy.instance, new HydromancyGuiHandler());
		
	}

	public void PostInit(FMLPostInitializationEvent event) 
	{
		// TODO Auto-generated method stub
		
	}
}
