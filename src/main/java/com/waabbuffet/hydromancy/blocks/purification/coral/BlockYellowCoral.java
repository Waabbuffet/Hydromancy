package com.waabbuffet.hydromancy.blocks.purification.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;

import net.minecraft.block.Block;

public class BlockYellowCoral extends BlockCoral
{

	public BlockYellowCoral(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	//Coral base 3
	
	@Override
	public Block[] getFriendsList() {
		Block blocks[] = {HydromancyBlockHandler.coral_black};
		return blocks;
	}

	@Override
	public boolean needsFriendToSurvive() {
		// TODO Auto-generated method stub
		return false;
	}
}
