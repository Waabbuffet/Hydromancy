package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiButtonArrowUp extends GuiButton{
	public boolean hovering;
	private ResourceLocation resource = new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table.png");
	public GuiButtonArrowUp(int id, int x, int y) {
		super(id, x, y, "");
		this.width = 9;
		this.height = 8;
	}

	@Override
	public void drawButton(Minecraft mc, int mx, int my){}
	
	public void drawAButton(Minecraft mc, int mx, int my)
	{
		hovering = (mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height);
		mc.getTextureManager().bindTexture(resource);
		if(!hovering){
			drawTexturedModalRect(xPosition, yPosition, 120, 233, 9, 8);
		}else{
			drawTexturedModalRect(xPosition, yPosition, 138, 233, 9, 8);
			
		}
	}
}
