package com.waabbuffet.hydromancy.inventory.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTranslationTable extends Slot{

	public SlotTranslationTable(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
	
	@Override
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }

	
}
