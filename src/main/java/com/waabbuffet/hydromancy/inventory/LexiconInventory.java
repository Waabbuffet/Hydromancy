package com.waabbuffet.hydromancy.inventory;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SUpdateLexiconInventory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

/**
 * There is a rumor that IInventory isn't modern anymore, but since minecraft still uses this interfac,</br>
 * I think that there is no reason to change it.
 * */
public class LexiconInventory implements IInventory{
	private ItemStack invStack;
	private List<ItemStack> inventory = new ArrayList<ItemStack>();
	
	public LexiconInventory(ItemStack stack)
	{
		invStack = stack;
		
		if(!stack.hasTagCompound())
		{
			//System.out.println("this has no compound");
			stack.setTagCompound(new NBTTagCompound());
		}
		
		//inventory = new ItemStack[stack.getTagCompound().getInteger("numberOfSlots")+1];
		readFromNBT(stack.getTagCompound());
	}
	
	public void addSlot(Slot slot) {
		inventory.add(slot.getStack());
	}
	
	public void removeSlot(int slotNumber) {
		inventory.remove(slotNumber);
	}

	@Override
	public int getSizeInventory() {
		return inventory.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if(inventory.size() > slot)
			return inventory.get(slot);
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack stack = getStackInSlot(slot);
		if(stack != null)
		{
			if(stack.stackSize > count)
			{
				stack = stack.splitStack(count);
				// Don't forget this line or your inventory will not be saved!
				markDirty();
			}
			else
			{
				// this method also calls onInventoryChanged, so we don't need to call it again
				setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {

		//System.out.println("stack id: " + slot + "stack: " + stack);
		/*if(slot >= this.getSizeInventory())
			slot = this.getSizeInventory()-1;*/
		
		if (slot < 0 || slot >= inventory.size())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;
			
		inventory.set(slot, stack);
		
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {

		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0) {
				inventory.set(i, null);
			}
		}
		
		// This line here does the work:		
		writeToNBT(invStack.getTagCompound());
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(ItemStack.areItemStacksEqual(stack, new ItemStack(HydromancyItemHandler.translatedPage))) 
		{
			return true;
		}
		return false;
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		invStack.setTagCompound(compound);
		NBTTagList items = compound.getTagList("LexiconInventory", Constants.NBT.TAG_COMPOUND);

		int size = compound.getInteger("size");
		for(int k = getSizeInventory(); k < size; k++)
		{
			inventory.add(k, null);
		}
		for (int i = 0; i < items.tagCount(); ++i)
		{
			NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
			int slot = item.getInteger("Slot");
			
			// Just double-checking that the saved slot index is within our inventory array bounds
			if (slot >= 0 && slot < size) {
				inventory.set(slot, ItemStack.loadItemStackFromNBT(item));
			}	
		}
	}

	public void writeToNBT(NBTTagCompound tagcompound)
	{
		// Create a new NBT Tag List to store itemstacks as NBT Tags
		NBTTagList items = new NBTTagList();
		
		//System.out.println("inv size (write): " + getSizeInventory());

		for (int i = 0; i < getSizeInventory(); ++i)
		{
			// Only write stacks that contain items
			if (getStackInSlot(i) != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				getStackInSlot(i).writeToNBT(item);

				items.appendTag(item);
			}
		}
		
		// Add the TagList to the ItemStack's Tag Compound with the name "ItemLexicon"
		tagcompound.setTag("LexiconInventory", items);
		tagcompound.setInteger("size", getSizeInventory());
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {}
}
