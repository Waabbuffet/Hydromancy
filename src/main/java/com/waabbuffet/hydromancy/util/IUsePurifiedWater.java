package com.waabbuffet.hydromancy.util;

import net.minecraft.item.ItemStack;

public interface IUsePurifiedWater 
{
	
	//how often the process could fail...lower end stuff higher rate then high stuff
	public float getFailChance();
	
	//total blocks in radius to increase
	public int getBlockRadius();

	//how often it checks to increase 
	public int getSpeedInTicks();

	//the flat amount that the water will be increased by or decrease if negative 
	public int getAmountIncrease();

	//when increasing how many blocks are effected
	public int getMaxBlocks();

	//items that are needed to carry out function
	public ItemStack[] cost_item();

	//items that are produced when function is carried out
	public ItemStack[] produce_item();

	//the maximum purity value that will increase the nearby water to
	public int getMaxPurityValue();

	//the minimum purity value that machine will operate 
	public int getMinPurityValue();
	
	public boolean isPreserver();
}
