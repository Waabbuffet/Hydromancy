package com.waabbuffet.hydromancy.packet.packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.blocks.generation.BlockPurifiedWater;
import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifiedWater;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SyncLexicon implements IMessage, IMessageHandler<SyncLexicon, IMessage> {

	List<String> knownWords = new ArrayList<String>();

	boolean[][] lexiconPages  = new boolean[10][50]; //categories, each category can have up to 50 pages



	public SyncLexicon(){ }


	public SyncLexicon(boolean[][] LexiconPages, List<String> KnownWords)
	{
		knownWords = KnownWords;
		lexiconPages = LexiconPages;
	}




	@Override
	public IMessage onMessage(SyncLexicon message, MessageContext ctx) {

		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		World world = p.worldObj;
		
		HydromancyPlayerProperties.get(p).setKnownWords(message.knownWords);
		HydromancyPlayerProperties.get(p).setLexiconPages(message.lexiconPages);		

		return null;
	}



	@Override
	public void fromBytes(ByteBuf buf) {

		NBTTagCompound nbt = ByteBufUtils.readTag(buf);
		
		for(int i = 0; i < this.lexiconPages.length; i ++)
		{
			for(int j = 0; j < this.lexiconPages[0].length; j ++)
			{
				this.lexiconPages[i][j] = nbt.getBoolean("Category" + i + "Page" + j);
			}
		}
		
		for(int k = 0; k < nbt.getInteger("KnownWordsArray"); k++)
		{
			this.knownWords.add(nbt.getString("KnownWords"+k)); 
		}

	}

	@Override
	public void toBytes(ByteBuf buf) {

		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("KnownWordsArray", this.knownWords.size());

		for(int i = 0; i < this.lexiconPages.length; i ++)
		{
			for(int j = 0; j < this.lexiconPages[0].length; j ++)
			{
				nbt.setBoolean("Category" + i + "Page" + j , this.lexiconPages[i][j]);
			}
		}

		for(int k = 0; k < this.knownWords.size(); k++)
		{
			nbt.setString("KnownWords"+k, this.knownWords.get(k));
		}
		ByteBufUtils.writeTag(buf, nbt);


	}


}

