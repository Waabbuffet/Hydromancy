package com.waabbuffet.hydromancy.items.spells;

import com.waabbuffet.hydromancy.entity.spells.EntityMagicProjectile;
import com.waabbuffet.hydromancy.spells.WaterBolt;
import com.waabbuffet.hydromancy.spells.particles.HydromancyParticleHandler;
import com.waabbuffet.hydromancy.util.spellTypes.SpellData;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMagicSpellCaster extends Item{

	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
	
		//Should be moved to packet afterwards	
		
		
		if(!world.isRemote)
		{
			WaterBolt b = new WaterBolt(new SpellData(1, 1, 1, 1, 1,false));
			b.activateSpell(player, world);
		}
		
		return super.onItemRightClick(stack, world, player);
	}
	
	
	@Override
	public void registerIcons(IIconRegister Icon) {
		
		HydromancyParticleHandler.initSpellIcons(Icon);
		
		super.registerIcons(Icon);
	}
}
