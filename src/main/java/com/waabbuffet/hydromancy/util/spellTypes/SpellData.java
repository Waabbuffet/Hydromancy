package com.waabbuffet.hydromancy.util.spellTypes;

import java.util.List;

import net.minecraft.item.ItemStack;

public class SpellData {

	//What makes up a spell is the following data:
	/* Potency -> How strong the spell will be (Damage, healing, effect strength)
	 * Projectile Number - > If is type projective will increase how many bolts are shot
	 * Duration -> How long the spell will last for  (only applies to certain spells
	 * Magic Cost - > how much water it will consume per use/tick/etc
	 * range - > how far the spell will travel before stopping 
	 * Spell combination will determine the effect of a spell
	 */

	private double potency, duration, magicCost, projectileNumber, range;
	private boolean isPassive;

	List<ItemStack> ItemCost;

	public SpellData(double potency, double duration, double magicCost,
			double projectileNumber, double range, boolean isPassive, List<ItemStack> itemCost) {
	
		this.potency = potency;
		this.duration = duration;
		this.magicCost = magicCost;
		this.projectileNumber = projectileNumber;
		this.range = range;
		this.isPassive = isPassive;
		ItemCost = itemCost;
	}
	
	
	public SpellData(double potency, double duration, double magicCost,
			double projectileNumber, double range, boolean isPassive) {
	
		this.potency = potency;
		this.duration = duration;
		this.magicCost = magicCost;
		this.projectileNumber = projectileNumber;
		this.range = range;
		this.isPassive = isPassive;
	}

	public double getPotency() {
		return potency;
	}

	public void setPotency(double potency) {
		this.potency = potency;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getMagicCost() {
		return magicCost;
	}

	public void setMagicCost(double magicCost) {
		this.magicCost = magicCost;
	}

	public double getProjectileNumber() {
		return projectileNumber;
	}

	public void setProjectileNumber(double projectileNumber) {
		this.projectileNumber = projectileNumber;
	}

	public boolean isPassive() {
		return isPassive;
	}

	public void setPassive(boolean isPassive) {
		this.isPassive = isPassive;
	}

	public List<ItemStack> getItemCost() {
		return ItemCost;
	}

	public void setItemCost(List<ItemStack> itemCost) {
		ItemCost = itemCost;
	}	
	
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	
}
