package com.waabbuffet.hydromancy.blocks.purification.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBlueCoral extends BlockCoral 
{

	public BlockBlueCoral(String name) 
	{
		super(name);
	}

	@Override
	public Block[] getFriendsList() {
		
		Block blocks[] = {HydromancyBlockHandler.coral_purple, HydromancyBlockHandler.coral_green, HydromancyBlockHandler.coral_pink,HydromancyBlockHandler.coral_orange};
		return blocks;
	}

	@Override
	public boolean needsFriendToSurvive() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
