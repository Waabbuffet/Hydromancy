package com.waabbuffet.hydromancy.inventory.containers;

import java.util.List;

import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.tileEntity.TileEntityTranslationTable;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
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
		this.slotList(research); //I have let it be the method although it is not needed anymore
	}
		
	public void slotList(boolean isTabResearch)
	{
			this.addSlotToContainer(new SlotItemHandler(TranslationTable.handler, 0, 11, 13)); //Fragment
			this.addSlotToContainer(new SlotItemHandler(TranslationTable.handler, 1, 11, 38)); //Stone
			this.addSlotToContainer(new SlotItemHandler(TranslationTable.handler, 2, 11, 63)); //Paper
			this.addSlotToContainer(new SlotTranslationTable(TranslationTable.handler, 3, 155, 38)); //output slot
			
			// Player Inventory, Slot 1-28, Slot IDs 1-28
			for (int i = 0; i < 3; i++) {
				for (int k = 0; k < 9; k++) {
					addSlotToContainer(new Slot(PlayerInv, k + i * 9 + 9, 8 + k * 18 + 3, 87 + i * 18));
				}
			}

			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(PlayerInv, j, 8 + j * 18 + 3, 145));
			}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		if(!playerIn.isSpectator())
			return true;
		return false;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot  < 4) {
				// From TE Inventory to Player Inventory
				if (!this.mergeItemStack(current, 4, this.inventorySlots.size(), true))
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
