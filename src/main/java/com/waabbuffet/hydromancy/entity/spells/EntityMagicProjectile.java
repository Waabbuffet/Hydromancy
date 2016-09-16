package com.waabbuffet.hydromancy.entity.spells;

import com.waabbuffet.hydromancy.spells.particles.HydromancyParticleHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityMagicProjectile extends EntityThrowable{

	public long boltVertex;
	public int Ticks;
	EntityLivingBase Target;
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private Block inTile;
	private boolean inGround;
	public EntityLivingBase shootingEntity;
	private int ticksAlive;
	private int ticksInAir;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;


	public EntityMagicProjectile(World worldIn) {
		super(worldIn);

		this.isImmuneToFire = true;

		this.boltVertex = this.rand.nextLong();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}


	@Override
	protected void onImpact(MovingObjectPosition movingObject)
	{
		if (!this.worldObj.isRemote)
		{

			if(!(movingObject.entityHit instanceof EntityPlayer))
			{
				if (movingObject.entityHit != null)
				{
					movingObject.entityHit.attackEntityFrom(DamageSource.magic, 10.0F);
					this.setDead();
				}
			}

		}
	}



	public IIcon getIcon() {

		return HydromancyParticleHandler.waterSpellIcon;
	}

	public int getColor() {
		// TODO Auto-generated method stub
		return 0xFFFFFF;
	}
	
	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		this.worldObj.spawnParticle("magicCrit", this.posX, this.posY, this.posZ, 0, 0, 0);
		
		super.onUpdate();
	}



}
