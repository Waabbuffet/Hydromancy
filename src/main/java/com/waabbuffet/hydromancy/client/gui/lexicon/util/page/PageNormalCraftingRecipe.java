package com.waabbuffet.hydromancy.client.gui.lexicon.util.page;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PageNormalCraftingRecipe extends LexiconPageBase implements ILexiconPage{


	public final ResourceLocation craftingLocation = new ResourceLocation(Reference.MODID + ":textures/gui/lexiconGUI.png");

	ItemStack[] CraftingRecipe = new ItemStack[9];
	ItemStack Output; 
	int guiX, guiY, guiYr, pages;
	String unLocalizedName;


	public PageNormalCraftingRecipe(ItemStack[] craftingRecipe, ItemStack output, String unLocalizedName, int guiYr) 
	{
		// TODO Auto-generated constructor stub
		CraftingRecipe = craftingRecipe;
		Output = output;	
		setCraftingRecipeSubtext(unLocalizedName);
		setRecipeY(guiYr);
	}
	public  void setRecipeY(int y) {
		guiYr = y;
	}
	public  void setCraftingRecipeSubtext(String unlocalizedName) {
		unLocalizedName = unlocalizedName;
	}

	@Override
	public void renderScreen(GuiLexicon gui) {		
		int width = 115;
		guiX = (gui.width - gui.xSize) / 2;
		guiY = (gui.height - gui.ySize) / 2;

		int x = guiX + 15;
		int y = guiY;
		y += guiYr;

	
		if(pages%2 == 1){
			this.renderOnScreen(gui.mc, guiX+gui.xSize/2, y+26, gui);
			PageText.renderText(x+gui.xSize/2, guiY-guiYr-4, width, gui.height-guiYr, 10, this.unLocalizedName);
		}else{
			this.renderOnScreen(gui.mc, guiX-gui.xSize/2, y+26, gui);
			PageText.renderText(x-gui.xSize/2, guiY-guiYr-4, width, gui.height-guiYr, 10, this.unLocalizedName);
		}
	}


	public void renderOnScreen(Minecraft minecraft, int x, int y, GuiLexicon Lexicon)
	{

		minecraft.getMinecraft().getTextureManager().bindTexture(craftingLocation);
		GL11.glColor4f(1f, 1f, 1f, 1f);
		Lexicon.drawTexturedModalRect(x + 15, y + 50, 159, 1, 65, 65); //3 by 3 214 57
		Lexicon.drawTexturedModalRect(x + 75, y + 70, 179, 93, 26, 17); //Arrow 204 109
		Lexicon.drawTexturedModalRect(x + 110, y + 70 , 184, 127, 16, 16); // Crafting Location 199, 142


		for(int i =0; i < 3; i ++)
		{
			if(this.CraftingRecipe[i] != null)
				Lexicon.drawItemStack(this.CraftingRecipe[i], x + 15 + i * 19, y + 50, "");
		}

		for(int i =0; i < 3; i ++)
		{
			if(this.CraftingRecipe[i + 3] != null)
				Lexicon.drawItemStack(this.CraftingRecipe[i + 3], x + 15 +  i * 19, y + 16 + 52, "");
		}

		for(int i =0; i < 3; i ++)
		{
			if(this.CraftingRecipe[i + 6] != null)
				Lexicon.drawItemStack(this.CraftingRecipe[i + 6], x + 15 + i * 19, y + 32 + 55, "");
		}

		if(this.Output != null)
		{
			Lexicon.drawItemStack(this.Output, x + 110, y + 16 + 54, "");
		}
	}
	@Override
	public void setPage(int page) {
		this.pages = page;

	}

}
