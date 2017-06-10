package com.waabbuffet.hydromancy.lexicon;

import com.waabbuffet.hydromancy.util.EnumCategoryType;

public enum EnumResearchState 
{
	COMPLETED(1),
	UNKNOWN(0),
	IN_PROGRESS(2);

	int id;
	EnumResearchState(int id)
	{
		this.id = id;
	}

	public int getID()
	{
		return this.id;
	}

	public static EnumResearchState getStatefromID(int id)
	{
		for(EnumResearchState dir : values()){
			if(dir.getID() == id){
				return dir;
			}
		}
		return null;
	}
}
