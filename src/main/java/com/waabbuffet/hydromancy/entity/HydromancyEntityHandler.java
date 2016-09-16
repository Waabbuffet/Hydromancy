package com.waabbuffet.hydromancy.entity;

import java.util.Random;

import net.minecraft.entity.EntityList;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.client.models.entity.ModelMermaid;
import com.waabbuffet.hydromancy.client.renderer.entity.RenderMermaid;
import com.waabbuffet.hydromancy.client.renderer.entity.spells.RenderMagicProjectile;
import com.waabbuffet.hydromancy.entity.spells.EntityMagicProjectile;
import com.waabbuffet.hydromancy.entity.water.EntityMermaid;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class HydromancyEntityHandler {
	
	static int EntityID = 0;
/*
	public static void RegisterEntity(Class EntityClass, String Name ){
		int EntityID = EntityRegistry.findGlobalUniqueEntityId();
		
		
		long x = Name.hashCode();
		Random random = new Random(x);
		int MainColour = random.nextInt() * 16777215;
		int SubColour = random.nextInt() * 16777215;
		
		EntityRegistry.registerGlobalEntityID(EntityClass, Name, EntityID);
		EntityRegistry.registerModEntity(EntityClass, Name, EntityID, Hydromancy.instance, 80, 3, true);
		EntityList.entityEggs.put(Integer.valueOf(EntityID), new EntityList.EntityEggInfo(EntityID, MainColour, SubColour));
	}
	*/
	
	public static void RegisterEntity(Class EntityClass, String Name ){

		EntityID++;
		EntityRegistry.registerModEntity(EntityClass, Name, EntityID, Hydromancy.instance, 80, 3, true);
		

	}


	public static void InitializeEntities()
	{
		RegisterEntity(EntityMermaid.class, "EntityMermaid");
		RegisterEntity(EntityMagicProjectile.class, "EntityMagicSpell");
	}
	
	
	public static void initRenders()
	{
	    RenderingRegistry.registerEntityRenderingHandler(EntityMermaid.class, new RenderMermaid(new ModelMermaid(), 0.3F));
	    RenderingRegistry.registerEntityRenderingHandler(EntityMagicProjectile.class, new RenderMagicProjectile());
	    
	}
	
}
