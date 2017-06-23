package com.waabbuffet.hydromancy.client.gui.lexicon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;
import com.waabbuffet.hydromancy.capabilities.translationTable.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
//import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SUpdateTranslationTable;
import com.waabbuffet.hydromancy.tileEntity.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TranslationMinigame {
	public String text,researchName;
	public int pageIndex;
	public String[] textToTranslationA;
	public boolean[] textToTranslationB;
	private String pageText;
	private List<String> splitText = new ArrayList<String>();
	private TranslationTableResearch research;
	private LexiconEntry page;
	private EntityPlayer player;
	private TileEntityTranslationTable te;
	private HydromancyPlayerProperties playerProperties;
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
		playerProperties = (HydromancyPlayerProperties) player.getCapability(HydromancyCapabilities.PLAYER_PROPERTIES, null);

		if(research != null){
			researchName = research.unlocalizedName;
			page = TranslationTableUtils.getPageBasedOnResearchName(researchName);
	
			do {
				System.out.println(page);
				System.out.println(page.PageSequence[rnd.nextInt(page.PageSequence.length)]);
				pageIndex = rnd.nextInt(page.PageSequence.length);
				pageText = page.PageSequence[pageIndex].unLocalizedText;
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
				if(te.handler.getStackInSlot(1) != null){
					if((playerProperties.getTextToTranslationA() == null || playerProperties.getTextToTranslationA().length == 0) && te.handler.getStackInSlot(1).isItemEqual(new ItemStack(HydromancyItemHandler.item_decipheringStone)))
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
		List<String> knownWords = playerProperties.getKnownWords();
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
		if((playerProperties.getKnownWords().size() > 0 ? (rnd.nextInt(playerProperties.getKnownWords().size())) : 1) < (100/(1 + playerProperties.getKnownWords().size())))
		{
			for(int i=0;i<splitText.size();i++){
				if(playerProperties.alreadyInList(splitText.get(i))){
					playerProperties.addWord(splitText.get(i));
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
