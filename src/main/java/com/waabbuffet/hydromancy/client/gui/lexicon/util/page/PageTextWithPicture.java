package com.waabbuffet.hydromancy.client.gui.lexicon.util.page;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslatedPage;

public class PageTextWithPicture extends LexiconPageBase implements ILexiconPage {

	boolean useStack;

	ResourceLocation picture;
	ItemStack stack;

	int pX, pY, offset, width, height, swidth, sheight;

	private int pages;

	public PageTextWithPicture(ResourceLocation picture, int pX, int pY, int width, int height, int sWidth, int sHeight, String unlocalizedText, int offset) {
		// TODO Auto-generated constructor stub
		this.picture = picture;
		this.pX = pX;
		this.pY = pY;

		this.width = width;
		this.height = height;
		this.offset = offset;
		
		this.swidth = sWidth;
		this.sheight = sHeight;

		this.unLocalizedText = unlocalizedText;

		this.useStack = false;
	}

	public PageTextWithPicture(ItemStack stack, int pX, int pY, String unlocalizedText, int offset) {
		// TODO Auto-generated constructor stub
		this.stack = stack;
		this.pX = pX;
		this.pY = pY;
		this.offset = offset;

		this.unLocalizedText = unlocalizedText;
		this.useStack = true;

	}


	@Override
	public void renderScreen(GuiLexicon gui) {
		// TODO Auto-generated method stub
		int width1 = 115;
		int guiX = (gui.width - gui.xSize) / 2;
		int guiY = (gui.height - gui.ySize) / 2;


		int x = guiX + 15;
		int y = guiY;
		

	

		if(pages%2 == 1){
			renderText(x + gui.xSize/2, y + offset, width1, gui.height, 10, this.unLocalizedText);
		}else{
			renderText(x - gui.xSize/2, y + offset, width1, gui.height, 10, this.unLocalizedText);
		}

		if(!useStack)
		{
			gui.mc.getMinecraft().getTextureManager().bindTexture(picture);
			GL11.glColor4f(1f, 1f, 1f, 1f);
			
			if(pages%2 == 1){
				
			//	gui.drawTexturedModalRect(pX + gui.xSize/2, pY, 0, 0, width, height);	
			//	 Draws a textured rectangle at z = 0. Args: x, y, u, v, width, height, textureWidth, textureHeight
				gui.func_146110_a(x + gui.xSize/2 + pX, y + pY, 0, 0, width, height, swidth, sheight);
			}else{
			//	gui.drawTexturedModalRect(pX - gui.xSize/2, pY, 0, 0, width, height);	
				gui.func_146110_a(x - gui.xSize/2 + pX, y + pY, 0, 0, width, height, swidth, sheight);
			}
		}else
		{
			if(pages%2 == 1){
				gui.drawItemStack(stack, x + pX + gui.xSize/2, pY + y, "");	
			}else{
				gui.drawItemStack(stack, x + pX - gui.xSize/2, pY + y, "");
			}
		}
	}

	public void renderScreen(GuiTranslatedPage gui) {
		// TODO Auto-generated method stub
		int width1 = 115;
		int guiX = (gui.width - gui.xSize) / 2;
		int guiY = (gui.height - gui.ySize) / 2;


		int x = guiX + 15;
		int y = guiY;
		
		renderText(x, y + offset, width1, gui.height, 10, this.unLocalizedText);

		if(!useStack)
		{
			gui.mc.getMinecraft().getTextureManager().bindTexture(picture);
			GL11.glColor4f(1f, 1f, 1f, 1f);
			gui.func_146110_a(x + pX, y + pY, 0, 0, width, height, swidth, sheight);
		}
		else
		{
			gui.drawItemStack(stack, x + pX, pY + y, "");	
		}
	}


	@Override
	public void setPage(int page) {
		this.pages = page;

	}



}
