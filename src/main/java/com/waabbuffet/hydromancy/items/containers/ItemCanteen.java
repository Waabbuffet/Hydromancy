package com.waabbuffet.hydromancy.items.containers;

import java.util.List;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.PlaceAirBlock;
import com.waabbuffet.hydromancy.packet.packets.PlaceBlock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCanteen extends Item {

	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List information, boolean NoIdea) {

		
		if(stack.hasTagCompound())
		{ 
			information.add("Water Amount: " + stack.getTagCompound().getInteger("WaterAmount") + " mB");
			information.add("Average Water Purity: " + stack.getTagCompound().getInteger("chicekn") + " %");
			
		}
				
		super.addInformation(stack, player, information, NoIdea);
	}
	
	
	
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		
		Block water = world.getBlock(posX, posY + 1, posZ);
		
		if(water.equals(HydromancyBlocksHandler.Block_Purified_Water))
		{
			if(stack.hasTagCompound())
			{
				if(stack.getTagCompound().getInteger("WaterAmount") < 4000)
				{
					stack.getTagCompound().setInteger("WaterAmount",stack.getTagCompound().getInteger("WaterAmount") + 1000);
					HydromancyPacketHandler.INSTANCE.sendToServer(new PlaceAirBlock(posX, posY + 1, posZ));
				}
				
			}else{
				HydromancyPacketHandler.INSTANCE.sendToServer(new PlaceAirBlock(posX, posY + 1, posZ));
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("WaterAmount", 1000);
				stack.setTagCompound(tag);
			}
		}
		
		
		return super.onItemUse(stack, player, world, posX, posY, posZ, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
	}
	
	
}
