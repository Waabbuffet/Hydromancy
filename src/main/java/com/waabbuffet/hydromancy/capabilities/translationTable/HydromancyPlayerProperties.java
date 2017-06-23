package com.waabbuffet.hydromancy.capabilities.translationTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.TranslationTableResearch;
import com.waabbuffet.hydromancy.lexicon.EnumResearchState;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import scala.actors.threadpool.Arrays;

public class HydromancyPlayerProperties implements IPlayerProperties{
	
	// PROPERTIES =============================================================
	List<String> knownWords = new ArrayList<String>();
		
	// ----- Minigame variables -----
	Map<String, EnumResearchState> researchStates = new HashMap<String, EnumResearchState>();
	
	List<String> textToTranslationA = new ArrayList<String>();
	List<Boolean> textToTranslationB = new ArrayList<Boolean>();
	String textToTranslation = null;
	String researchName = null;
	int pageIndex = -1;
	
	List<Integer> yCoords = new ArrayList<Integer>();
	List<String> wordOptions = new ArrayList<String>();
	boolean WTBC;
	int chosenID;
	// ------------------------------

	private static EntityPlayer player;

	// CONSTRUCTOR, GETTER, REGISTER ==========================================

	public HydromancyPlayerProperties(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void setKnownWords(List<String> knownWords) {
		if(knownWords != null)
			this.knownWords = knownWords;
		else
			this.knownWords.clear();
	}
	
	@Override
	public void setPageIndex(int index)
	{
		if(index >= -1)
			pageIndex = index;
		else
			pageIndex = -1;
	}
	
	@Override
	public void setTextToTranslation(String text)
	{
		textToTranslation = text;
	}
	
	@Override
	public void setTextToTranslationA(String[] text)
	{
		textToTranslationA.clear(); //we don't want any other element at this point
		if(text != null && text.length > 0)
			textToTranslationA.addAll(Arrays.asList(text));
	}
	
	@Override
	public void setTextToTranslationA(List<String> text)
	{
		if(text != null)
			textToTranslationA = text;
		else
			textToTranslationA.clear();
	}

	@Override
	public void setTextToTranslationB(boolean[] translated)
	{
		textToTranslationB.clear(); //we don't want any other element at this point
		for(boolean b : translated)
		{
			textToTranslationB.add(b);
		}
	}
	
	@Override
	public void setTextToTranslationB(List<Boolean> translated)
	{
		textToTranslationB = translated;
	}
	
	@Override
	public void addOrModifyResearchState(String name, EnumResearchState value) {
		researchStates.put(name, value);
	}

	@Override
	public void setResearchStates(Map<String, EnumResearchState> states)
	{
		researchStates.clear();
		if(states != null)
			researchStates.putAll(states);
	}
	
	@Override
	public void setResearchName(String name)
	{
		researchName = name;
	}
	
	@Override
	public void addWord(String word)
	{
		if(this.alreadyInList(word))
		{
			this.knownWords.add(word);
		}
	}

	@Override
	public List<String> getKnownWords() {
		return knownWords;
	}
	
	@Override
	public int getPageIndex()
	{
		return pageIndex;
	}
	
	@Override
	public String[] getTextToTranslationA()
	{
		return textToTranslationA.toArray(new String[0]);
	}
	
	@Override
	public boolean[] getTextToTranslationB()
	{
		boolean[] array = new boolean[textToTranslationB.size()];
		for(int i=0;i<textToTranslationB.size();i++)
		{
			array[i] = textToTranslationB.get(i);
		}
		return array;
	}
	
	@Override
	public String getTextToTranslation() {
		return textToTranslation;
	}
	
	@Override
	public Map<String, EnumResearchState> getResearchStates() {
		return researchStates;
	}

	@Override
	public boolean getWTBC() {
		return WTBC;
	}

	@Override
	public int getChosenId() {
		return chosenID;
	}

	@Override
	public int[] getChosenY() {
		int[] returnint = new int[yCoords.size()];
		for(int i = 0; i < yCoords.size(); i++)
		{
			returnint[i] = yCoords.get(i);
		}
		return returnint;
	}
	
	@Override
	public String[] getWordOptions() {
		return wordOptions.toArray(new String[0]);
	}
	
	@Override
	public String getResearchName()
	{
		return researchName;
	}
	
	@Override
	public void setWTBC(boolean wordsTBC) {
		WTBC = wordsTBC;
	}

	@Override
	public void setChosenId(int chosenid) {
		chosenID = chosenid;
	}
	
	@Override
	public void setChosenY(int[] yCoord) {
		yCoords.clear();
		if(yCoord != null)
			for(int i:yCoord)
			{
				yCoords.add(i);
			}
	}
	
	@Override
	public void setChosenY(List<Integer> yCoord) {
		if(yCoord != null)
			yCoords = yCoord;
		else
			yCoords.clear();
	}
	
	@Override
	public void setWordOptions(String[] options) {
		wordOptions.clear();
		if(options != null)
			wordOptions.addAll(Arrays.asList(options));
	}
	
	@Override
	public void setWordOptions(List<String> options) {
		if(options != null)
			wordOptions = options;
		else
			wordOptions.clear();
	}
	
	@Override
	public void disposeMinigame()
	{
		chosenID = -1;
		WTBC = false;
		researchName = null;
		textToTranslation = null;
		yCoords.clear();
		textToTranslationA.clear();
		textToTranslationB.clear();
		wordOptions.clear();
	}
	
	
	@Override
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

	/*@Override
	public void syncTranslationTable() {
		/*if(this.isServerSide())
		{
			if(this.pageText != null && this.textToTranslation != null && this.knownWords.size() > 0)
			HydromancyPacketHandler.INSTANCE.sendTo(new CSyncHydromancyPlayerProperties(this.player),(EntityPlayerMP) this.player);
		}*/
	//}
	
	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("KnownWordsArray", this.knownWords.size());
		//save map as a list and then parse it back on read
		
		List<String> researchStatesList = new ArrayList<String>();
		for(String k : researchStates.keySet())
		{
			researchStatesList.add(k);
			researchStatesList.add(Integer.toString(researchStates.get(k).getID()));
		}
		
		nbt.setInteger("researchStatesListSize", researchStatesList.size());
		nbt.setInteger("transaltionASize", this.textToTranslationA.size());
		nbt.setInteger("transaltionBSize", this.textToTranslationB.size());
		nbt.setInteger("wordOptionsSize", this.wordOptions.size());
		nbt.setInteger("yCoordsSize", this.yCoords.size());
		nbt.setInteger("chosenID", this.chosenID);
		nbt.setInteger("pageIndex", this.pageIndex);
		
		if(textToTranslation != null)
			nbt.setString("translation", textToTranslation);
		if(researchName != null)
			nbt.setString("research", researchName);

		for(int k = 0; k < this.knownWords.size(); k++)
		{
			nbt.setString("KnownWords"+k, this.knownWords.get(k));
		}
		
		for(int i = 0; i < researchStatesList.size(); i++)
		{
			nbt.setString("researchStatesList"+i, researchStatesList.get(i));
		}
		
		for(int k = 0; k < this.textToTranslationA.size(); k++)
		{
			nbt.setString("translationA"+k, this.textToTranslationA.get(k));
		}
		
		for(int i = 0; i < this.textToTranslationB.size(); i++)
		{
			nbt.setBoolean("translationB"+i, this.textToTranslationB.get(i));
		}
		
		for(int k = 0; k < this.yCoords.size(); k++)
		{
			nbt.setInteger("yCoords"+k, this.yCoords.get(k));
		}
		
		for(int i = 0; i < this.wordOptions.size(); i++)
		{
			nbt.setString("wordOptions"+i, this.wordOptions.get(i));
		}
			
		nbt.setBoolean("WTBC", this.WTBC);
		
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		knownWords.clear();
		researchStates.clear();
		textToTranslationA.clear();
		textToTranslationB.clear();
		wordOptions.clear();
		yCoords.clear();

		WTBC = nbt.getBoolean("WTBC");

		for(int k = 0; k < nbt.getInteger("KnownWordsArray"); k++)
		{
			this.knownWords.add(nbt.getString("KnownWords"+k)); 
		}
		
		for(int i = 0; i < nbt.getInteger("researchStatesListSize"); i+=2)
		{
			System.out.println("researches: " + EnumResearchState.getStatefromID(Integer.parseInt(nbt.getString("researchStatesList"+(i+1)))));
			researchStates.put(nbt.getString("researchStatesList"+i), EnumResearchState.getStatefromID(Integer.parseInt(nbt.getString("researchStatesList"+(i+1)))));
		}
		
		for(int k = 0; k < nbt.getInteger("transaltionASize"); k++)
		{
			this.textToTranslationA.add(nbt.getString("translationA"+k)); 
		}
		
		for(int i = 0; i < nbt.getInteger("transaltionBSize"); i++)
		{
			this.textToTranslationB.add(nbt.getBoolean("translationB"+i)); 
		}
		
		for(int k = 0; k < nbt.getInteger("yCoordsSize"); k++)
		{
			this.yCoords.add(nbt.getInteger("yCoords"+k)); 
		}
		
		for(int i = 0; i < nbt.getInteger("wordOptionsSize"); i++)
		{
			this.wordOptions.add(nbt.getString("wordOptions"+i)); 
		}
		
		textToTranslation = nbt.getString("translation");
		researchName = nbt.getString("research");
		pageIndex = nbt.getInteger("pageIndex");
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == HydromancyCapabilities.PLAYER_PROPERTIES;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return (T)this;
	}
	
	
}
