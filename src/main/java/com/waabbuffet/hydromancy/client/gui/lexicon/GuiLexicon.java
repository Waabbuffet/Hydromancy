package com.waabbuffet.hydromancy.client.gui.lexicon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.LexiconEntry;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonCategory;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonEntry;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonPageChanger;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.ILexiconPage;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.LexiconPageBase;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageNormalCraftingRecipe;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.page.PageText;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiLexicon extends GuiScreen {

	//TODO: Add a List for each Category and then add all the entries inside there
	//- Need to add play button (play the entry sequence)
	//- Need to add history button (add entry to history)
	//- This lexicon is a list of categories, the categories are a list of page entries, the page entries are a list of page objects;
	
	//Categories
	/************************************************************************************************************************************/
	public List<LexiconEntry> GeneratingCategory = new ArrayList<LexiconEntry>();
	public List<LexiconEntry> ConsumingCategory = new ArrayList<LexiconEntry>();
	public List<LexiconEntry> TransportationCategory = new ArrayList<LexiconEntry>();
	public List<LexiconEntry> WorldGenerationCategory = new ArrayList<LexiconEntry>();
	
	
	
	
	public GuiButtonCategory ButtonGeneratingCategory, ButtonConsumingCategory, ButtonTransportationCategory, ButtonWorldGenerationCategory;
	
	
	public List<LexiconEntry> SelectedCategory;
	public LexiconPageBase[] SelectedPages;
	/************************************************************************************************************************************/
	public static final ResourceLocation Background1 = new ResourceLocation(Reference.MODID + ":textures/gui/LexiconGUI.png");
	
	boolean isMainScreen, isEntryScreen, isPagesScreen;
	public int xSize, ySize, PageIndex;

	public GuiLexicon() {

		this.xSize = 145;
		this.ySize = 179;

		this.isMainScreen = true;
		this.isEntryScreen = false;
		this.isPagesScreen = false;
		this.PageIndex = 0;
		
		this.initGeneratingCategory();
		
	}


	@Override
	public void initGui() {
		int guiX = ((width - xSize) / 2) - xSize/2;
		int guiY = (height - ySize) / 2;

		buttonList.clear();

		 
		if(this.isMainScreen)
		{
			//new ResourceLocation(Reference.MODID + ":textures/items/bucket_purified_water.png")
			buttonList.add(this.ButtonGeneratingCategory = new GuiButtonCategory(0, guiX + 20, guiY + 15, "textures/gui/category/Generation of Pure Water.png"));
			buttonList.add(this.ButtonTransportationCategory = new GuiButtonCategory(3, guiX + 65, guiY + 15, "textures/gui/category/Water Transportation Category.png"));
			buttonList.add(this.ButtonWorldGenerationCategory = new GuiButtonCategory(4, guiX + 100, guiY + 20, "textures/gui/category/World Generation Category.png"));
			

		}else if(this.isEntryScreen)
		{
			if(this.SelectedCategory != null)
			{
				for(int i =0; i < this.SelectedCategory.size(); i ++)
				{
					if(this.SelectedCategory.get(i) != null)
					{
						buttonList.add(new GuiButtonEntry(i + 10, guiX + 10, guiY + 20 + i*15, this.SelectedCategory.get(i), this));
					}
				}
			}

		}else if(this.isPagesScreen)
		{
			if(this.SelectedPages != null)
			{
				//TODO: Draw next page arrows 
				buttonList.add(new GuiButtonPageChanger(2,guiX + 34, guiY + 158 , true ,this)); //back
				buttonList.add(new GuiButtonPageChanger(1, guiX + 86 + xSize, guiY + 158, false,this)); // front
				
			}
		}
		super.initGui();
	}

	

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		GL11.glColor4f(1, 1, 1, 1);
		
		this.mc.getTextureManager().bindTexture(Background1);
		Tessellator tessellator = Tessellator.instance;
		//this draws other half of book
        tessellator.startDrawingQuads();                                               
        tessellator.addVertexWithUV(guiX-xSize/2, guiY, this.zLevel, (float) xSize/256, 0.0f); //left bottom
        tessellator.addVertexWithUV(guiX-xSize/2, guiY+ySize, this.zLevel, (float) xSize/256, (float) ySize/256); //left top
        tessellator.addVertexWithUV(guiX+xSize/2, guiY+ySize, this.zLevel, 0.0f, (float) ySize/256); //right top
        tessellator.addVertexWithUV(guiX+xSize/2, guiY, this.zLevel, 0.0f, 0.0f); //right bottom
        tessellator.draw();
		drawTexturedModalRect(guiX+xSize/2, guiY, 0, 0, this.xSize, this.ySize);		
		
		if(this.isMainScreen)
		{
			if(this.ButtonGeneratingCategory.isHovering())
			{
				String[] text = {"Pure Water Generation"};
				List temp = Arrays.asList(text);
				drawHoveringText(temp, guiX + 5, guiY - 3, fontRendererObj);
			}else if(this.ButtonTransportationCategory.isHovering())
			{
				String[] text = {"Pure Water Transportation"};
				List temp = Arrays.asList(text);
				drawHoveringText(temp, guiX + 5, guiY - 3, fontRendererObj);
			}else if(this.ButtonWorldGenerationCategory.isHovering())
			{
				String[] text = {"World Generation"};
				List temp = Arrays.asList(text);
				drawHoveringText(temp, guiX + 5, guiY - 3, fontRendererObj);
			}
	
		}else if(this.isEntryScreen)
		{
			
		}else if(this.isPagesScreen)
		{
			if(this.SelectedPages != null)
			{
				if(this.SelectedPages[PageIndex] instanceof ILexiconPage)
				{
					//if we're at the end of index, render only one page
					if((SelectedPages.length%2 == 1) && (SelectedPages.length-1 == PageIndex)){
						ILexiconPage page = (ILexiconPage) this.SelectedPages[PageIndex];
						PageText.setPageIndex(PageIndex);
						page.renderScreen(this);
					}
					//if we're in middle, render two pages
					if(SelectedPages.length-1 > PageIndex){
						ILexiconPage page = (ILexiconPage) this.SelectedPages[PageIndex];	
						PageText.setPageIndex(PageIndex);
						page.renderScreen(this);
						ILexiconPage page2 = (ILexiconPage) this.SelectedPages[PageIndex+1];
						PageText.setPageIndex(PageIndex+1);
						page2.renderScreen(this);
					}
					//if there is no record don't render anything
					else if((SelectedPages.length%2 == 1) && (SelectedPages.length < 1)){
						
					}
					//if something is wrong, render one page
					else {
						ILexiconPage page = (ILexiconPage) this.SelectedPages[PageIndex];
					}
					//
					//page.renderScreen(this);
					//this.fontRendererObj.drawString((this.PageIndex + 1) +"/" + this.SelectedPages.length, guiX - xSize/2 + 64, guiY + 161, Color.WHITE.getRGB());
				}
			}
		}
		

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean doesGuiPauseGame() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected void actionPerformed(GuiButton button) {

		if(button.id == 0)
		{//generation
			this.isMainScreen = false;
			this.isEntryScreen = true;
			this.SelectedCategory = this.GeneratingCategory;
			
			initGui();
		}else if(button.id == 3)
		{//transportation
			this.isMainScreen = false;
			this.isEntryScreen = true;
			this.SelectedCategory = this.TransportationCategory;
			
			initGui();
		}else if(button.id == 4)
		{//world gen
			this.isMainScreen = false;
			this.isEntryScreen = true;
			this.SelectedCategory = this.WorldGenerationCategory;
			
			initGui();
		}else if(button.id == 1)
		{
		//Page Forward
		
			if((this.SelectedPages.length - 2) > this.PageIndex)
			{
				//this.PageIndex++;
				this.PageIndex += 2;
				initGui();
			}
			
		}else if(button.id == 2)
		{
		//Page Backward
			if(0 < this.PageIndex)
			{
				//this.PageIndex--;
				this.PageIndex -= 2;
				initGui();
			}
		
		}else if(button.id >= 10)
		{
			
			this.isMainScreen = false;
			this.isEntryScreen = false;
			this.isPagesScreen = true;
			this.SelectedPages = this.SelectedCategory.get(button.id - 10).PageSequence;
			this.PageIndex = 0;
			
			initGui();
		}
		


		super.actionPerformed(button);
	}
	
	
	
	public void initGeneratingCategory()
	{
		this.GeneratingCategory.clear();
		this.GeneratingCategory.add(new LexiconEntry("Purifier", new ResourceLocation(Reference.Purifier_Texture), 
				new PageText(Reference.LexiconData.Purifier_Page_1_Text), 
				new PageNormalCraftingRecipe(new ItemStack[]{new ItemStack(Items.baked_potato), new ItemStack(Items.baked_potato), new ItemStack(Items.baked_potato), new ItemStack(Items.baked_potato), new ItemStack(Items.baked_potato), new ItemStack(Items.baked_potato), new ItemStack(Items.baked_potato), new ItemStack(Items.baked_potato) ,new ItemStack(Items.baked_potato)}, new ItemStack(HydromancyBlocksHandler.Block_Purifier), this),
				new PageText(Reference.LexiconData.Purifier_Page_2_Text)));
				PageNormalCraftingRecipe.setCraftingRecipeSubtext(Reference.LexiconData.Purifier_Page_3_Text);
				PageNormalCraftingRecipe.setRecipeY(-65);
	}
	
	
	public void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_)
    {
			RenderHelper.disableStandardItemLighting();			
			
	        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
	        this.zLevel = 200.0F;
	        itemRender.zLevel = 200.0F;
	        FontRenderer font = null;
	        if (p_146982_1_ != null) font = p_146982_1_.getItem().getFontRenderer(p_146982_1_);
	        if (font == null) font = fontRendererObj;
	        itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_);
	        itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_ , p_146982_4_);
	        this.zLevel = 0.0F;
	        itemRender.zLevel = 0.0F;       
    }
}