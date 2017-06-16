package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslationTable;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiButtonTabResearch extends GuiButton{
	private boolean res;
	private ResourceLocation resource = new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table.png");
	public GuiButtonTabResearch(int ID, int x, int y, boolean research) {
		super(ID, x, y, 28, 32, "");
		// TODO Auto-generated constructor stub
		this.width = 28;
		this.height = 32;
		this.res = research;
	}
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my){}
	
	public void drawSwitchTab(Minecraft minecraft, int mx, int my){
		minecraft.getTextureManager().bindTexture(resource);
		if(res == true)
			drawTexturedModalRect(this.xPosition, this.yPosition, 148, 172, width, height);
		else{
			drawTexturedModalRect(this.xPosition, this.yPosition+1, 148, 204, width, height);		
		}
	}
}
