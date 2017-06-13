package com.waabbuffet.hydromancy.client.renderer;

import com.waabbuffet.hydromancy.tileEntity.TileEntityPurifyWater;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderTileEntityPurifyWater extends TileEntitySpecialRenderer<TileEntityPurifyWater>
{
	
	@Override
	public void renderTileEntityAt(TileEntityPurifyWater te, double x, double y, double z, float partialTicks, int destroyStage) 
	{
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
	}
}
