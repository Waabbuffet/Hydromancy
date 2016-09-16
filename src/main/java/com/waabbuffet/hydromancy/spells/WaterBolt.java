package com.waabbuffet.hydromancy.spells;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.waabbuffet.hydromancy.entity.spells.EntityMagicProjectile;
import com.waabbuffet.hydromancy.util.spellTypes.IMagicSpell;
import com.waabbuffet.hydromancy.util.spellTypes.MagicSpellBase;
import com.waabbuffet.hydromancy.util.spellTypes.SpellData;

public class WaterBolt extends MagicSpellBase implements IMagicSpell {

	public WaterBolt(SpellData spellData) {
		super(spellData);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void activateSpell(EntityPlayer player, World world) {
		// TODO Auto-generated method stub

		Vec3 vec3 = player.getLookVec();

		double pitch = ((player.rotationPitch + 90) * Math.PI) / 180;
		double yaw  = ((player.rotationYaw + 90)  * Math.PI) / 180;

		double x = Math.sin(pitch) * Math.cos(yaw);
		double y = Math.sin(pitch) * Math.sin(yaw);
		double z = Math.cos(pitch);


		EntityMagicProjectile entitylargefireball = new EntityMagicProjectile(world);

		entitylargefireball.setVelocity(x, z, y);

		entitylargefireball.posX = player.posX + vec3.xCoord * 4;
		entitylargefireball.posY = player.posY + (double)(player.height / 2.0F) + 0.5D;
		entitylargefireball.posZ = player.posZ + vec3.zCoord * 4;
		
		world.spawnEntityInWorld(entitylargefireball);

	}
}
