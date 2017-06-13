package com.waabbuffet.hydromancy.tileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;

import com.waabbuffet.hydromancy.client.entity.ParticleAbsorbWater;
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
		if(this.worldObj != null)
		{
			this.worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);

			if(this.worldObj.isRemote)
			{
				Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleAbsorbWater(this.worldObj, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 0, 1, 0));
			}
		}
	}

	public void changePurity(int change)
	{
		setpurity(this.getpurity() + change);

		if(purity < 0)
			this.turnNormal();
	}
	public void turnNormal()
	{
		this.worldObj.setBlockState(this.getPos(), Blocks.WATER.getDefaultState());
		this.worldObj.setTileEntity(getPos(), null);
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
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) 
	{

		this.readFromNBT(pkt.getNbtCompound());
		super.onDataPacket(net, pkt);
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
		this.purity = nbt.getInteger("purity");
		super.readFromNBT(nbt);
	}

	@Override
	public void update() 
	{
		if(this.check_cd == 0)
		{
			if(!PurifiedWaterUtil.hasPurifyingSource(worldObj, getPos(), 5))
			{
				this.changePurity(-1);
			}
			this.check_cd = 10;
		}else
			this.check_cd--;
	}
}
