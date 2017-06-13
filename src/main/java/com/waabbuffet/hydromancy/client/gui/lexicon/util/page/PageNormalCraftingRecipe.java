package com.waabbuffet.hydromancy.client.gui.lexicon.util.page;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PageNormalCraftingRecipe extends LexiconPageBase implements ILexiconPage{


	public final ResourceLocation craftingLocation = new ResourceLocation(Reference.MODID + ":textures/gui/lexiconGUI.png");

	ItemStack[] CraftingRecipe = new ItemStack[9];
	ItemStack Output; 
	int guiX, guiY, recipeYOffset, pages;


	public PageNormalCraftingRecipe(ItemStack[] craftingRecipe, ItemStack output, String unLocalizedText, int recipeYOffset) 
	{
		// TODO Auto-generated constructor stub
		CraftingRecipe = craftingRecipe;
		Output = output;	
		setCraftingRecipeSubtext(unLocalizedText);
		setRecipeY(recipeYOffset);
	}
	public  void setRecipeY(int y) {
		recipeYOffset = y;
	}
	public  void setCraftingRecipeSubtext(String unLocalizedText) {
		this.unLocalizedText = unLocalizedText;
	}

	@Override
	public void renderScreen(GuiLexicon gui) {		
		int width = 115;
		guiX = (gui.width - gui.xSize) / 2;
		guiY = (gui.height - gui.ySize) / 2;

		int x = guiX + 15;
		int y = guiY + recipeYOffset;


		if(pages%2 == 1){
		
			renderText(x+gui.xSize/2, guiY-recipeYOffset-4, width, gui.height-recipeYOffset, 10, this.unLocalizedText);
			this.renderOnScreen(gui.mc, guiX+gui.xSize/2, y+26, gui);
		}else{
			
			renderText(x-gui.xSize/2, guiY-recipeYOffset-4, width, gui.height-recipeYOffset, 10, this.unLocalizedText);
			this.renderOnScreen(gui.mc, guiX-gui.xSize/2, y+26, gui);
		}
	}

	public void renderOnScreen(Minecraft minecraft, int x, int y, GuiScreen screen)
	{

		minecraft.getMinecraft().getTextureManager().bindTexture(craftingLocation);
		GL11.glColor4f(1f, 1f, 1f, 1f);
		screen.drawTexturedModalRect(x + 15, y + 50, 159, 1, 56, 55); //3 by 3 214 57
		screen.drawTexturedModalRect(x + 75, y + 70, 180, 93, 25, 17); //Arrow 204 109
		screen.drawTexturedModalRect(x + 110, y + 70 , 184, 127, 16, 16); // Crafting Location 199, 142

		for(int i =0; i < 3; i ++)
		{
			if(this.CraftingRecipe[i] != null){
				if(screen instanceof GuiLexicon)
					((GuiLexicon) screen).drawItemStack(this.CraftingRecipe[i], x + 15 + i * 19, y + 50, "");
				else return;
			}
		}

		for(int i =0; i < 3; i ++)
		{
			if(this.CraftingRecipe[i + 3] != null){
				if(screen instanceof GuiLexicon)
					((GuiLexicon) screen).drawItemStack(this.CraftingRecipe[i + 3], x + 15 +  i * 19, y + 16 + 52, "");

			}
		}

		for(int i =0; i < 3; i ++)
		{
			if(this.CraftingRecipe[i + 6] != null){
				if(screen instanceof GuiLexicon)
					((GuiLexicon) screen).drawItemStack(this.CraftingRecipe[i + 6], x + 15 + i * 19, y + 32 + 55, "");

			}
		}

		if(this.Output != null)
		{
			if(screen instanceof GuiLexicon)
				((GuiLexicon) screen).drawItemStack(this.Output, x + 110, y + 16 + 54, "");
		}
	}

	@Override
	public void setPage(int page) {
		this.pages = page;

	}
}
