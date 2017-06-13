package com.waabbuffet.hydromancy.tileEntity.consume;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.tileEntity.TileEntityConsumeWater;
import com.waabbuffet.hydromancy.util.IUsePurifiedWater;

public class TileEntityObelisk extends TileEntityConsumeWater 
{	
	boolean isOn = false;
	boolean isMasterBlock = false;

	public TileEntityObelisk() 
	{

	}

	public TileEntityObelisk(IUsePurifiedWater water) 
	{
		super(water);
	}

	@Override
	public void applyEffect() 
	{
		System.out.println("I am consuming!!");
		this.isOn = true;
	}

	@Override
	public boolean shouldActivate() 
	{
		return this.isMasterBlock && isOn && !worldObj.isBlockPowered(getPos());
	}

	@Override
	public void update() 
	{
		super.update();

		int amount = 4;
		for(int y = 1; y < 5; y ++)
		{
			if(this.worldObj.getBlockState(getPos().up(y)).getBlock().equals(HydromancyBlockHandler.block_obelisk))
			{
				amount--;
			}
		}
		if(amount <= 0)
		{
			this.isMasterBlock = true;
		}
	}

	@Override
	public void applyNegativeEffect() 
	{
		System.out.println("Uh");
		this.isOn = false;
	}

	public void turnOn()
	{
		if(this.isMasterBlock && !worldObj.isBlockPowered(getPos()))
			this.isOn = true;
	}
}
