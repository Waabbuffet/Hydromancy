package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import org.lwjgl.input.Mouse;

import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiSlider extends GuiButton{

	public boolean hovering, disabled, changed = false;
	public float sliderValue;
	public int maxHeight, minY, segments;
	private boolean clicked;
	private int lasti = 0;
	public int idelta = 0;
	
	private ResourceLocation res = new ResourceLocation(Reference.MODID + ":textures/gui/LexiconContainerGui.png");
	
	public GuiSlider(int id, int X, int minY, int maxHeight, int width, int height)
	{
		super(id, X, minY, "");
		this.width = width;
		this.height = height;
		this.minY = minY;
		this.maxHeight = maxHeight - height;
		sliderValue = (yPosition - minY) / maxHeight;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mx, int my)
	{
		changed = false;
		if(!disabled)
		{		
			hovering = (mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height);
			if(Mouse.isButtonDown(0) && (hovering || clicked))
			{
				if(hovering)
					clicked = true;
				sliderValue = (float) ((my - height/2) - minY ) / maxHeight;
			}
			else if (!Mouse.isButtonDown(0))
			{
				clicked = false;
			}
			
			if(sliderValue > 1f)
				sliderValue = 1f;
			else if(sliderValue < 0f)
				sliderValue = 0;
			
			int roundTo = Math.round((float)maxHeight/segments);
			float sValue = 0;
			
			
			for(int i = 1; i <= segments; i++)
			{
				if(sliderValue*maxHeight >= roundTo*(i-1) && sliderValue*maxHeight < roundTo*i)
				{
					sValue = (float)((roundTo*(i-1) + roundTo*(i-1+Math.round((float)roundTo*(i-1)/maxHeight))/segments))/maxHeight;
					idelta = lasti - (i-1);
					lasti = i-1;
					changed = true;
				}
			}
			
			if(changed)
			{
				sliderValue = sValue;
				//System.out.println(idelta);
			}
		}
		
		mc.getTextureManager().bindTexture(res);
		if(disabled)
			drawTexturedModalRect(xPosition, yPosition = minY, 206, 0, width, height);
		else
			drawTexturedModalRect(xPosition, yPosition = Math.round((float)(minY + sliderValue*maxHeight)), 194, 0, width, height);
	}
}
