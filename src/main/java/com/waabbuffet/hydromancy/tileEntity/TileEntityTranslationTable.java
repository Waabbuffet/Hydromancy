package com.waabbuffet.hydromancy.tileEntity;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.TranslationTableResearch;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;

import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
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
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityTranslationTable extends TileEntity implements ICapabilityProvider
{
	//public ItemStack[] inventory;
	public ItemStackHandler handler;
	String ChosenWords;
	public boolean isTabResearch = false;
	public boolean researchInProgress = false;
	public boolean minigameInitialized;
	public TranslationTableResearch activeResearch;
	
	public TileEntityTranslationTable() {
		//inventory = new ItemStack[this.getSizeInventory()];
		handler = new ItemStackHandler(4);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(pos,metadata,nbt);
	}
	
	@Override
	public NBTTagCompound getUpdateTag()
	{
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound nbt = new NBTTagCompound();
		return writeToNBT(nbt);
	}


	public String getChosenWords() {
		return ChosenWords;
	}
	
	public void setChosenWords(String chosenWords) {
		ChosenWords = chosenWords;
	}
	
	public void setActiveResearch(TranslationTableResearch research)
	{
		this.activeResearch = research;
	}
	

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("Items", handler.serializeNBT());
		nbt.setBoolean("tabResearch", this.isTabResearch);
		nbt.setBoolean("inp", this.researchInProgress);
		
		/*if (this.hasCustomName()) {
	        nbt.setString("CustomName", this.getName());
	    }*/
		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		//this.setChosenWords(nbt.getString("ChosenWords"));
		this.isTabResearch = nbt.getBoolean("tabResearch");
		this.researchInProgress = nbt.getBoolean("inp");

		handler.deserializeNBT(nbt.getCompoundTag("Items"));
		this.markDirty();
		
	}

	public ItemStack decrStackSize(int index, int count) {
		if (handler.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (handler.getStackInSlot(index).stackSize <= count) {
				itemstack = handler.getStackInSlot(index);
				setInventorySlotContents(index, null);
				markDirty();
				return itemstack;
			} else {
				itemstack = handler.getStackInSlot(index).splitStack(count);

				if (handler.getStackInSlot(index).stackSize <= 0) {
					setInventorySlotContents(index, null);
				} else {
					//Just to show that changes happened
					setInventorySlotContents(index, handler.getStackInSlot(index));
				}

				markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}
	
	public void setInventorySlotContents(int index, ItemStack stack) {
		
		if (index < 0 || index >= handler.getSlots())
			return;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		handler.setStackInSlot(index, stack);
		markDirty();
	}
	
	public int getInventoryStackLimit()
    {
        return 64;
    }
	
	public boolean hasPaper()
	{
		if(handler.getStackInSlot(0) != null)		
		{
			if(handler.getStackInSlot(0).isItemEqual(new ItemStack(HydromancyItemHandler.lostFragment)))
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
		if(handler.getStackInSlot(1) != null)		
		{
			if(handler.getStackInSlot(1).isItemEqual(new ItemStack(HydromancyItemHandler.decipheringStone)))
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
		if(handler.getStackInSlot(1) != null)
		{
			
			if(handler.getStackInSlot(0) != null)
			{
				
				if(handler.getStackInSlot(1).isItemEqual(new ItemStack(HydromancyItemHandler.decipheringStone)))
				{
					
					if(handler.getStackInSlot(1).getTagCompound().getInteger("PageNumber") == handler.getStackInSlot(0).getTagCompound().getInteger("PageNumber"))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	
	public int getStackSize(int index)
	{
		if(handler.getStackInSlot(index) != null)
			return handler.getStackInSlot(index).stackSize;
		return 0;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T)handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
}