package com.waabbuffet.hydromancy.packet.packets;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.client.gui.HydromancyGuiHandler;
import com.waabbuffet.hydromancy.inventory.containers.LexiconInventory;
import com.waabbuffet.hydromancy.inventory.containers.ContainerLexicon;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class SUpdateLexiconInventory implements IMessage, IMessageHandler<SUpdateLexiconInventory, IMessage>{

	NBTTagCompound nbt;
	
	private LexiconInventory inv;
	
	public SUpdateLexiconInventory() {}
	
	public SUpdateLexiconInventory(LexiconInventory inv) {
		nbt = new NBTTagCompound();
		
		inv.writeToNBT(nbt);
	}
	
	@Override
	public IMessage onMessage(SUpdateLexiconInventory message, MessageContext ctx) {
		inv = new LexiconInventory(ctx.getServerHandler().playerEntity.getHeldItem());
		inv.readFromNBT(message.nbt);
		//System.out.println("no fall");
		if(ctx.getServerHandler().playerEntity.openContainer != null && ctx.getServerHandler().playerEntity.openContainer instanceof ContainerLexicon)
		{
			((ContainerLexicon)ctx.getServerHandler().playerEntity.openContainer).redraw(inv);
			//System.out.println("no fall muhahaha");
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

}
