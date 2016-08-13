package com.waabbuffet.hydromancy.properties;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SyncLexicon;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class HydromancyPlayerProperties implements IExtendedEntityProperties {

	private static final String identifier = "hydromancy";


	// PROPERTIES =============================================================
	List<String> knownWords = new ArrayList<String>();

	boolean[][] lexiconPages = new boolean[10][50]; //categories, each category can have up to 50 pages



	private static EntityPlayer player;

	// CONSTRUCTOR, GETTER, REGISTER ==========================================


	public HydromancyPlayerProperties(EntityPlayer player) {
		this.player = player;
	}

	public void unlockPage(int category, int PageNumber, boolean result)
	{
		this.lexiconPages[category][PageNumber] = result;
	}

	public void setKnownWords(List<String> knownWords) {
		this.knownWords = knownWords;
	}


	public void setLexiconPages(boolean[][] lexiconPages) {
		this.lexiconPages = lexiconPages;
	}

	public void addWord(String word)
	{
		if(this.alreadyInList(word))
		{
			this.knownWords.add(word);
		}
	}

	public List<String> getKnownWords() {
		return knownWords;
	}

	
	public boolean alreadyInList(String word)
	{
		for(int i =0; i < this.knownWords.size(); i ++)
		{
			if(word.contentEquals(this.knownWords.get(i)))
			{
				return false;
			}
		}
		
		return true;
	}
	

	public void syncLexicon() {

		if (this.isServerSide())
		{
			
			HydromancyPacketHandler.INSTANCE.sendTo(new SyncLexicon(lexiconPages, knownWords), (EntityPlayerMP) this.player);
		}
	}

	public static HydromancyPlayerProperties get(EntityPlayer player) {
		return (HydromancyPlayerProperties) player.getExtendedProperties(identifier);
	}

	public static void register(EntityPlayer player) {
		player.registerExtendedProperties(identifier, new HydromancyPlayerProperties(player));
	}

	public boolean isServerSide() {
		return this.player instanceof EntityPlayerMP;
	}

	public void saveReviveRelevantNBTData(NBTTagCompound nbt, boolean wasDeath) {
		if (wasDeath)
			this.saveNBTData(nbt);
	}

	// LOAD, SAVE =============================================================

	@Override
	public void saveNBTData(NBTTagCompound nbt) {

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
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) {



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
	public void init(Entity entity, World world) 
	{

	}



}
