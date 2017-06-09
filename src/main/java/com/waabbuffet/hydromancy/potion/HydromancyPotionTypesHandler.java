package com.waabbuffet.hydromancy.potion;

import javax.annotation.Nullable;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HydromancyPotionTypesHandler 
{

	public static PotionType whispering_thoughts;


	public static void registerPotions()
	{
		whispering_thoughts = createPotionType(new PotionEffect(HydromancyPotionHandler.whispering_thoughts, 3600));
	}

	private static PotionType createPotionType(PotionEffect potionEffect) {
		return createPotionType(potionEffect, null);
	}

	private static PotionType createPotionType(PotionEffect potionEffect, @Nullable String namePrefix) {
		final ResourceLocation potionName = potionEffect.getPotion().getRegistryName();

		final ResourceLocation potionTypeName;
		if (namePrefix != null) {
			potionTypeName = new ResourceLocation(potionName.getResourceDomain(), namePrefix + potionName.getResourcePath());
		} else {
			potionTypeName = potionName;
		}

		return GameRegistry.register(new PotionType(potionName.toString(), potionEffect).setRegistryName(potionTypeName));
	}
}
