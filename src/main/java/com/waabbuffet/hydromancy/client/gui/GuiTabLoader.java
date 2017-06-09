package com.waabbuffet.hydromancy.client.gui;

import java.io.IOException;

import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public abstract class GuiTabLoader extends GuiScreen 
{

	public static final ResourceLocation Background1 = new ResourceLocation(Reference.MODID + ":textures/gui/LexiconGUI.png");

	public GuiTabLoader() 
	{

	}

	@Override
	public void initGui() 
	{
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{
		super.actionPerformed(button);
	}
}
