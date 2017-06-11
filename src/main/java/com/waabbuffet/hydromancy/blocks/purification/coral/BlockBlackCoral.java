package com.waabbuffet.hydromancy.blocks.purification.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class BlockBlackCoral extends BlockCoral
{

	public BlockBlackCoral(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Block[] getFriendsList() {
		Block blocks[] = {HydromancyBlockHandler.coral_cyan, HydromancyBlockHandler.coral_yellow};
		return blocks;
	}

	@Override
	public boolean needsFriendToSurvive() {
		// TODO Auto-generated method stub
		return false;
	}

}
