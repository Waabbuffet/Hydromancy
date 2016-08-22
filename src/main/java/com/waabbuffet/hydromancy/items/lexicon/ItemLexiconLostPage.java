package com.waabbuffet.hydromancy.items.lexicon;

import java.util.List;
import java.util.Random;

import com.waabbuffet.hydromancy.util.Reference;
import com.waabbuffet.hydromancy.util.TranslationTablePage;
import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemLexiconLostPage extends Item {


	public ItemLexiconLostPage() {

	}



	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List information, boolean NoIdea) {

		//When the page numbers match...it will show the text in the gui

		if(stack.hasTagCompound())
		{ 
			information.add("Name: " + stack.getTagCompound().getString("PageName"));
			information.add("Category Number: " + stack.getTagCompound().getInteger("CateNumber") + "/10");
			information.add("Page Number: " + stack.getTagCompound().getInteger("PageNumber") + "/100");
		}

		super.addInformation(stack, player, information, NoIdea);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {


		//Add stack information here only give pages that the player has not discovered
		if(!stack.hasTagCompound())
		{ 
			Random rand = new Random();
			NBTTagCompound tag = new NBTTagCompound();

			int RandomCategoryNumber = rand.nextInt(TranslationTableUtils.Lexiconpages.size());
			int Page[] = TranslationTableUtils.getUnknownPageNumber(player, RandomCategoryNumber);
			String HardcodedText = TranslationTableUtils.getTextBasedOnPageNumber(Page[1], Page[0]);
			String Clue = TranslationTableUtils.getClueBasedOnPageNumber(Page[1], Page[0]);
			
			if(TranslationTableUtils.hasPageNumber(player, Page[1], Page[0]))
			{
				System.out.println(TranslationTableUtils.Lexiconpages.size());
				player.addChatMessage(new ChatComponentText("Looks like I know everything here..."));
				return super.onItemRightClick(stack, world, player);
			}
			
			tag.setInteger("PageNumber",  Page[1]); 
			tag.setInteger("CateNumber",  Page[0]); 
			tag.setString("Clue", Clue == null ? "Not available" : Clue);
			tag.setString("UndecipheredText", HardcodedText == null ? TranslationTableUtils.generateRandomText() : HardcodedText);
			tag.setString("PageName", TranslationTableUtils.getNameBasedOnPageNumber(Page[1], Page[0]));
			
			stack.setTagCompound(tag);
		}


		return super.onItemRightClick(stack, world, player);
	}




}
