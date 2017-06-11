package com.waabbuffet.hydromancy.tileEntity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import com.waabbuffet.hydromancy.util.PurifiedWaterUtil;

public class TileEntityPurifiedWater extends TileEntity implements ITickable
{
	int purity, check_cd;

	public int getpurity() {
		return purity;
	}

	public void setpurity(int purity) 
	{
		this.purity = purity;
		this.markDirty();
	}

	public void changePurity(int change)
	{

		purity = (this.getpurity() + change);

		if(purity < 0)
			this.turnNormal();
	}
	public void turnNormal()
	{
		this.worldObj.setBlockState(this.getPos(), Blocks.WATER.getDefaultState());
		this.worldObj.setTileEntity(getPos(), null);
	}
	
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() 
	{
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeToNBT(tagCompound);
		return new SPacketUpdateTileEntity(getPos(), 0, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		
		readFromNBT(pkt.getNbtCompound());
		super.onDataPacket(net, pkt);
		this.markDirty();
	}
	

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("purity", this.getpurity());
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		this.setpurity(nbt.getInteger("purity"));
		super.readFromNBT(nbt);
	}

	@Override
	public void update() 
	{
		if(this.check_cd == 0)
		{
			if(!PurifiedWaterUtil.hasPurifyingSource(worldObj, getPos(), 5))
			{

			}
			this.check_cd = 10;
		}else
			this.check_cd--;
	}
}
