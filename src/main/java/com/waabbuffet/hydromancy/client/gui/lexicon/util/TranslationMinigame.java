package com.waabbuffet.hydromancy.client.gui.lexicon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SUpdateTranslationTable;
import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.util.TranslationTablePage;
import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TranslationMinigame {
	public String text,pageText,researchName;
	public String[] textToTranslationA;
	public boolean[] textToTranslationB;
	private List<String> splitText = new ArrayList<String>();
	private TranslationTableResearch research;
	private LexiconEntry page;
	private EntityPlayer player;
	private TileEntityTranslationTable te;
	Random rnd = new Random();
	
	//reference pages: com.waabbuffet.hydromancy.util.LexiconPages
	
	/**
	 * From now on reference texts must match research name otherwise this will not work!
	 * cost determines difficulty of translation.
	 * 
	 * @param research
	 */
	public TranslationMinigame(TileEntityTranslationTable te, TranslationTableResearch research, EntityPlayer player){
		this.research = research;
		this.player = player;
		this.te = te;

		if(research != null){
			researchName = research.unlocalizedName;
			page = TranslationTableUtils.getPageBasedOnResearchName(researchName).getLexiconPage();
	
			do {
				pageText = page.PageSequence[rnd.nextInt(page.PageSequence.length)].unLocalizedText;
			}
			while(pageText == null);
			if(pageText != null)
			{
				String[] splitBText = pageText.replaceAll("\\t", "").replaceAll(" +", " ").trim().split(" "); //first replace is in case we'll switch to some xml or something that can do tabs
				int maxWords = rnd.nextInt(6)+3;
				int start = rnd.nextInt(splitBText.length - maxWords);
				for(int i=start; i<start+maxWords;i++){
					if(i==start){
						text = splitBText[i];
					}
					else {
						text += " " + splitBText[i];
					}
					splitText.add(splitBText[i]);
				}
				if(te.getStackInSlot(1) != null){
					if((HydromancyPlayerProperties.get(player).getTextToTranslationA() == null || HydromancyPlayerProperties.get(player).getTextToTranslationA().length == 0) && te.getStackInSlot(1).isItemEqual(new ItemStack(HydromancyItemsHandler.decipheringStone)))
					{
						generateNewKnownWord();
						te.decrStackSize(1, 1);
						HydromancyPacketHandler.INSTANCE.sendToServer(new SUpdateTranslationTable(te));
					}
				}
				revealWords();
				//System.out.println(text);
			}
		}
	}

	private void revealWords() {
		textToTranslationA = splitText.toArray(new String[0]);
		textToTranslationB = new boolean[textToTranslationA.length];
		List<String> knownWords = HydromancyPlayerProperties.get(player).getKnownWords();
		/*System.out.println(String.join(" ", textToTranslationA));
		System.out.println(knownWords.toString());*/
		for(int i=0; i<textToTranslationA.length; i++)
		{
			for(int j=0; j<knownWords.size(); j++)
			{
				if(textToTranslationA[i].contentEquals(knownWords.get(j)))
				{
					textToTranslationB[i] = true;
				}
			}
			if(isNumber(textToTranslationA[i]))
			{
				textToTranslationB[i] = true;
			}
		}
	}

	private void generateNewKnownWord() {
		if((HydromancyPlayerProperties.get(player).getKnownWords().size() > 0 ? (rnd.nextInt(HydromancyPlayerProperties.get(player).getKnownWords().size())) : 1) < (100/(1 + HydromancyPlayerProperties.get(player).getKnownWords().size())))
		{
			for(int i=0;i<splitText.size();i++){
				if(HydromancyPlayerProperties.get(player).alreadyInList(splitText.get(i))){
					HydromancyPlayerProperties.get(player).addWord(splitText.get(i));
					break;
				}
			}
		}
	}
	
	private boolean isNumber(String is)
	{
		char[] isn = is.toCharArray();
		for(char ch : isn)
		{
			if(Character.isDigit(ch))
			{
				return true;
			}
		}
		return false;
	}
	
	
}
