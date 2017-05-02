package com.waabbuffet.hydromancy.inventory.containers;

import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResearchNote extends Slot{
	public boolean visible = true;

	public SlotResearchNote(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
	
	@Override
    public boolean isItemValid(ItemStack item)
    {
		if(item.isItemEqual(new ItemStack(HydromancyItemsHandler.translatedPage)))
			return true;
        return false;
    }

	public void updateSlotPosition(int x, int y)
	{
		xDisplayPosition += x;
		yDisplayPosition += y;
	}
}
