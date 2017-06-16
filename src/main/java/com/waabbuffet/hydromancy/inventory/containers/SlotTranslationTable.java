package com.waabbuffet.hydromancy.inventory.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotTranslationTable extends SlotItemHandler{

	public SlotTranslationTable(ItemStackHandler handler, int id, int x, int y) {
		super(handler, id, x, y);
	}
	
	@Override
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }

	
}
