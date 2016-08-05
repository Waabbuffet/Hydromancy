package com.waabbuffet.hydromancy.tileentity.generation;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPurifiedWater extends TileEntity{

	int Purity;
	
	
	public TileEntityPurifiedWater() {
	
	}
	
	@Override
	public Packet getDescriptionPacket() {
		//Debug
		//	System.out.println("[DEBUG]:Server sent tile sync packet");

		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		//Debug
		//	System.out.println("[DEBUG]:Client recived tile sync packet");

		readFromNBT(pkt.func_148857_g());
		worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.markDirty();
	}

	
	
	public int getPurity() {
		return Purity;
	}
	
	public void setPurity(int purity) {
		Purity = purity;
	}
	
	public void increasePurity(int change)
	{
		if((this.getPurity() + change) < 100)
			Purity = (this.getPurity() + change);
		else
			Purity = 100;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
		nbt.setInteger("purity", this.getPurity());
		
		
		super.writeToNBT(nbt);
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		this.setPurity(nbt.getInteger("purity"));
		
		
		super.readFromNBT(nbt);
	}
	
	
}
