package com.waabbuffet.hydromancy.inventory.containers;

import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTranslationTable extends Container {

	private TileEntityTranslationTable TranslationTable;
	private IInventory PlayerInv;
	boolean research;

	public ContainerTranslationTable(IInventory playerInv, TileEntityTranslationTable TranslationTable) {
		this.TranslationTable = TranslationTable;
		this.PlayerInv = playerInv;

		research = this.TranslationTable.isTabResearch;
		this.slotList(research);
	}
		
	public void slotList(boolean isTabResearch)
	{
		
		if(isTabResearch == false){		
			this.addSlotToContainer(new Slot(TranslationTable, 0, 85, 10)); //Fuel slot
			this.addSlotToContainer(new Slot(TranslationTable, 1, 85, 35)); //Fuel slot
			this.addSlotToContainer(new Slot(TranslationTable, 2, 85, 60));
			
			// Player Inventory, Slot 1-28, Slot IDs 1-28
			for (int i = 0; i < 3; i++) {
				for (int k = 0; k < 9; k++) {
					addSlotToContainer(new Slot(PlayerInv, k + i * 9 + 9, 8 + k * 18 + 77, 84 + i * 18));
				}
			}
	
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(PlayerInv, j, 8 + j * 18 + 77, 142));
			}
		} else {
			this.inventorySlots.clear();
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.TranslationTable.isUseableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot  < 3) {
				// From TE Inventory to Player Inventory
				if (!this.mergeItemStack(current, 3, this.inventorySlots.size(), true))
					return null;
				
				slot.onSlotChange(current, previous);
			} else {
				// From Player Inventory to TE Inventory
				if (!this.mergeItemStack(current, 0, 3, false))
					return null;
			}

			if (current.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (current.stackSize == previous.stackSize)
				return null;
			slot.onPickupFromSlot(playerIn, current);
		}
		return previous;
	}
}
