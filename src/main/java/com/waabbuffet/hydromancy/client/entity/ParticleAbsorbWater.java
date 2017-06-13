package com.waabbuffet.hydromancy.client.entity;

import net.minecraft.client.particle.ParticleCloud;
import net.minecraft.world.World;

public class ParticleAbsorbWater extends ParticleCloud{

	public ParticleAbsorbWater(World worldIn, double xCoordIn,
			double yCoordIn, double zCoordIn, double p_i1221_8_,
			double p_i1221_10_, double p_i1221_12_) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, p_i1221_8_, p_i1221_10_,
				p_i1221_12_);
		
		particleBlue = 255;
		particleGravity = 0;
		particleRed = 0;
		particleGreen = 0;
	}

}
