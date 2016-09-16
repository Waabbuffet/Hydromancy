package com.waabbuffet.hydromancy.spells.particles;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class HydromancyParticleHandler {

	
	@SideOnly(Side.CLIENT)
	public static IIcon waterSpellIcon;
	
	@SideOnly(Side.CLIENT)
	public static void initSpellIcons()
	{
		IIconRegister IconRegister = Minecraft.getMinecraft().getTextureMapBlocks();
		
		waterSpellIcon = IconRegister.registerIcon(Reference.MODID + ":purified_0_water_still");
		
		
		
	}
	
	
	
	
	
	
	
}
