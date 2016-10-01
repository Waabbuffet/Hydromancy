package com.waabbuffet.hydromancy.entity.spells;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.waabbuffet.hydromancy.spells.particles.HydromancyParticleHandler;

public class EntityMagicPortal extends Entity{

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
	
	public int NumberOfProjectiles, ProjectileCoolDown;

	private IIcon spellIcon;

	public EntityMagicPortal(World worldIn, IIcon spellIcon) {
		super(worldIn);

		this.isImmuneToFire = true;
		this.boltVertex = this.rand.nextLong();
		// TODO Auto-generated constructor stub
	}
	
	public EntityMagicPortal(World worldIn) {
		super(worldIn);

		this.isImmuneToFire = true;
		this.boltVertex = this.rand.nextLong();
		
		this.NumberOfProjectiles = 20;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canBePushed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

	public void setSpellIcon(IIcon spellIcon) {
		this.spellIcon = spellIcon;
	}


	public IIcon getIcon() {

		return this.spellIcon != null ? this.spellIcon : HydromancyParticleHandler.RedBeam;
	}

	public int getColor() {
		// TODO Auto-generated method stub
		return 0xFFFFFF;
	}
	
	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
	
		this.ticksAlive++;
		
		if(this.ticksAlive > 600 || this.NumberOfProjectiles == 0)
		{
			this.setDead();
		}
		
		if(this.ProjectileCoolDown == 0)
		{
			
			
			
			this.NumberOfProjectiles--;
			this.ProjectileCoolDown = 60;
		}else
		{
			this.ProjectileCoolDown--;
		}
		
		
		
		super.onUpdate();
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		// TODO Auto-generated method stub
		
	}



}