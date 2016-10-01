package com.waabbuffet.hydromancy.util.spellTypes;

import java.util.List;

import com.waabbuffet.hydromancy.entity.spells.EntityMagicPortal;
import com.waabbuffet.hydromancy.entity.spells.EntityMagicProjectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class MagicSpellBase {


	private SpellData spellData;


	public MagicSpellBase(SpellData spellData) {
		// TODO Auto-generated constructor stub
		this.spellData = spellData;
	}

	protected void spawnProjectile(EntityPlayer player, World world, boolean isPortal) // uses default values
	{
		this.spawnProjectile(player, world, null, 0F, this.getSpellData().getPotency(), this.getSpellData().getProjectileNumber(), isPortal);
	}

	protected void spawnProjectile(EntityPlayer player, World world, IIcon SpellIcon, float scale, double damage, double ProjectileNumber, boolean isPortal)
	{	
		Vec3 vec3 = player.getLookVec();

		double pitch = ((player.rotationPitch + 90) * Math.PI) / 180;
		double yaw  = ((player.rotationYaw + 90)  * Math.PI) / 180;

		double x = Math.sin(pitch) * Math.cos(yaw);
		double y = Math.sin(pitch) * Math.sin(yaw);
		double z = Math.cos(pitch);

	
		if(!isPortal)
		{
			EntityMagicProjectile entitylargefireball = new EntityMagicProjectile(world);
			entitylargefireball.setSpellIcon(SpellIcon);
	
			entitylargefireball.setVelocity(x, z, y);
	
			entitylargefireball.posX = player.posX + vec3.xCoord * 4;
			entitylargefireball.posY = player.posY + (double)(player.height / 2.0F) + 0.5D;
			entitylargefireball.posZ = player.posZ + vec3.zCoord * 4;
			
			world.spawnEntityInWorld(entitylargefireball);
		}else
		{
			EntityMagicPortal entitylargefireball = new EntityMagicPortal(world);
			entitylargefireball.setSpellIcon(SpellIcon);
			
			entitylargefireball.posX = player.posX + vec3.xCoord * 4;
			entitylargefireball.posY = player.posY + (double)(player.height / 2.0F) + 0.5D;
			entitylargefireball.posZ = player.posZ + vec3.zCoord * 4;
			
			world.spawnEntityInWorld(entitylargefireball);
		}

	}

	protected void spawnBeam() // uses default values
	{
		spawnBeam(null, 0,this.getSpellData().getPotency(), this.getSpellData().getProjectileNumber(), false);
	}



	/**
	 * @param SpellIcon - spell icon to change color and etc
	 * @param scale - how large the spell will render on the scale
	 * @param damage - how much damage the spell will do on contact
	 * @param ProjectileNumber - how many beams will spawn
	 * @param isCallDown - is from the player or like lightening effect
	 */

	protected void spawnBeam(ResourceLocation SpellIcon, float scale, double damage, double ProjectileNumber, boolean isCallDown)
	{

	}

	protected void applyPotionAffectToPlayer() //uses default values
	{
		applyPotionAffectToPlayer(null, this.getSpellData().getDuration(), this.getSpellData().getPotency());
	}
	/**
	 * @param effect - which potion id to use
	 * @param duration - how long the potion will last not needed for all potions
	 * @param potency - how strong the effect will be
	 */
	protected void applyPotionAffectToPlayer(PotionEffect effect, double duration, double potency)
	{

	}

	protected boolean hasSufficientPurifiedWater()
	{

		return false;
	}

	protected boolean consumePurifiedWater()
	{

		return false;
	}


	protected SpellData getSpellData() {
		return spellData;
	}


	protected void setSpellData(SpellData spellData) {
		this.spellData = spellData;
	}

	protected EntityLivingBase getNearestTarget(World world, EntityLivingBase caster)
	{

		List<EntityLivingBase> entities = world.getEntitiesWithinAABB(
				EntityLivingBase.class,
				AxisAlignedBB.getBoundingBox(
						caster.posX - 15,
						caster.posY - 15,
						caster.posZ - 15,
						caster.posX + 15,
						caster.posY + 15,
						caster.posZ + 15));

		EntityLivingBase closest = null;
		double curShortestDistance = 900;

		for (EntityLivingBase e : entities){
			if (e == caster)
				continue;

			double distance = caster.getDistanceSqToEntity(e);
			if (distance < curShortestDistance){
				curShortestDistance = distance;
				closest = e;
			}
		}

		if (closest != null){
			return closest;
		}
		
		return null;
	}
}
