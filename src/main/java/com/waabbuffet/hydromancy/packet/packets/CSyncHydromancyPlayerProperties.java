package com.waabbuffet.hydromancy.packet.packets;

import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CSyncHydromancyPlayerProperties implements IMessage, IMessageHandler<CSyncHydromancyPlayerProperties, IMessage>{
	NBTTagCompound nbt;
	EntityPlayer player;
	
	public CSyncHydromancyPlayerProperties() {}
	
	public CSyncHydromancyPlayerProperties(EntityPlayer player) {
		this.player = player;
		nbt = new NBTTagCompound();
		HydromancyPlayerProperties.get(player).saveNBTData(nbt);
	}
	
	@Override
	public IMessage onMessage(CSyncHydromancyPlayerProperties message, MessageContext ctx) {
		player = Minecraft.getMinecraft().thePlayer;
		HydromancyPlayerProperties.get(player).loadNBTData(message.nbt);
		//System.out.println(HydromancyPlayerProperties.get(player).getResearchStates().toString());
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
