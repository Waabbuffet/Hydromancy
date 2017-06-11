package com.waabbuffet.hydromancy.blocks.purification.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOrangeCoral extends BlockCoral{

	public BlockOrangeCoral(String name) {
		super(name);
		// TODO Auto-generated constructor stub
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
