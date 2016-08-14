package com.waabbuffet.hydromancy.inventory.containers;

import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPurifier extends Container {


	private TileEntityPurifier Purifier;

	public ContainerPurifier(IInventory playerInv, TileEntityPurifier Purifier) {
		this.Purifier = Purifier;

		// Tile Entity, Slot 0, Slot IDs 0


		this.addSlotToContainer(new Slot(Purifier, 0, 52, 41)); //Fuel slot
		//87, 54 - > 52, 41


		// Player Inventory, Slot 1-28, Slot IDs 1-28
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(playerInv, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}

		}

		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(playerInv, j, 8 + j * 18, 142));
		}	
	}


	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {

		return this.Purifier.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		
		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < 1) {
				// From TE Inventory to Player Inventory
				if (!this.mergeItemStack(current, 1, this.inventorySlots.size(), true))
					return null;
				
				slot.onSlotChange(current, previous);
			} else {
				// From Player Inventory to TE Inventory
				if (!this.mergeItemStack(current, 0, 1, false))
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
