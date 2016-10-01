package com.waabbuffet.hydromancy.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.waabbuffet.hydromancy.spells.particles.HydromancyParticleHandler;
import com.waabbuffet.hydromancy.util.spellTypes.IMagicSpell;
import com.waabbuffet.hydromancy.util.spellTypes.MagicSpellBase;
import com.waabbuffet.hydromancy.util.spellTypes.SpellData;

public class MagicPortal extends MagicSpellBase implements IMagicSpell {

	public MagicPortal(SpellData spellData) {
		super(spellData);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void activateSpell(EntityPlayer source, World world) {
		// TODO Auto-generated method stub
		spawnProjectile(source, world, HydromancyParticleHandler.waterBolt, 1, 1, 1, false);
		
	}
}

