package com.waabbuffet.hydromancy.tileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.util.IUsePurifiedWater;
import com.waabbuffet.hydromancy.util.PurifiedWaterUtil;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityPurifyWater extends TileEntity implements ITickable
{
	float fail_chance;
	int cooldown, speedInTicks, blockRadius, amountIncrease, maxBlocks, maxPurity, minPurity;
	boolean is_loaded = false, isPreserver;

	Random rand = new Random();

	public TileEntityPurifyWater() 
	{
		this.is_loaded = false;
	}

	@Override
	public final NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {

		return new SPacketUpdateTileEntity(this.getPos(), 1, this.getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {

		this.readFromNBT(pkt.getNbtCompound());
		super.onDataPacket(net, pkt);
	}

	public TileEntityPurifyWater(IUsePurifiedWater stats) 
	{
		this.speedInTicks = stats.getSpeedInTicks();
		this.blockRadius = stats.getBlockRadius();
		this.amountIncrease = stats.getAmountIncrease();
		this.maxBlocks = stats.getMaxBlocks();
		this.maxPurity = stats.getMaxPurityValue();
		this.minPurity = stats.getMinPurityValue();
		this.isPreserver = stats.isPreserver();
		this.fail_chance = stats.getFailChance();

		this.cooldown = 1000;
		this.is_loaded = true;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("cooldown", this.cooldown);
		compound.setInteger("speedticks", this.speedInTicks);
		compound.setInteger("blockradius", this.blockRadius);
		compound.setInteger("amountincrease", this.amountIncrease);
		compound.setInteger("maxBlocks", this.maxBlocks);
		compound.setInteger("maxPurity", this.maxPurity);
		compound.setInteger("minPurity", this.minPurity);
		compound.setBoolean("isPreserver", this.isPreserver);
		compound.setFloat("failchance", this.fail_chance);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		this.cooldown = compound.getInteger("cooldown");
		this.speedInTicks = compound.getInteger("speedticks");
		this.blockRadius = compound.getInteger("blockradius");
		this.amountIncrease = compound.getInteger("amountincrease");
		this.maxBlocks = compound.getInteger("maxBlocks");
		this.maxPurity = compound.getInteger("maxPurity");
		this.minPurity = compound.getInteger("minPurity");
		this.isPreserver = compound.getBoolean("isPreserver");
		this.fail_chance = compound.getFloat("failchance");
		this.is_loaded = true;

		super.readFromNBT(compound);
	}


	//doesnt change water blocks to purified water tho
	public void increaseNearbyWater()
	{
		int rand_number;
		List<TileEntityPurifiedWater> water = new ArrayList<TileEntityPurifiedWater>();
		List<BlockPos> water_blocks = PurifiedWaterUtil.getAllWithin(getPos(), this.blockRadius);

		if(!this.handleWaterBlocks(water_blocks))
		{
			for(BlockPos pos : water_blocks)
			{
				TileEntity possible = worldObj.getTileEntity(pos);

				if(possible != null && possible instanceof TileEntityPurifiedWater)
				{
					int purity = ((TileEntityPurifiedWater) possible).getpurity();

					if(purity >= this.minPurity && purity < this.maxPurity)
					{
						water.add((TileEntityPurifiedWater) possible);
					}
				}
			}
			if(!water.isEmpty())
			{
				for(int i = 0; i < this.maxBlocks; i ++)
				{
					rand_number = rand.nextInt(water.size());

					if((water.get(rand_number).getpurity() + this.amountIncrease) <= this.maxPurity)
					{
						water.get(rand_number).changePurity(this.amountIncrease);
					}else
					{
						water.get(rand_number).setpurity(this.maxPurity);
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
			if(this.is_loaded)
			{
				if(this.cooldown == 0)
				{
					if(rand.nextInt(100) > this.fail_chance)
					{
						this.increaseNearbyWater();
					}
					this.cooldown = this.speedInTicks;
				}else
					this.cooldown--;
			}
		}
	}
}
