package com.waabbuffet.hydromancy.entity.water;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMermaid extends EntityTameable {


	public EntityMermaid(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.7F);

		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
	}


	@Override
	protected void applyEntityAttributes() {


		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(2.0D);
	}


	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {


		return null;
	}
}
