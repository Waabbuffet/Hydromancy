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
import com.waabbuffet.hydromancy.util.EnumCategoryType;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HydromancyCapabilities {
	@CapabilityInject(IPlayerProperties.class)
	public static final Capability<IPlayerProperties> PLAYER_PROPERTIES = null;
	@CapabilityInject(IPlayerLexiconPages.class)
	public static final Capability<IPlayerLexiconPages> BOTW_CAP = null;
	
	public static final EnumFacing DEFAULT_FACING = null;

	public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, "PlayerLexiconPages");
	public static final ResourceLocation PROPERTIES = new ResourceLocation(Reference.MODID, "PlayerProperties");
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IPlayerLexiconPages.class, new PagesStorage(), PlayerLexiconPages.class);
		CapabilityManager.INSTANCE.register(IPlayerProperties.class, new PlayerPropertiesStorage(), HydromancyPlayerProperties.class);
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	public static class PlayerPropertiesStorage implements IStorage<IPlayerProperties>
	{
		@Override
		public NBTBase writeNBT(Capability<IPlayerProperties> capability, IPlayerProperties instance, EnumFacing side) {
			/*NBTTagCompound nbt = new NBTTagCompound();
			
			nbt.setInteger("KnownWordsArray", instance.getKnownWords().size());
			nbt.setInteger("researchStatesArray", instance.getResearchStates().size());
			nbt.setInteger("transaltionASize", instance.getTextToTranslationA().length);
			nbt.setInteger("transaltionBSize", instance.getTextToTranslationB().length);
			nbt.setInteger("wordOptionsSize", instance.getWordOptions().length);
			nbt.setInteger("yCoordsSize", instance.getChosenY().length);
			nbt.setInteger("chosenID", instance.getChosenId());
			
			if(instance.getTextToTranslation() != null)
				nbt.setString("translation", instance.getTextToTranslation());
			if(instance.getPageText() != null)
				nbt.setString("page", instance.getPageText());
			if(instance.getResearchName() != null)
				nbt.setString("research", instance.getResearchName());

			for(int i = 0; i < instance.getLexiconPages().length; i ++)
			{
				for(int j = 0; j < instance.getLexiconPages()[0].length; j ++)
				{
					nbt.setBoolean("Category" + i + "Page" + j , instance.getLexiconPages()[i][j]);
				}
			}

			for(int k = 0; k < instance.getKnownWords().size(); k++)
			{
				nbt.setString("KnownWords"+k, instance.getKnownWords().get(k));
			}
			
			for(int i = 0; i < instance.getResearchStates().size(); i++)
			{
				nbt.setString("ResearchStates"+i, instance.getResearchStates().get(i));
			}
			
			for(int k = 0; k < instance.getTextToTranslationA().length; k++)
			{
				nbt.setString("translationA"+k, instance.getTextToTranslationA()[k]);
			}
			
			for(int i = 0; i < instance.getTextToTranslationB().length; i++)
			{
				nbt.setBoolean("translationB"+i, instance.getTextToTranslationB()[i]);
			}
			
			for(int k = 0; k < instance.getChosenY().length; k++)
			{
				nbt.setInteger("yCoords"+k, instance.getChosenY()[k]);
			}
			
			for(int i = 0; i < instance.getWordOptions().length; i++)
			{
				nbt.setString("wordOptions"+i, instance.getWordOptions()[i]);
			}
				
			nbt.setBoolean("WTBC", instance.getWTBC());*/
			
			return instance.serializeNBT();
		}

		@Override
		public void readNBT(Capability<IPlayerProperties> capability, IPlayerProperties instance, EnumFacing side, NBTBase NBTbase) 
		{
			NBTTagCompound nbt = (NBTTagCompound) NBTbase;
			/*instance.clearKnownWords();
			instance.clearResearchStates();
			instance.clearTextToTranslationA();
			instance.clearTextToTranslationB();
			instance.clearWordOptions();
			instance.clearYCoords();
			
			for(int i = 0; i < instance.getLexiconPages().length; i++)
			{
				for(int j = 0; j < instance.getLexiconPages()[0].length; j++)
				{
					instance.getLexiconPages()[i][j] = nbt.getBoolean("Category" + i + "Page" + j);
				}
			}
			instance.setWTBC(nbt.getBoolean("WTBC"));

			for(int k = 0; k < nbt.getInteger("KnownWordsArray"); k++)
			{
				instance.addWord(nbt.getString("KnownWords"+k)); 
			}
			
			List<String> states = new ArrayList<String>();
			for(int i = 0; i < nbt.getInteger("researchStatesArray"); i++)
			{
				states.add(nbt.getString("ResearchStates"+i));
			}
			instance.setResearchStates(states);
			
			List<String> translationA = new ArrayList<String>();
			for(int k = 0; k < nbt.getInteger("transaltionASize"); k++)
			{
				translationA.add(nbt.getString("translationA"+k)); 
			}
			instance.setTextToTranslationA(translationA);
			
			List<Boolean> translationB = new ArrayList<Boolean>();
			for(int i = 0; i < nbt.getInteger("transaltionBSize"); i++)
			{
				translationB.add(nbt.getBoolean("translationB"+i)); 
			}
			instance.setTextToTranslationB(translationB);
			
			List<Integer> yCoords = new ArrayList<Integer>();
			for(int k = 0; k < nbt.getInteger("yCoordsSize"); k++)
			{
				yCoords.add(nbt.getInteger("yCoords"+k)); 
			}
			instance.setChosenY(yCoords);
			
			
			List<String> wordOptions = new ArrayList<String>();
			for(int i = 0; i < nbt.getInteger("wordOptionsSize"); i++)
			{
				wordOptions.add(nbt.getString("wordOptions"+i)); 
			}
			instance.setWordOptions(wordOptions);
			
			instance.setTextToTranslation(nbt.getString("translation"));
			instance.setPageText(nbt.getString("page"));
			instance.setResearchName(nbt.getString("research"));*/
			instance.deserializeNBT(nbt);
		}
	}
	
	public static class PagesStorage implements IStorage<IPlayerLexiconPages>
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
	}

	public static class EventHandler {

		@SubscribeEvent
		public void attachCapabilities(AttachCapabilitiesEvent.Entity event) {

			if (event.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntity();
				event.addCapability(ID, new Provider(player));
				if(!(player.hasCapability(PLAYER_PROPERTIES, null)))
				{
					event.addCapability(PROPERTIES, new HydromancyPlayerProperties(player));
				}
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
				
				if(e.getOriginal().hasCapability(PLAYER_PROPERTIES, null))
				{
					IPlayerProperties newProperties = e.getEntityPlayer().getCapability(PLAYER_PROPERTIES, null);
					IPlayerProperties oldProperties = (IPlayerProperties) e.getOriginal().getCapability(BOTW_CAP, null);
					newProperties.deserializeNBT(oldProperties.serializeNBT());
					//new.setMap(oldTracker.getMap());
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
