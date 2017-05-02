package com.waabbuffet.hydromancy.packet.packets;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SSyncHydromancyPlayerProperties implements IMessage, IMessageHandler<SSyncHydromancyPlayerProperties, IMessage> {
	
	List<String> knownWords = new ArrayList<String>();
	
	List<String> textToTranslationA = new ArrayList<String>();
	List<Boolean> textToTranslationB = new ArrayList<Boolean>();
	
	List<String> researchStates = new ArrayList<String>(); //research + state
	
	String textToTranslation = null;
	EntityPlayer player;
	
	NBTTagCompound nbt;
	
	public SSyncHydromancyPlayerProperties() {}
	
	public SSyncHydromancyPlayerProperties(EntityPlayer player)
	{
		this.player = player;
		
		nbt = new NBTTagCompound();
		
		HydromancyPlayerProperties.get(player).saveNBTData(nbt);
	}
	
	@Override
	public IMessage onMessage(SSyncHydromancyPlayerProperties message, MessageContext ctx) {
		player = ctx.getServerHandler().playerEntity;
		HydromancyPlayerProperties.get(player).loadNBTData(message.nbt);
		
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
