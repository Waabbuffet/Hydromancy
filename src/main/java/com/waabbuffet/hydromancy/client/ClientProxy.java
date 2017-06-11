package com.waabbuffet.hydromancy.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.proxy.CommonProxy;
import com.waabbuffet.hydromancy.tileEntity.HydromancyTileEntityHandler;

public class ClientProxy extends CommonProxy 
{

	@Override
	public void PreInit(FMLPreInitializationEvent event) 
	{
		super.PreInit(event);
	
		HydromancyBlockHandler.registerFluidRenders();
	}

	@Override
	public void Init(FMLInitializationEvent event) 
	{
		super.Init(event);
		
		HydromancyTileEntityHandler.registerRender();
		this.registerRender();
	}

	@Override
	public void PostInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		super.PostInit(event);
	}
	
	public void registerRender()
	{
		HydromancyBlockHandler.registerRenders();
		HydromancyItemHandler.registerRenders();
	}
}
