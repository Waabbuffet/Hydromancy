package com.waabbuffet.hydromancy.client.gui.lexicon;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageNormalCraftingRecipe;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageText;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageTextWithPicture;
import com.waabbuffet.hydromancy.util.Reference;
import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiTranslatedPage extends GuiScreen
{
	public static final ResourceLocation bg = new ResourceLocation(Reference.MODID + ":textures/gui/research_notes.png");
	public int xSize, ySize, PageIndex;
	public Minecraft mc = Minecraft.getMinecraft();
	private EntityPlayer player;
	private LexiconEntry pageEntry;
	public GuiTranslatedPage(EntityPlayer player)
	{
		this.xSize = 151;
		this.ySize = 230;
		this.player = player;
	}
	
	@Override
	public void initGui()
	{
		if(pageEntry == null && !player.getHeldItemMainhand().getTagCompound().getString("research").contentEquals("scribble")){
			pageEntry = TranslationTableUtils.getPageBasedOnResearchName(player.getHeldItemMainhand().getTagCompound().getString("research")).getLexiconPage();
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;
		
		this.mc.getTextureManager().bindTexture(bg);
		drawTexturedModalRect(guiX, guiY, 0, 0, this.xSize, this.ySize);
		
		if(pageEntry != null){
			if(pageEntry.PageSequence[PageIndex] instanceof PageText)
				((PageText) pageEntry.PageSequence[PageIndex]).renderScreen(this);
			else if(pageEntry.PageSequence[PageIndex] instanceof PageNormalCraftingRecipe)
				((PageNormalCraftingRecipe) pageEntry.PageSequence[PageIndex]).renderScreen(this);
			else if(pageEntry.PageSequence[PageIndex] instanceof PageTextWithPicture)
				((PageTextWithPicture) pageEntry.PageSequence[PageIndex]).renderScreen(this);
			else return;
		}
		else
		{
			drawTexturedModalRect(guiX+30, guiY+10, 160, 23, 88, 194);
		}
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	public void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_)
	{
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		itemRender.zLevel = 200.0F;
		FontRenderer font = null;
		if (p_146982_1_ != null) font = p_146982_1_.getItem().getFontRenderer(p_146982_1_);
		if (font == null) font = fontRendererObj;
		itemRender.renderItemOverlayIntoGUI(font, p_146982_1_, p_146982_2_, p_146982_3_ , p_146982_4_);
		this.zLevel = 0.0F;
		itemRender.zLevel = 0.0F; 
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	
	}
}
