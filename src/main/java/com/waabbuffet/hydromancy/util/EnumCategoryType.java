package com.waabbuffet.hydromancy.util;

import java.awt.Color;

public enum EnumCategoryType
{
	EXPLORATION("Exploration",0, new Color(255,178,25)),
	TRANSPORTATION("Transportation", 1, new Color(181,17,255)),
	CONSUME("Consume", 2, new Color(127,0,0)),
	GENERATION("Generation", 3, new Color(0,148,255)),
	RITUALS("Rituals",4, new Color(255,109,5));

	Color rbg;
	int id;
	
	EnumCategoryType(String string, int id, Color rbg)
	{
		this.id = id;
		this.rbg = rbg;
	}

	public int getRBG()
	{
		return rbg.getRGB();
	}

	public int getID()
	{
		return id;
	}

	public static EnumCategoryType getEnumFromID(int id)
	{
		for(EnumCategoryType dir : values()){
			if(dir.getID() == id){
				return dir;
			}
		}
		return null;
	}
}
