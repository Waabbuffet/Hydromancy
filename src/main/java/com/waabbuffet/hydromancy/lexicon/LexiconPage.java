package com.waabbuffet.hydromancy.lexicon;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

public class LexiconPage 
{
	int row, column;
	String name, description, parent;
	ItemStack stack;
	
	public LexiconPage(String name, String description, @Nullable String parent, int row, int column, ItemStack stack) {
	
		this.name = name;
		this.description = description;
		this.parent = parent;
		this.row = row;
		this.column = column;
		this.stack = stack;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getParent() {
		return parent;
	}
	
	public int getRow() {
		return row;
	}
	
	public ItemStack getStack() {
		return stack;
	}

	public static class PageInfo
	{
	
		public static String coral_description = "Hmm coral seem to have a mysterious property when around normal water";
		
		public static String canteen_description = "I have finally found a way to pick up some of this new water, too bad I can only hold 5 buckets worth";
		
		
	}
}
