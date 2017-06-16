package com.waabbuffet.hydromancy.capabilities.translationTable;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.TranslationTableResearch;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import scala.actors.threadpool.Arrays;

public interface IPlayerProperties extends ICapabilitySerializable<NBTTagCompound> {
	public void unlockPage(int category, int PageNumber, boolean result);

	public String getPageText();
	public void setPageText(String text);
	
	public void setLexiconPages(boolean[][] lexiconPages);	
	
	public String getTextToTranslation();
	public void setTextToTranslation(String text);
	
	public String[] getTextToTranslationA();
	public boolean[] getTextToTranslationB();
	
	public void setTextToTranslationA(String[] text);
	public void setTextToTranslationA(List<String> text);
	public void setTextToTranslationB(boolean[] translated);
	public void setTextToTranslationB(List<Boolean> translated);
	
	public List<String> getResearchStates();
	public void addResearchState(String name, String value, int index);
	
	public void setResearchStateResearched(String name);
	public void setResearchStateAvailable(String name);
	public void setResearchStates(List<String> states);
	
	public String getResearchName();
	public void setResearchName(String name);
	
	public List<String> getKnownWords();
	public void setKnownWords(List<String> knownWords);
	public void addWord(String word);
	
	public boolean[][] getLexiconPages();
	
	public boolean getWTBC();
	public void setWTBC(boolean wordsTBC);

	public int getChosenId();
	public void setChosenId(int chosenid);

	public int[] getChosenY();
	public void setChosenY(int[] yCoord);
	public void setChosenY(List<Integer> yCoord);
	
	public String[] getWordOptions();
	public void setWordOptions(String[] options);
	public void setWordOptions(List<String> options);

	public void disposeMinigame();
	
	public boolean alreadyInList(String word); //needs rename

	public void syncTranslationTable();
	
	public void clearKnownWords();
	public void clearResearchStates();
	public void clearTextToTranslationA();
	public void clearTextToTranslationB();
	public void clearWordOptions();
	public void clearYCoords();

}
