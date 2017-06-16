package com.waabbuffet.hydromancy.capabilities.lexiconPages;

import java.util.Map;

import com.waabbuffet.hydromancy.lexicon.EnumResearchState;



public interface IPlayerLexiconPages 
{
	public boolean hasUnlockedPage(String map);
	
	public void unlockPage(String map);
	
	public Map<String, EnumResearchState> getMap();
	
	public void setMap(Map<String, EnumResearchState> map);
}
