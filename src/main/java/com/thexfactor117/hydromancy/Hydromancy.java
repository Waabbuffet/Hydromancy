package com.thexfactor117.hydromancy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thexfactor117.hydromancy.init.ModBlocks;
import com.thexfactor117.hydromancy.proxies.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * 
 * @author TheXFactor117
 *
 */
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
		ModBlocks.registerBlocks();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
}
