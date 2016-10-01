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

	

	public static IIcon RedBeam, waterBolt, TestGui;
	
	public static void initSpellIcons(IIconRegister IconRegister)
	{
		RedBeam = IconRegister.registerIcon(Reference.MODID + ":RedBeam");
		waterBolt = IconRegister.registerIcon(Reference.MODID + ":WaterBolt");
		TestGui = IconRegister.registerIcon(Reference.MODID + ":magic_circle");
	}
}
