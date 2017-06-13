package com.waabbuffet.hydromancy.items;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.PacketSyncLexicon;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemLexicon extends Item {

	public ItemLexicon(String name) 
	{
		this.setCreativeTab(CreativeTabs.MISC);
		setUnlocalizedName(name);
		setRegistryName(name);

		this.setHasSubtypes(true);
		GameRegistry.register(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		
		if(hand.equals(EnumHand.MAIN_HAND))
		{
			if(!worldIn.isRemote)
			{
				HydromancyPacketHandler.INSTANCE.sendTo(new PacketSyncLexicon(playerIn), (EntityPlayerMP) playerIn);
			}
		}
		
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	
}
