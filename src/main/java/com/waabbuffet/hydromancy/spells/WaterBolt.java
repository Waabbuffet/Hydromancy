package com.waabbuffet.hydromancy.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.waabbuffet.hydromancy.entity.spells.EntityMagicProjectile;
import com.waabbuffet.hydromancy.spells.particles.HydromancyParticleHandler;
import com.waabbuffet.hydromancy.util.Reference;
import com.waabbuffet.hydromancy.util.spellTypes.IMagicSpell;
import com.waabbuffet.hydromancy.util.spellTypes.MagicSpellBase;
import com.waabbuffet.hydromancy.util.spellTypes.SpellData;

public class WaterBolt extends MagicSpellBase implements IMagicSpell {

	public WaterBolt(SpellData spellData) {
		super(spellData);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void activateSpell(EntityPlayer source, World world) {
		// TODO Auto-generated method stub
		spawnProjectile(source, world, HydromancyParticleHandler.waterBolt, 1, 1, 1, true);
		
	}
}
