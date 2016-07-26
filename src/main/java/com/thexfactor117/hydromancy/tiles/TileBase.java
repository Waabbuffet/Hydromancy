package com.thexfactor117.hydromancy.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileBase extends TileEntity implements IInventory
{
	@Override
	public int getSizeInventory() 
	{
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) 
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {}

	@Override
	public String getInventoryName() 
	{
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) 
	{
		return false;
	}
}
