package com.waabbuffet.hydromancy.packet.packets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;
import com.waabbuffet.hydromancy.capabilities.lexiconPages.IPlayerLexiconPages;
import com.waabbuffet.hydromancy.lexicon.EnumResearchState;
import com.waabbuffet.hydromancy.lexicon.LexiconPageHandler;
import com.waabbuffet.hydromancy.util.EnumCategoryType;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSyncLexicon implements IMessage, IMessageHandler<PacketSyncLexicon , IMessage> {

	IPlayerLexiconPages pages;

	public PacketSyncLexicon(){ }

	public PacketSyncLexicon(EntityPlayer player)
	{
		pages = player.getCapability(HydromancyCapabilities.BOTW_CAP, null);
	}

	@Override
	public IMessage onMessage(final PacketSyncLexicon message, MessageContext ctx) {

		/*if(pages == null)
			pages = (new HydromancyCapabilities.Provider(ctx.getServerHandler().playerEntity)).PlayerLexiconPages();*/
		
		IThreadListener mainThread = Minecraft.getMinecraft();
		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run() {
				EntityPlayer p = Minecraft.getMinecraft().thePlayer;
				World world = p.worldObj;

				IPlayerLexiconPages pages = p.getCapability(HydromancyCapabilities.BOTW_CAP, null);
				if(pages != null)
				{
					pages.setMap(message.pages.getMap());
					if(p != null)
					{
						p.openGui(Hydromancy.instance, 0, world, 0, 0, 0);
					}
				}else
					System.out.println("Uh oh its null....");
			}
		});

		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		NBTTagCompound tagCompound = ByteBufUtils.readTag(buf);

		if(pages != null)
		{
			Map<String, EnumResearchState> lexicon_map = new HashMap<String, EnumResearchState>();
			for(EnumCategoryType cate : EnumCategoryType.values())
			{
				List<String> strings_to_check = LexiconPageHandler.getCategory(cate);
	
				for(String string : strings_to_check)
				{
					if(tagCompound.hasKey(string))
					{
						int state = tagCompound.getInteger(string);
						lexicon_map.put(string, EnumResearchState.getStatefromID(state));
					}
				}
			}
			
			pages.setMap(lexicon_map);
		}
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		NBTTagCompound tagCompound = new NBTTagCompound();

		Iterator<Entry<String, EnumResearchState>> itr = pages.getMap().entrySet().iterator();

		while(itr.hasNext())
		{
			Entry<String, EnumResearchState> entry = itr.next();
			tagCompound.setInteger(entry.getKey(), entry.getValue().getID());
		}
		ByteBufUtils.writeTag(buf, tagCompound);
	}
}