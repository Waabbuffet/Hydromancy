package com.waabbuffet.hydromancy.capability.lexiconPages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;
import javax.swing.text.html.parser.TagElement;

import com.waabbuffet.hydromancy.lexicon.LexiconPageHandler;
import com.waabbuffet.hydromancy.util.EnumCategoryType;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityLexiconPages {

	@CapabilityInject(IPlayerLexiconPages.class)
	public static final Capability<IPlayerLexiconPages> BOTW_CAP = null;

	public static final EnumFacing DEFAULT_FACING = null;

	public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, "PlayerLexiconPages");


	public static void register() {
		CapabilityManager.INSTANCE.register(IPlayerLexiconPages.class, new Storage(), PlayerLexiconPages.class);
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	public static class PlayerLexiconPages implements IPlayerLexiconPages
	{
		//only ever contain unlocked values 
		Map<String, Boolean> lexicon_map = new HashMap<String, Boolean>();

		
		public PlayerLexiconPages() {
			// TODO Auto-generated constructor stub
		}
		
		public PlayerLexiconPages(EntityPlayer player) 
		{
			
		}

		@Override
		public boolean hasUnlockedPage(String map) 
		{
			return lexicon_map.containsKey(map);
		}

		@Override
		public void unlockPage(String map) 
		{
			lexicon_map.put(map, Boolean.TRUE);
		}

		@Override
		public Map<String, Boolean> getMap() 
		{
			return lexicon_map;
		}

		@Override
		public void setMap(Map<String, Boolean> map) 
		{
			lexicon_map = map;
		}
	}

	public static class Storage implements Capability.IStorage<IPlayerLexiconPages>
	{
		@Override
		public NBTBase writeNBT(Capability<IPlayerLexiconPages> capability, IPlayerLexiconPages instance, EnumFacing side) {
			NBTTagCompound tagCompound = new NBTTagCompound();

			Iterator<Entry<String, Boolean>> itr = instance.getMap().entrySet().iterator();

			while(itr.hasNext())
			{
				Entry<String, Boolean> entry = itr.next();
				tagCompound.setBoolean(entry.getKey(), Boolean.TRUE);
			}

			return tagCompound;
		}

		@Override
		public void readNBT(Capability<IPlayerLexiconPages> capability, IPlayerLexiconPages instance, EnumFacing side, NBTBase nbt) 
		{
			NBTTagCompound tagCompound = (NBTTagCompound) nbt;

			Map<String, Boolean> lexicon_map = new HashMap<String, Boolean>();
			for(EnumCategoryType cate : EnumCategoryType.values())
			{
				List<String> strings_to_check = LexiconPageHandler.getCategory(cate);

				for(String string : strings_to_check)
				{
					if(tagCompound.hasKey(string))
					{
						boolean bool = tagCompound.getBoolean(string);
						if(bool)
						{
							lexicon_map.put(string, Boolean.TRUE);
						}
					}
				}
			}
			instance.setMap(lexicon_map);
		}
	}

	public static class EventHandler {

		@SubscribeEvent
		public void attachCapabilities(AttachCapabilitiesEvent.Entity event) {

			if (event.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntity();
				event.addCapability(ID, new Provider(player));
			}
		}

		@SubscribeEvent
		public void onPlayerCloned(PlayerEvent.Clone e) {
			NBTTagCompound nbt = new NBTTagCompound();

			if(e.isWasDeath())
			{
				if(e.getOriginal().hasCapability(BOTW_CAP, null))
				{
					IPlayerLexiconPages newTracker = e.getEntityPlayer().getCapability(BOTW_CAP, null);
					IPlayerLexiconPages oldTracker = e.getOriginal().getCapability(BOTW_CAP, null);
					newTracker.setMap(oldTracker.getMap());
				}
			}
		}
	}

	public static class Provider implements ICapabilitySerializable<NBTTagCompound> {

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
	}
}
