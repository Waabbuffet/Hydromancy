package com.waabbuffet.hydromancy.entity.water;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;

public class EntityMermaid extends EntityTameable {

	
	public EntityMermaid(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.7F);
	
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		
	
		return null;
	}
}
