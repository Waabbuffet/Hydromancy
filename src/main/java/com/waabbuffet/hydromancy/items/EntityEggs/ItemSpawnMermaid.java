package com.waabbuffet.hydromancy.items.EntityEggs;

import com.waabbuffet.hydromancy.entity.HydromancyEntityHandler;
import com.waabbuffet.hydromancy.entity.water.EntityMermaid;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSpawnMermaid extends ItemMonsterPlacer{


	@SideOnly(Side.CLIENT)
	private IIcon theIcon;
	protected int colorBase = 0x000000;
	protected int colorSpots = 0xFFFFFF;
	protected String entityToSpawnName = "";
	protected String entityToSpawnNameFull = Reference.MODID + "." +"EntityMermaid";
	protected EntityLiving entityToSpawn = null;

	public ItemSpawnMermaid() {
		// TODO Auto-generated constructor stub
	
	
	}


	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		//Add stack information here only give pages that the player has not discovered
		System.out.println("Here");
		spawnEntity(world, player.posX, player.posY, player.posZ);


		return super.onItemRightClick(stack, world, player);
	}

	public Entity spawnEntity(World parWorld, double parX, double parY, double parZ)
	{
		
		if (!parWorld.isRemote) // never spawn entity on client side
		{
			
			if (EntityList.stringToClassMapping.containsKey(entityToSpawnNameFull))
			{
			
				entityToSpawn = (EntityLiving) EntityList.createEntityByName(entityToSpawnNameFull, parWorld);
				entityToSpawn.setLocationAndAngles(parX, parY, parZ, 

						MathHelper.wrapAngleTo180_float(parWorld.rand.nextFloat()

								* 360.0F), 0.0F);
				
				System.out.println(entityToSpawn);
				parWorld.spawnEntityInWorld(entityToSpawn);
				entityToSpawn.playLivingSound();
			}
			else
			{
				
				
				//DEBUG
				System.out.println("Entity not found "+entityToSpawnName);
			}
		}

		return entityToSpawn;
	}


}
