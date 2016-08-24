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
	

	public ContainerTranslationTable(IInventory playerInv, TileEntityTranslationTable TranslationTable) {
		this.TranslationTable = TranslationTable;
		this.PlayerInv = playerInv;

		boolean hasPaper = this.TranslationTable.hasPaper();
		this.slotList(hasPaper);
		
		
		
		// Player Inventory, Slot 1-28, Slot IDs 1-28
		
	}
	
	public void slotList(boolean hasPaper)
	{
		this.inventorySlots.clear();
		
		this.addSlotToContainer(new Slot(TranslationTable, 0, (hasPaper ?  105 : 20), 22)); 
		this.addSlotToContainer(new Slot(TranslationTable, 1, (hasPaper ?  105 : 20), 51)); 
		
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(PlayerInv, k + i * 9 + 9, 8 + k * 18 + (hasPaper ?  85 : 0), 84 + i * 18));
			}
		}

		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(PlayerInv, j, 8 + j * 18 + (hasPaper ?  85 : 0), 142));
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

			if (fromSlot  > 0) {
				// From TE Inventory to Player Inventory
				if (!this.mergeItemStack(current, 1, 28, true))
					return null;
			} else {
				// From Player Inventory to TE Inventory
				if (!this.mergeItemStack(current, 0, 0, false))
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
