package com.waabbuffet.hydromancy.client.event;

import com.waabbuffet.hydromancy.client.ClientProxy;
import com.waabbuffet.hydromancy.tileEntity.TileEntityPurifiedWater;
import com.waabbuffet.hydromancy.tileEntity.TileEntityPurifyWater;
import com.waabbuffet.hydromancy.util.PurifiedWaterUtil;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HydromancyClientEvent 
{
	ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/potions/whispering_thoughts.png");
	ResourceLocation old = new ResourceLocation("textures/gui/icons.png");
	boolean show_purity;

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent e)
	{
		RayTraceResult trace = PurifiedWaterUtil.rayTrace(e.player, 5, 1f, true);

		if(trace != null)
		{
			if(trace.getBlockPos() != null)
			{
				TileEntity entity = e.player.worldObj.getTileEntity(trace.getBlockPos());
				if(entity instanceof TileEntityPurifiedWater)
				{
					ClientProxy.water = (TileEntityPurifiedWater) entity;
					return;
				}
			}
		}
		ClientProxy.water = null;
	}

	@SubscribeEvent
	public void renderGameOverlayEvent(RenderGameOverlayEvent.Post e)
	{
		if(ClientProxy.water != null)
		{	int water = ClientProxy.water.getpurity();
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.color(1f, 1f, 1f, 1f);
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			GuiScreen.drawModalRectWithCustomSizedTexture(180, 80, 0,  0,  16, 16,  16, 16);


			if(Minecraft.getMinecraft().fontRendererObj != null)
				Minecraft.getMinecraft().fontRendererObj.drawString("" + water, 200, 85, 0xffffff);

			GlStateManager.popMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(old);
		}
	}
}
