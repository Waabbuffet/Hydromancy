package com.waabbuffet.hydromancy.util.spellTypes;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MagicSpellBase {


	private SpellData spellData;


	public MagicSpellBase(SpellData spellData) {
		// TODO Auto-generated constructor stub
		this.spellData = spellData;
	}

	protected void spawnProjectile() // uses default values
	{
		this.spawnProjectile(null, 0F, this.getSpellData().getPotency(), this.getSpellData().getProjectileNumber());
	}

	protected void spawnProjectile(ResourceLocation SpellIcon, float scale, double damage, double ProjectileNumber)
	{

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
