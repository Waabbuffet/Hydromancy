package com.waabbuffet.hydromancy.blocks.fluid;

import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidPurifiedWater extends Fluid
{
	public FluidPurifiedWater() 
	{
		super("purified_0_water", new ResourceLocation(Reference.MODID + ":blocks/purified_0_water_still"), new ResourceLocation(Reference.MODID + ":blocks/purified_0_water_flow"));
		setUnlocalizedName("purified_0_water");
		
		FluidRegistry.registerFluid(this);
		FluidRegistry.addBucketForFluid(this);
	}
	
	
}
