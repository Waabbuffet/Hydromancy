package com.waabbuffet.hydromancy.packet.packets;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.client.gui.HydromancyGuiHandler;
import com.waabbuffet.hydromancy.inventory.containers.ContainerLexicon;
import com.waabbuffet.hydromancy.inventory.LexiconInventory;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SUpdateLexiconInventory implements IMessage, IMessageHandler<SUpdateLexiconInventory, IMessage>{

	NBTTagCompound nbt;
	
	private LexiconInventory inv;
	
	public SUpdateLexiconInventory() {}
	
	public SUpdateLexiconInventory(LexiconInventory inventory) {
		nbt = new NBTTagCompound();
		inventory.writeToNBT(nbt);
	}
	
	@Override
	public IMessage onMessage(final SUpdateLexiconInventory message, final MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(new Runnable()
		{
			@Override
			public void run() {
				inv = new LexiconInventory(ctx.getServerHandler().playerEntity.getHeldItemMainhand());
				inv.readFromNBT(message.nbt);
				if(ctx.getServerHandler().playerEntity.openContainer != null && ctx.getServerHandler().playerEntity.openContainer instanceof ContainerLexicon)
				{
					((ContainerLexicon)ctx.getServerHandler().playerEntity.openContainer).redraw(inv);
					//System.out.println("no fall muhahaha");
				}
			}
		});
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
