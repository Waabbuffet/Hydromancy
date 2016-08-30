package com.waabbuffet.hydromancy.entity.water;

import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMermaid extends EntitySquid {

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
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
		
		
	
	}

	@Override
	protected Item getDropItem() {
		// TODO Auto-generated method stub
		
		return null;
	}
	
}
