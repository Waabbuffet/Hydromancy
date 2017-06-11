package com.waabbuffet.hydromancy.blocks.purification.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;

import net.minecraft.block.Block;

public class BlockPinkCoral extends BlockCoral
{

	public BlockPinkCoral(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	//coral4
	
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
