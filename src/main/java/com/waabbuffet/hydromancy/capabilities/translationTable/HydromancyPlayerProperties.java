package com.waabbuffet.hydromancy.capabilities.translationTable;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.TranslationTableResearch;
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
	
	boolean[][] lexiconPages = new boolean[10][50]; //categories, each category can have up to 50 pages
	
	// ----- Minigame variables -----
	List<String> researchStates = new ArrayList<String>(); //research + state
	List<String> textToTranslationA = new ArrayList<String>();
	List<Boolean> textToTranslationB = new ArrayList<Boolean>();
	String textToTranslation = null;
	String pageText = null;
	String researchName = null;
	
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
	public void unlockPage(int category, int PageNumber, boolean result)
	{
		this.lexiconPages[category][PageNumber] = result;
	}

	@Override
	public void setKnownWords(List<String> knownWords) {
		if(knownWords != null)
			this.knownWords = knownWords;
		else
			this.knownWords.clear();
	}

	@Override
	public void setPageText(String text)
	{
		pageText = text;
	}
	@Override
	public void setLexiconPages(boolean[][] lexiconPages) {
		this.lexiconPages = lexiconPages;
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
	public void addResearchState(String name, String value, int index) {
		if(researchStates.contains(name))
		{
			this.researchStates.set(index, name);
			this.researchStates.set(index+1, value);
		}
		else
		{
			this.researchStates.add(index, name);
			this.researchStates.add(index+1, value);
		}
	}
	
	@Override
	public void setResearchStateResearched(String name)
	{
		for(int i = 0; i < researchStates.size(); i+=2)
		{
			if(researchStates.get(i).equals(name))
			{
				this.researchStates.set(i, name);
				this.researchStates.set(i+1, TranslationTableResearch.researchStates.RESEARCHED.name());
				break;
			}
		}
	}
	
	@Override
	public void setResearchStateAvailable(String name)
	{
		for(int i = 0; i < researchStates.size(); i+=2)
		{
			if(researchStates.get(i).equals(name))
			{
				this.researchStates.set(i, name);
				this.researchStates.set(i+1, TranslationTableResearch.researchStates.AVAILABLE.name());
				break;
			}
		}
	}

	@Override
	public void setResearchStates(List<String> states)
	{
		if(states != null)
			researchStates = states;
		else
			researchStates.clear();
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
	public String getPageText() {
		return pageText;
	}
	
	@Override
	public String getTextToTranslation() {
		return textToTranslation;
	}
	
	@Override
	public boolean[][] getLexiconPages() {
		return lexiconPages;
	}
	
	@Override
	public List<String> getResearchStates() {
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
		pageText = null;
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

	@Override
	public void syncTranslationTable() {
		/*if(this.isServerSide())
		{
			if(this.pageText != null && this.textToTranslation != null && this.knownWords.size() > 0)
			HydromancyPacketHandler.INSTANCE.sendTo(new CSyncHydromancyPlayerProperties(this.player),(EntityPlayerMP) this.player);
		}*/
	}
	
	@Override
	public void clearKnownWords() {
		knownWords.clear();
	}
	
	@Override
	public void clearResearchStates() {
		researchStates.clear();
	}
	
	@Override
	public void clearTextToTranslationA() {
		textToTranslationA.clear();
	}
	
	@Override
	public void clearTextToTranslationB() {
		textToTranslationB.clear();
	}
	
	@Override
	public void clearWordOptions() {
		wordOptions.clear();
	}

	@Override
	public void clearYCoords() {
		yCoords.clear();
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("KnownWordsArray", this.knownWords.size());
		nbt.setInteger("researchStatesArray", this.researchStates.size());
		nbt.setInteger("transaltionASize", this.textToTranslationA.size());
		nbt.setInteger("transaltionBSize", this.textToTranslationB.size());
		nbt.setInteger("wordOptionsSize", this.wordOptions.size());
		nbt.setInteger("yCoordsSize", this.yCoords.size());
		nbt.setInteger("chosenID", this.chosenID);
		
		if(textToTranslation != null)
			nbt.setString("translation", textToTranslation);
		if(pageText != null)
			nbt.setString("page", pageText);
		if(researchName != null)
			nbt.setString("research", researchName);

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
		
		for(int i = 0; i < this.researchStates.size(); i++)
		{
			nbt.setString("ResearchStates"+i, this.researchStates.get(i));
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

		for(int i = 0; i < this.lexiconPages.length; i ++)
		{
			for(int j = 0; j < this.lexiconPages[0].length; j ++)
			{
				this.lexiconPages[i][j] = nbt.getBoolean("Category" + i + "Page" + j);
			}
		}
		WTBC = nbt.getBoolean("WTBC");

		for(int k = 0; k < nbt.getInteger("KnownWordsArray"); k++)
		{
			this.knownWords.add(nbt.getString("KnownWords"+k)); 
		}
		
		for(int i = 0; i < nbt.getInteger("researchStatesArray"); i++)
		{
			this.researchStates.add(nbt.getString("ResearchStates"+i));
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
		pageText = nbt.getString("page");
		researchName = nbt.getString("research");
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == HydromancyCapabilities.PLAYER_PROPERTIES;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		// TODO Auto-generated method stub
		return (T)this;
	}
	
	
}
