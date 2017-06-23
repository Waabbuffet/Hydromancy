package com.waabbuffet.hydromancy.inventory.containers;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.inventory.LexiconInventory;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SUpdateLexiconInventory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerLexicon extends Container{

	public LexiconInventory inventory;
	private EntityPlayer player;
	
	private List<SlotResearchNote> refSlot = new ArrayList<SlotResearchNote>();
	
	public ContainerLexicon(EntityPlayer player, LexiconInventory lInventory) {
		this.player = player;
		
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(player.inventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		// 28 - 36
		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(player.inventory, j, 8 + j * 18, 142));
		}
		
		redraw(lInventory);
	}

	public void redraw(LexiconInventory lInventory)
	{
		inventory = lInventory;
		
		//instead of clearing slots and drawing them all again, remove only item inventory
		for(int i = inventorySlots.size()-1;i>35;i--)
		{
			inventorySlots.remove(i);
			inventoryItemStacks.remove(i);
		}
		
		refSlot.clear();
		
		// Player Inventory, Slot 1-28, Slot IDs 1-28
			
		int slots = inventory.getSizeInventory();
		for(int i = 0; i < Math.ceil((float)slots/9); i++)
		{
			for(int k = 0; k < 9; k++)
			{
				if(i*9+k >= slots)
					break;
				refSlot.add(new SlotResearchNote(inventory, i*9+k, 8 + k*18, 8 + i*18));
				addSlotToContainer(refSlot.get(i*9+k));
			}
		}
		if(slots == 0)
		{
			addAdditionalAndUpdate();
		}
		
		//System.out.println(inventoryItemStacks.size());
		
		
		for(Object s : inventorySlots)
		{
			if(s instanceof SlotResearchNote)
				if(((SlotResearchNote) s).yDisplayPosition < 0 || ((SlotResearchNote) s).yDisplayPosition > 79)
				{
					((SlotResearchNote) s).visible = false;
				}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int fromSlot)
    {
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < 36) {
				if (!this.mergeItemStack(current, 36, this.inventorySlots.size(), true))
					return null;
				
				slot.onSlotChange(current, previous);
			} else {
				if (!this.mergeItemStack(current, 0, 36, false))
					return null;
			}

			if (current.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
				slot.onSlotChanged();

			if (current.stackSize == previous.stackSize)
				return null;
			slot.onPickupFromSlot(player, current);
		}
		return previous;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
    {
		if(slotId < inventorySlots.size()) {
			if (slotId >= 0 && getSlot(slotId) != null && getSlot(slotId).getStack() == player.getHeldItemMainhand()) {
				return null;
			}		
			return super.slotClick(slotId, dragType, clickTypeIn, player);
		}
		return null;
    }
	
	/** -1 for up, 1 for down */
	public void updateSlotPositions(byte direction)
	{
		if(direction == 0)
			return;

		int slots = inventory.getSizeInventory();
		for(int i = 0; i < slots; i++)
		{	
			refSlot.get(i).updateSlotPosition(0, direction >= 1 ? 18 : -18);
		}
		renewVisibility();
	}
	
	public void renewVisibility()
	{
		int slots = inventory.getSizeInventory();
		
		for(int i = 0; i < slots; i++)
		{	
			if(refSlot.get(i).yDisplayPosition < 0 || refSlot.get(i).yDisplayPosition > 79) //max y min y
				refSlot.get(i).visible = false;
			else
				refSlot.get(i).visible = true;
		}
	}

	public void removeAdditionalAndUpdate(Slot slot)
	{
		for(int i = slot.getSlotIndex();i<inventory.getSizeInventory()-1;i++)
		{
			inventory.setInventorySlotContents(i, inventory.getStackInSlot(i+1));
		}
		
		if(inventory.getSizeInventory()-1 > 0)
		{
			inventory.removeSlot(inventory.getSizeInventory()-1);
			HydromancyPacketHandler.INSTANCE.sendToServer(new SUpdateLexiconInventory(inventory));  //sends info about slot removal
			redraw(inventory); //client redraw is now necessary
		}

		/*	inform server about change of itemstack slot (and its possible change due to slot removal),
		  	thanks to automatic synchronization the change will be visible on client					 */
		HydromancyPacketHandler.INSTANCE.sendToServer(new SUpdateLexiconInventory(inventory));
	}

	public void addAdditionalAndUpdate() {
		if(inventory.getSizeInventory() == 0)
		{
			refSlot.add(new SlotResearchNote(inventory, 0, 8, 8));
		}
		else
		{
			refSlot.add(new SlotResearchNote(inventory, refSlot.get(refSlot.size()-1).getSlotIndex()+1, (refSlot.get(refSlot.size()-1).xDisplayPosition >= 8 + 8*18) ? 8 : refSlot.get(refSlot.size()-1).xDisplayPosition + 18, (refSlot.get(refSlot.size()-1).xDisplayPosition >= 8 + 8*18) ? refSlot.get(refSlot.size()-1).yDisplayPosition + 18 : refSlot.get(refSlot.size()-1).yDisplayPosition));
			if(refSlot.get(refSlot.size()-1) instanceof SlotResearchNote)
				if(((SlotResearchNote) refSlot.get(refSlot.size()-1)).yDisplayPosition < 0 || ((SlotResearchNote) refSlot.get(refSlot.size()-1)).yDisplayPosition > 79) //max y min y
					((SlotResearchNote) refSlot.get(refSlot.size()-1)).visible = false;
		}
		inventory.addSlot(refSlot.get(refSlot.size()-1));
		this.addSlotToContainer(refSlot.get(refSlot.size()-1));
		HydromancyPacketHandler.INSTANCE.sendToServer(new SUpdateLexiconInventory(inventory)); //adding needs to be handled immediately
	}
	
	@Override
	public Slot getSlot(int p_75139_1_)
    {
		if(p_75139_1_ < inventorySlots.size())
			return (Slot)this.inventorySlots.get(p_75139_1_);
		return null;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void putStacksInSlots(ItemStack[] p_75131_1_)
    {
        for (int i = 0; i < p_75131_1_.length; ++i)
        {
        	if(this.getSlot(i) != null)
        		this.getSlot(i).putStack(p_75131_1_[i]);
        }
    }
	
	@Override
	public void putStackInSlot(int p_75141_1_, ItemStack p_75141_2_)
    {
		if(getSlot(p_75141_1_) != null)
			this.getSlot(p_75141_1_).putStack(p_75141_2_);
    }
	
	public LexiconInventory getItemInventory()
	{
		return inventory;
	}
}
