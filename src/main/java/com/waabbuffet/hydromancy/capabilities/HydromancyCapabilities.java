package com.waabbuffet.hydromancy.capabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.waabbuffet.hydromancy.capabilities.translationTable.IPlayerProperties;
import com.waabbuffet.hydromancy.capabilities.lexiconPages.IPlayerLexiconPages;
import com.waabbuffet.hydromancy.capabilities.lexiconPages.PlayerLexiconPages;
import com.waabbuffet.hydromancy.capabilities.translationTable.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.lexicon.EnumResearchState;
import com.waabbuffet.hydromancy.lexicon.LexiconPageHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.CSyncHydromancyPlayerProperties;
import com.waabbuffet.hydromancy.util.EnumCategoryType;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent.Entity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HydromancyCapabilities {
	@CapabilityInject(IPlayerProperties.class)
	public static final Capability<IPlayerProperties> PLAYER_PROPERTIES = null;
	//@CapabilityInject(IPlayerLexiconPages.class)
	//public static final Capability<IPlayerLexiconPages> BOTW_CAP = null;
	
	public static final EnumFacing DEFAULT_FACING = null;

	public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, "PlayerLexiconPages");
	public static final ResourceLocation PROPERTIES = new ResourceLocation(Reference.MODID, "PlayerProperties");
	
	public static void register() {
		//CapabilityManager.INSTANCE.register(IPlayerLexiconPages.class, new PagesStorage(), PlayerLexiconPages.class);
		CapabilityManager.INSTANCE.register(IPlayerProperties.class, new PlayerPropertiesStorage(), HydromancyPlayerProperties.class);
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	public static class PlayerPropertiesStorage implements IStorage<IPlayerProperties>
	{
		@Override
		public NBTBase writeNBT(Capability<IPlayerProperties> capability, IPlayerProperties instance, EnumFacing side) {
			return instance.serializeNBT();
		}

		@Override
		public void readNBT(Capability<IPlayerProperties> capability, IPlayerProperties instance, EnumFacing side, NBTBase NBTbase) 
		{
			NBTTagCompound nbt = (NBTTagCompound) NBTbase;
			instance.deserializeNBT(nbt);
		}
	}
	
	/*public static class PagesStorage implements IStorage<IPlayerLexiconPages>
	{
		@Override
		public NBTBase writeNBT(Capability<IPlayerLexiconPages> capability, IPlayerLexiconPages instance, EnumFacing side) {
			NBTTagCompound tagCompound = new NBTTagCompound();

			Iterator<Entry<String, EnumResearchState>> itr = instance.getMap().entrySet().iterator();

			while(itr.hasNext())
			{
				Entry<String, EnumResearchState> entry = itr.next();
				tagCompound.setInteger(entry.getKey(),  entry.getValue().getID());
			}

			return tagCompound;
		}

		@Override
		public void readNBT(Capability<IPlayerLexiconPages> capability, IPlayerLexiconPages instance, EnumFacing side, NBTBase nbt) 
		{
			NBTTagCompound tagCompound = (NBTTagCompound) nbt;

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
			instance.setMap(lexicon_map);
		}
	}*/

	public static class EventHandler {

		@SubscribeEvent
		public void attachCapabilities(AttachCapabilitiesEvent.Entity event) {

			if (event.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntity();
				//event.addCapability(ID, new Provider(player));
				if(!(player.hasCapability(PLAYER_PROPERTIES, null)))
				{
					System.out.println("added");
					event.addCapability(PROPERTIES, new HydromancyPlayerProperties(player));
				}
				
			}
		}
		
		@SubscribeEvent
		public void onEntityJoinWorld(EntityJoinWorldEvent e)
		{
			if (e.getEntity() instanceof EntityPlayerMP) {
				HydromancyPacketHandler.INSTANCE.sendTo(new CSyncHydromancyPlayerProperties((EntityPlayer) e.getEntity()), (EntityPlayerMP) e.getEntity());
			}
		}

		@SubscribeEvent
		public void onPlayerCloned(PlayerEvent.Clone e) {
			NBTTagCompound nbt = new NBTTagCompound();

			if(e.isWasDeath())
			{
				/*if(e.getOriginal().hasCapability(BOTW_CAP, null))
				{
					IPlayerLexiconPages newTracker = e.getEntityPlayer().getCapability(BOTW_CAP, null);
					IPlayerLexiconPages oldTracker = e.getOriginal().getCapability(BOTW_CAP, null);
					newTracker.setMap(oldTracker.getMap());
				}*/
				
				if(e.getOriginal().hasCapability(PLAYER_PROPERTIES, null))
				{
					IPlayerProperties newProperties = e.getEntityPlayer().getCapability(PLAYER_PROPERTIES, null);
					IPlayerProperties oldProperties = e.getOriginal().getCapability(PLAYER_PROPERTIES, null);
					newProperties.deserializeNBT(oldProperties.serializeNBT());
				}
			}
		}
	}

	/*public static class Provider implements ICapabilitySerializable<NBTTagCompound> {

		private final IPlayerLexiconPages IPlayerLexiconPages;

		public Provider(EntityPlayer player)
		{
			this(new PlayerLexiconPages(player));
		}

		public Provider(IPlayerLexiconPages itemTracker) {
			this.IPlayerLexiconPages = itemTracker;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			return (NBTTagCompound)BOTW_CAP.getStorage().writeNBT(BOTW_CAP, IPlayerLexiconPages, EnumFacing.NORTH);
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			BOTW_CAP.getStorage().readNBT(BOTW_CAP, IPlayerLexiconPages, EnumFacing.NORTH, nbt);
		}

		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == BOTW_CAP;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

			if (capability == BOTW_CAP) {
				return BOTW_CAP.cast(this.IPlayerLexiconPages);
			}
			return null;
		}
	}*/
}
