package com.waabbuffet.hydromancy.capability.lexiconPages;

import java.util.Map;



public interface IPlayerLexiconPages 
{
	public boolean hasUnlockedPage(String map);
	
	public void unlockPage(String map);
	
	public Map<String, Boolean> getMap();
	
	public void setMap(Map<String, Boolean> map);
}
