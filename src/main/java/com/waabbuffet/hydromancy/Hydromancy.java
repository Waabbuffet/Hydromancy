package com.waabbuffet.hydromancy;



import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.waabbuffet.hydromancy.proxy.CommonProxy;
import com.waabbuffet.hydromancy.util.Reference;


@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Hydromancy 
{
	@Instance(Reference.MODID)
	public static Hydromancy instance;


	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;

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

