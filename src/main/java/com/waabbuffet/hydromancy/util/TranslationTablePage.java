package com.waabbuffet.hydromancy.util;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;

public class TranslationTablePage {

	
	LexiconEntry LexiconPage;
	String Clue, Text, ResearchName;
	
	/**
	 * 
	 * @param clue - clue to where the translation stone can be found
	 * @param text - Hard coded text for lost pages (CAN BE NULL)
	 * @param page - The corresponding page in the lexicon 
	 */
	
	public TranslationTablePage(String clue, String text, LexiconEntry page) {
		Clue = clue;
		Text = text;
		ResearchName = page.ResearchName;
		LexiconPage = page;
	}
	
	public LexiconEntry getLexiconPage() {
		return LexiconPage;
	}
	public void setLexiconPage(LexiconEntry lexiconPage) {
		LexiconPage = lexiconPage;
	}
	public String getClue() {
		return Clue;
	}
	public void setClue(String clue) {
		Clue = clue;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
}
