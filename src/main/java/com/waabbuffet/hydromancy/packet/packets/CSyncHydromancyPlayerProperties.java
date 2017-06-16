package com.waabbuffet.hydromancy.packet.packets;

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;

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
		
		nbt = player.getCapability(HydromancyCapabilities.PLAYER_PROPERTIES, null).serializeNBT();
	}
	
	@Override
	public IMessage onMessage(CSyncHydromancyPlayerProperties message, MessageContext ctx) {
		player = Minecraft.getMinecraft().thePlayer;
		player.getCapability(HydromancyCapabilities.PLAYER_PROPERTIES, null).deserializeNBT(message.nbt);
		
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
