package com.waabbuffet.hydromancy.tileentity.lexicon;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.UpdateFluidPurity;
import com.waabbuffet.hydromancy.util.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityTranslationTable extends TileEntity implements IInventory
{
	public ItemStack[] inventory;
	String ChosenWords;
	public float rotation;
	public boolean isTabResearch = false;
	
	public TileEntityTranslationTable() {
		inventory = new ItemStack[this.getSizeInventory()];
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


	public String getChosenWords() {
		return ChosenWords;
	}
	
	public void setChosenWords(String chosenWords) {
		ChosenWords = chosenWords;
	}
	

	@Override
	public void writeToNBT(NBTTagCompound nbt) {


		if(this.ChosenWords != null)
			nbt.setString("ChosenWords", this.ChosenWords);

		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		nbt.setTag("Items", list);

		super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {


		this.setChosenWords(nbt.getString("ChosenWords"));

		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot") & 255;
			this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
		}

		super.readFromNBT(nbt);
	}

	@Override
	public void updateEntity() {
		int blockMeta = this.getBlockMetadata();
		switch(blockMeta){
			case 3:
				//south
				rotation = 180f;
				break;
			case 4:
				//west
				rotation = 90f;
				break;				
			case 5:
				//east
				rotation = 270f;
				break;		
			case 2:
			default:
				break;
		}			
	}


	public boolean hasPaper()
	{
		if(getStackInSlot(0) != null)		
		{
			if(getStackInSlot(0).isItemEqual(new ItemStack(HydromancyItemsHandler.Lost_Page)))
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else {	
			return false;
		}
	}
	public boolean hasStone()
	{
		if(getStackInSlot(1) != null)		
		{
			if(getStackInSlot(1).isItemEqual(new ItemStack(HydromancyItemsHandler.deciphering_Stone)))
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else {	
			return false;
		}
	}
	
	public boolean hasMatchingTranslationStone()
	{
		if(this.getStackInSlot(1) != null)
		{
			
			if(this.getStackInSlot(0) != null)
			{
				
				if(this.getStackInSlot(1).isItemEqual(new ItemStack(HydromancyItemsHandler.deciphering_Stone)))
				{
					
					if(this.getStackInSlot(1).getTagCompound().getInteger("PageNumber") == this.getStackInSlot(0).getTagCompound().getInteger("PageNumber"))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	
	@Override
	public int getSizeInventory() {
		//change to be dependent on what tier the building is
		return 3;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return null;
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}



	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		this.inventory[index] = stack;
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64;
	}


	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == 0 && ItemStack.areItemStacksEqual(stack, new ItemStack(HydromancyItemsHandler.Lost_Page))) 
		{
			return true;
		}
		else if(index == 1 && ItemStack.areItemStacksEqual(stack, new ItemStack(HydromancyItemsHandler.deciphering_Stone)))
		{
			return true;
		}
		else if(index == 2 && ItemStack.areItemStacksEqual(stack, new ItemStack(Items.paper)))
		{
			return true;
		}
		else {
			return false;
		}
	}


	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}


	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}


}