package com.waabbuffet.hydromancy.packet.packets;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
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
		
		nbt = player.getCapability(HydromancyCapabilities.PLAYER_PROPERTIES, null).serializeNBT();
	}
	
	@Override
	public IMessage onMessage(final SSyncHydromancyPlayerProperties message, final MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(new Runnable()
		{
			@Override
			public void run() {
			
			player = ctx.getServerHandler().playerEntity;
			player.getCapability(HydromancyCapabilities.PLAYER_PROPERTIES, null).deserializeNBT(message.nbt);
			
			System.out.println("[SERVER RECIEVED]: " + player.getCapability(HydromancyCapabilities.PLAYER_PROPERTIES, null).getTextToTranslation());
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
