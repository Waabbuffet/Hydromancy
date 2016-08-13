package com.waabbuffet.hydromancy.items.lexicon;

import java.util.List;

import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemLexiconLostPage extends Item {

	
	
	/* How to draw the enchantment text on the screen! 
		String s1 = "";
		FontRenderer fontrenderer = this.mc.standardGalacticFontRenderer;
		int k1 = 6839882;
		fontrenderer.drawSplitString("the elder scrolls klaatu",  62, 16 + 19 - 50, 104, (k1 & 16711422) >> 1);

	 */
		
	
	public ItemLexiconLostPage() {
		
	}
	
	

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List information, boolean NoIdea) {
		
		//When the page numbers match...it will show the text in the gui
		
		if(stack.hasTagCompound())
		{ 
			information.add("Page Number: " + stack.getTagCompound().getInteger("Page Number") + "/100");
		}
				
		super.addInformation(stack, player, information, NoIdea);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
	
		
		//Add stack information here only give pages that the player has not discovered
		if(!stack.hasTagCompound())
		{
		
			NBTTagCompound tag = new NBTTagCompound();
			
			int textNumber = TranslationTableUtils.getUnknownPageNumber(player);
			
			tag.setInteger("PageNumber",  10); 
			tag.setString("Clue", TranslationTableUtils.getClueBasedOnPageNumber(textNumber));
			tag.setString("UndecipheredText", TranslationTableUtils.generateRandomText());
			
			stack.setTagCompound(tag);
		}
		
		
		return super.onItemRightClick(stack, world, player);
	}
	
	
	
	
}
