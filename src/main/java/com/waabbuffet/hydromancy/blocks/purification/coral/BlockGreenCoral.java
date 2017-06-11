package com.waabbuffet.hydromancy.blocks.purification.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGreenCoral extends BlockCoral{

	public BlockGreenCoral(String name) 
	{
		super(name);
		
	}
	
	@Override
	public Block[] getFriendsList() {
		Block blocks[] = {HydromancyBlockHandler.coral_blue};
		return blocks;
	}

	@Override
	public boolean needsFriendToSurvive() {
		// TODO Auto-generated method stub
		return false;
	}

}
