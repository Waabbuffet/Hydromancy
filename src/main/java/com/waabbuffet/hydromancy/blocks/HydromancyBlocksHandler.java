package com.waabbuffet.hydromancy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import com.waabbuffet.hydromancy.blocks.coral.BlockCoral;
import com.waabbuffet.hydromancy.blocks.coral.BlockCoral1;
import com.waabbuffet.hydromancy.blocks.coral.BlockCoral2;
import com.waabbuffet.hydromancy.blocks.coral.BlockCoral3;
import com.waabbuffet.hydromancy.blocks.coral.BlockCoral4;
import com.waabbuffet.hydromancy.blocks.coral.BlockCoral5;
import com.waabbuffet.hydromancy.blocks.coral.BlockCoral6;
import com.waabbuffet.hydromancy.blocks.fluids.FluidPurifiedWater;
import com.waabbuffet.hydromancy.blocks.generation.BlockPurifiedWater;
import com.waabbuffet.hydromancy.blocks.generation.BlockPurifier;
import com.waabbuffet.hydromancy.blocks.transportation.BlockCoralPump;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

public class HydromancyBlocksHandler {

	public static Block Block_Purifier, Block_Purified_Water, Block_Coral, Block_Coral1, Block_Coral2, Block_Coral3, Block_Coral4, Block_Coral5, Block_Coral6, Coral_Pump;
	
	public static Fluid FluidPurifiedWater;	
	
	public static void init()
	{
		FluidPurifiedWater = new FluidPurifiedWater("fluid_purified_water").setUnlocalizedName("fluid_purified_water").setViscosity(1000).setDensity(500);
		FluidRegistry.registerFluid(FluidPurifiedWater);
		
		Block_Purifier = new BlockPurifier().setBlockName(Reference.Purifier_Block_Name).setBlockTextureName(Reference.Purifier_Texture);
		Block_Purified_Water = new BlockPurifiedWater(FluidPurifiedWater, Material.water).setBlockName("block_purified_water").setBlockTextureName(Reference.MODID + ":purified_water");
		
		Coral_Pump = new BlockCoralPump().setBlockName("CoralPump").setBlockTextureName("");
		
		
		Block_Coral = new BlockCoral().setBlockName("coralTest").setBlockTextureName(Reference.MODID + ":coral1");
		Block_Coral1 = new BlockCoral1().setBlockName("coralTest1").setBlockTextureName(Reference.MODID + ":coral3");
		Block_Coral2 = new BlockCoral2().setBlockName("coralTest2").setBlockTextureName(Reference.MODID + ":coral4");
		Block_Coral3 = new BlockCoral3().setBlockName("coralTest3").setBlockTextureName(Reference.MODID + ":coral6");
		Block_Coral4 = new BlockCoral4().setBlockName("coralTest4").setBlockTextureName(Reference.MODID + ":Coral Base 1");
		Block_Coral5 = new BlockCoral5().setBlockName("coralTest5").setBlockTextureName(Reference.MODID + ":Coral Base 2");
		Block_Coral6 = new BlockCoral6().setBlockName("coralTest6").setBlockTextureName(Reference.MODID + ":Coral Base 3");
	}
	
	public static void register()
	{		
		GameRegistry.registerBlock(Block_Purifier, Block_Purifier.getUnlocalizedName().substring(5));	
		GameRegistry.registerBlock(Block_Purified_Water, Block_Purified_Water.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Coral_Pump, Coral_Pump.getUnlocalizedName().substring(5));
		
		
		GameRegistry.registerBlock(Block_Coral, Block_Coral.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Block_Coral1, Block_Coral1.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Block_Coral2, Block_Coral2.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Block_Coral3, Block_Coral3.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Block_Coral4, Block_Coral4.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Block_Coral5, Block_Coral5.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Block_Coral6, Block_Coral6.getUnlocalizedName().substring(5));
		
	}
}
