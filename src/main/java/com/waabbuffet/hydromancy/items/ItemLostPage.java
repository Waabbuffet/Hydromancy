package com.waabbuffet.hydromancy.items;

import java.awt.Color;
import java.util.List;

import com.waabbuffet.hydromancy.lexicon.LexiconPageHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionHandler;
import com.waabbuffet.hydromancy.util.EnumCategoryType;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemLostPage extends Item 
{
	public ItemLostPage(String name) 
	{
		this.setCreativeTab(CreativeTabs.MISC);
		setUnlocalizedName(name);
		setRegistryName(name);

		this.setHasSubtypes(true);
		GameRegistry.register(this);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {

		EnumCategoryType cate = EnumCategoryType.getEnumFromID(stack.getItemDamage());

		tooltip.add("Page Type: " + cate.toString());

		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().hasKey(Reference.lost_page_name_key))
				tooltip.add("Page Name: " + stack.getTagCompound().getString(Reference.lost_page_name_key));
		}

		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	public int getRBG(int id)
	{
		return EnumCategoryType.getEnumFromID(id).getRBG();
	}


	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> itemList) 
	{
		for(EnumCategoryType type : EnumCategoryType.values())
		{
			itemList.add(new ItemStack(item, 1, type.getID()));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) 
	{
		if(!worldIn.isRemote)
		{
			if(playerIn.getActivePotionEffect(HydromancyPotionHandler.whispering_thoughts) != null)
			{
				System.out.println("Potion: " + itemStackIn.getItemDamage());

				if(hand.equals(EnumHand.MAIN_HAND))
				{
					playerIn.removePotionEffect(HydromancyPotionHandler.whispering_thoughts);
					if(!itemStackIn.hasTagCompound())
					{
						NBTTagCompound tag = new NBTTagCompound();
						itemStackIn.setTagCompound(tag);
					}
					String name = LexiconPageHandler.getRandomPage(playerIn, itemStackIn.getItemDamage());
					
					if(name != null)
						itemStackIn.getTagCompound().setString(Reference.lost_page_name_key, name);
					else
					{
						if(!worldIn.isRemote)
							playerIn.addChatComponentMessage(new TextComponentString("Hmm I cant hear anything..."));
					}
				}
			}
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

}
