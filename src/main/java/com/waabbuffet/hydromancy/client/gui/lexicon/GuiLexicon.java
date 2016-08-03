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
	
	
	public GuiButtonCategory ButtonGeneratingCategory;
	
	
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
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		buttonList.clear();

		 
		if(this.isMainScreen)
		{
			buttonList.add(this.ButtonGeneratingCategory = new GuiButtonCategory(0, guiX + 20, guiY + 15,""));

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
				buttonList.add(new GuiButtonPageChanger(1, guiX + 86, guiY + 158, false,this)); // front
				
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
		drawTexturedModalRect(guiX, guiY, 0, 0, this.xSize, this.ySize);

		
		
		if(this.isMainScreen)
		{
			if(this.ButtonGeneratingCategory.isHovering())
			{
				String[] text = {"Pure Water Generation"};
				List temp = Arrays.asList(text);
				drawHoveringText(temp, mouseX, mouseY, fontRendererObj);
			}
	
		}else if(this.isEntryScreen)
		{
			
		}else if(this.isPagesScreen)
		{
			if(this.SelectedPages != null)
			{
				if(this.SelectedPages[PageIndex] instanceof ILexiconPage)
				{
					
					ILexiconPage page = (ILexiconPage) this.SelectedPages[PageIndex];
					page.renderScreen(this);
					this.fontRendererObj.drawString((this.PageIndex + 1) +"/" + this.SelectedPages.length, guiX + 64, guiY + 161, Color.WHITE.getRGB());
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
		{
			this.isMainScreen = false;
			this.isEntryScreen = true;
			this.SelectedCategory = this.GeneratingCategory;
			
			initGui();
		}else if(button.id == 1)
		{
		//Page Forward
		
			if((this.SelectedPages.length - 1) > this.PageIndex)
			{
				this.PageIndex++;
				initGui();
			}
			
		}else if(button.id == 2)
		{
		//Page Backward
			if(0 < this.PageIndex)
			{
				this.PageIndex--;
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