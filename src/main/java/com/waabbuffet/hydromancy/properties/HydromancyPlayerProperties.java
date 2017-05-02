package com.waabbuffet.hydromancy.properties;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.config.plugins.ResolverUtil.Test;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.TranslationTableResearch;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SyncLexicon;
import com.waabbuffet.hydromancy.packet.packets.CSyncHydromancyPlayerProperties;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import scala.actors.threadpool.Arrays;

public class HydromancyPlayerProperties implements IExtendedEntityProperties {

	private static final String identifier = "hydromancy";


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

	public void unlockPage(int category, int PageNumber, boolean result)
	{
		this.lexiconPages[category][PageNumber] = result;
	}

	public void setKnownWords(List<String> knownWords) {
		this.knownWords = knownWords;
	}

	public void setPageText(String text)
	{
		pageText = text;
	}
	public void setLexiconPages(boolean[][] lexiconPages) {
		this.lexiconPages = lexiconPages;
	}
	
	public void setTextToTranslation(String text)
	{
		textToTranslation = text;
	}
	
	public void setTextToTranslationA(String[] text)
	{
		textToTranslationA.clear(); //we don't want any other element at this point
		if(text != null && text.length > 0)
			textToTranslationA.addAll(Arrays.asList(text));
	}
	
	public void setTextToTranslationA(List<String> text)
	{
		textToTranslationA = text;
	}
	
	public void setTextToTranslationB(boolean[] translated)
	{
		textToTranslationB.clear(); //we don't want any other element at this point
		for(boolean b : translated)
		{
			textToTranslationB.add(b);
		}
	}
	
	public void setTextToTranslationB(List<Boolean> translated)
	{
		textToTranslationB = translated;
	}
	
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

	public void setResearchStates(List<String> states)
	{
		researchStates = states;
	}
	
	public void setResearchName(String name)
	{
		researchName = name;
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
	
	public String[] getTextToTranslationA()
	{
		return textToTranslationA.toArray(new String[0]);
	}
	
	public boolean[] getTextToTranslationB()
	{
		boolean[] array = new boolean[textToTranslationB.size()];
		for(int i=0;i<textToTranslationB.size();i++)
		{
			array[i] = textToTranslationB.get(i);
		}
		return array;
	}
	
	public String getPageText() {
		return pageText;
	}
	
	public String getTextToTranslation() {
		return textToTranslation;
	}
	
	public boolean[][] getLexiconPages() {
		return lexiconPages;
	}
	
	public List<String> getResearchStates() {
		return researchStates;
	}

	public boolean getWTBC() {
		return WTBC;
	}

	public int getChosenId() {
		// TODO Auto-generated method stub
		return chosenID;
	}

	public int[] getChosenY() {
		// TODO Auto-generated method stub
		int[] returnint = new int[yCoords.size()];
		for(int i = 0; i < yCoords.size(); i++)
		{
			returnint[i] = yCoords.get(i);
		}
		return returnint;
	}
	
	public String[] getWordOptions() {
		// TODO Auto-generated method stub
		return wordOptions.toArray(new String[0]);
	}
	
	public String getResearchName()
	{
		return researchName;
	}
	
	public void setWTBC(boolean wordsTBC) {
		WTBC = wordsTBC;
	}

	public void setChosenId(int chosenid) {
		chosenID = chosenid;
	}

	public void setChosenY(int[] yCoord) {
		yCoords.clear();
		if(yCoord != null)
			for(int i:yCoord)
			{
				yCoords.add(i);
			}
	}
	
	public void setWordOptions(String[] options) {
		// TODO Auto-generated method stub
		wordOptions.clear();
		if(options != null)
			wordOptions.addAll(Arrays.asList(options));
	}
	
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

	public void syncTranslationTable() {
		if(this.isServerSide())
		{
			//if(this.pageText != null && this.textToTranslation != null && this.knownWords.size() > 0)
			HydromancyPacketHandler.INSTANCE.sendTo(new CSyncHydromancyPlayerProperties(this.player),(EntityPlayerMP) this.player);
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
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) {
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
	public void init(Entity entity, World world) 
	{

	}

}
