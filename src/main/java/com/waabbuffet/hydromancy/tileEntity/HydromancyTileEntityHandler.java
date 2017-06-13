package com.waabbuffet.hydromancy.tileEntity;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.waabbuffet.hydromancy.client.renderer.RenderTileEntityPurifyWater;
import com.waabbuffet.hydromancy.tileEntity.consume.TileEntityObelisk;

public class HydromancyTileEntityHandler 
{

	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityPurifiedWater.class, "TileEntityPurifiedWater");
		GameRegistry.registerTileEntity(TileEntityPurifyWater.class, "TileEntityPurifyWater");
		GameRegistry.registerTileEntity(TileEntityObelisk.class, "TileEntityObelisk");
		
		
		
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRender()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPurifyWater.class, new RenderTileEntityPurifyWater());
	}

}
