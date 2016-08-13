package com.waabbuffet.hydromancy.packet.packets;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PlayerLexiconUpdate implements IMessage, IMessageHandler<PlayerLexiconUpdate, IMessage> {


	int category, page;
	String AddWord;
	int WhichOne;


	public PlayerLexiconUpdate(){ }


	public PlayerLexiconUpdate(String addWord)
	{
		this.WhichOne = 0;
		this.AddWord = addWord;
	}

	public PlayerLexiconUpdate(int category, int page)
	{
		this.WhichOne = 1;
		this.category = category;
		this.page = page;
	}




	@Override
	public IMessage onMessage(PlayerLexiconUpdate message, MessageContext ctx) {

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		World world = p.worldObj;

		
		if(message.WhichOne == 0)
		{
			
			HydromancyPlayerProperties.get(p).addWord(message.AddWord);
		}else if(message.WhichOne == 1)
		{
			HydromancyPlayerProperties.get(p).unlockPage(message.category, message.page, true);
		}


		return null;
	}



	@Override
	public void fromBytes(ByteBuf buf) {

		this.WhichOne = buf.readInt();

		if(this.WhichOne == 0)
		{
			this.AddWord = ByteBufUtils.readUTF8String(buf);
		}else if(WhichOne == 1)
		{
			this.category = buf.readInt();
			this.page = buf.readInt();
		}


	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(this.WhichOne);

		if(this.WhichOne == 0)
		{
			ByteBufUtils.writeUTF8String(buf, this.AddWord);
		}else if(this.WhichOne == 1)
		{
			buf.writeInt(this.category);
			buf.writeInt(this.page);
		}

	}


}
