package com.waabbuffet.hydromancy.tileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.util.IUsePurifiedWater;
import com.waabbuffet.hydromancy.util.PurifiedWaterUtil;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityPurifyWater extends TileEntity implements ITickable
{
	int cooldown;
	Random rand = new Random();
	IUsePurifiedWater stats;

	public TileEntityPurifyWater(IUsePurifiedWater stats) 
	{
		this.stats = stats;
	}
	

	//doesnt change water blocks to purified water tho
	public void increaseNearbyWater()
	{
		int rand_number;
		List<TileEntityPurifiedWater> water = new ArrayList<TileEntityPurifiedWater>();
		List<BlockPos> water_blocks = PurifiedWaterUtil.getAllWithin(getPos(), stats.getBlockRadius());

		if(!this.handleWaterBlocks(water_blocks))
		{
			for(BlockPos pos : water_blocks)
			{
				TileEntity possible = worldObj.getTileEntity(pos);

				if(possible != null && possible instanceof TileEntityPurifiedWater)
				{
					int purity = ((TileEntityPurifiedWater) possible).getpurity();

					if(purity >= stats.getMinPurityValue() && purity < this.stats.getMaxPurityValue())
					{
						water.add((TileEntityPurifiedWater) possible);
					}
				}
			}
			if(!water.isEmpty())
			{
				for(int i = 0; i < this.stats.getMaxBlocks(); i ++)
				{
					rand_number = rand.nextInt(water.size());

					if((water.get(rand_number).getpurity() + this.stats.getAmountIncrease()) <= this.stats.getMaxPurityValue())
					{
						System.out.println("Changed"  + water.get(rand_number).getpurity());
						water.get(rand_number).changePurity(this.stats.getAmountIncrease());
						System.out.println("Changed"  + water.get(rand_number).getpurity());
					}else
					{
						water.get(rand_number).setpurity(this.stats.getMaxPurityValue());
					}
				}
			}
		}
	}

	public boolean handleWaterBlocks(List<BlockPos> water_blocks)
	{
		for(BlockPos pos : water_blocks)
		{
			if(worldObj.getBlockState(pos).getBlock().equals(Blocks.WATER))
			{
				TileEntityPurifiedWater water = new TileEntityPurifiedWater();
				worldObj.setBlockState(pos, HydromancyBlockHandler.purified_water.getDefaultState());
				worldObj.setTileEntity(pos, water);
				return true;
			}
		}
		return false;
	}

	@Override
	public void update() 
	{
		if(!this.worldObj.isRemote)
		{
			if(stats != null)
			{
				if(stats.cost_item() == null)
				{
					if(this.cooldown == 0)
					{
						if(rand.nextInt(100) > stats.getFailChance())
						{
							this.increaseNearbyWater();
						}
						this.cooldown = stats.getSpeedInTicks();
					}else
						this.cooldown--;
				}
			}
		}
	}
}
