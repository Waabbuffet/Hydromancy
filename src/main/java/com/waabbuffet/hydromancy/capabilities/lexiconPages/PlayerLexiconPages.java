package com.waabbuffet.hydromancy.capabilities.lexiconPages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;
import javax.swing.text.html.parser.TagElement;

import com.waabbuffet.hydromancy.lexicon.EnumResearchState;
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

public class PlayerLexiconPages implements IPlayerLexiconPages
{
	//only ever contain unlocked values 
		Map<String, EnumResearchState> lexicon_map = new HashMap<String, EnumResearchState>();


		public PlayerLexiconPages() {
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
			lexicon_map.put(map, EnumResearchState.IN_PROGRESS);
		}

		@Override
		public Map<String, EnumResearchState> getMap() 
		{
			return lexicon_map;
		}

		@Override
		public void setMap(Map<String, EnumResearchState> map) 
		{
			lexicon_map = map;
		}
}
