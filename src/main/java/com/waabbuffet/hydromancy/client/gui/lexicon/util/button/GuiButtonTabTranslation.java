package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslationTable;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiButtonTabTranslation extends GuiButton{
	private boolean res;
	private GuiTranslationTable gui;
	public GuiButtonTabTranslation(int ID, int x, int y, boolean research, GuiTranslationTable gui) {
		super(ID, x, y, 0, 10, "");
		this.width = 28;
		this.height = 32;		
		this.res = research;
		this.gui = gui;
	}
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my){}
	
	public void drawSwitchTab(Minecraft minecraft, int mx, int my) {	
		minecraft.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table.png"));
		if (res == false)
			gui.drawTexturedModalRect(this.xPosition, this.yPosition, 124, 169, 28, 32);
		else		
			gui.drawTexturedModalRect(this.xPosition, this.yPosition+1, 124, 201, 28, 32);
	}

}
