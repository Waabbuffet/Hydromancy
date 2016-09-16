package com.waabbuffet.hydromancy.client;

import net.minecraftforge.client.MinecraftForgeClient;

import com.waabbuffet.hydromancy.client.events.ClientTickEventHandler;
import com.waabbuffet.hydromancy.client.renderer.RenderLexicon;
import com.waabbuffet.hydromancy.entity.HydromancyEntityHandler;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.proxy.CommonProxy;
import com.waabbuffet.hydromancy.spells.particles.HydromancyParticleHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	
	public void PreInit(FMLPreInitializationEvent event) {
		super.PreInit(event);
		
		
	}

	public void Init(FMLInitializationEvent event) {
		super.Init(event);
		
		FMLCommonHandler.instance().bus().register(new ClientTickEventHandler());
		MinecraftForgeClient.registerItemRenderer(HydromancyItemsHandler.lexicon, new RenderLexicon());
		
		HydromancyEntityHandler.initRenders();
		
		HydromancyParticleHandler.initSpellIcons();
		
		
	}

	public void PostInit(FMLPostInitializationEvent event) {
		super.PostInit(event);
		
		
	}
	
	
	
}
