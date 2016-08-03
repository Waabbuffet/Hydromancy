package com.waabbuffet.hydromancy;

import com.waabbuffet.hydromancy.proxy.CommonProxy;
import com.waabbuffet.hydromancy.util.Reference;
import org.apache.logging.log4j.LogManager;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Hydromancy 
{
	@Instance(Reference.MODID)
	public static Hydromancy instance;
	
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	
	public static final Logger LOGGER = LogManager.getLogger("Lost Eclipse");

	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.PreInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.Init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		proxy.PostInit(event);
	}
}

