package com.waabbuffet.hydromancy.util;

import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;
import com.waabbuffet.hydromancy.lexicon.LexiconPages;

//import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TranslationTableUtils {

	public static LexiconEntry getPageBasedOnResearchName(String researchName)
	{
		List<CathegoryType> allCathegories = LexiconPages.getAllCathegories();
		for(CathegoryType c : allCathegories)
		{
			for(LexiconEntry e : c.getCathegory())
			{
				//System.out.println("resName: " + researchName);
				if(e.ResearchName.equals(researchName))
					return e;
			}
		}
		return null;
	}
	
	public static String convertToBasicLatin(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

}
