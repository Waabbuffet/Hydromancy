package com.waabbuffet.hydromancy.client.gui.lexicon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;
import com.waabbuffet.hydromancy.capabilities.translationTable.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.client.gui.HydromancyResearchHandler;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.GuiContainerVisibility;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.TranslationMinigame;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonWordToTranslate;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonArrowDown;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonArrowUp;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonChooseWord;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonChosenWords;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonPageChanger;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonTabResearch;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonTabTranslation;
import com.waabbuffet.hydromancy.inventory.containers.ContainerTranslationTable;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SSyncHydromancyPlayerProperties;
import com.waabbuffet.hydromancy.packet.packets.SUpdateTranslationTable;
import com.waabbuffet.hydromancy.tileEntity.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.util.Reference;
import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GuiTranslationTable extends GuiContainerVisibility {

	private IInventory playerInv;
	private TileEntityTranslationTable te;
	private int xDefaultSize,yDefaultSize,extraY;
	int[] yCoord;
	String[] wordOptions;
	List<GuiButtonChooseWord> whole = new ArrayList<GuiButtonChooseWord>();

	private boolean researchActive, hasTranslationPage, wordsTBC, mouseHasBeenPressedA,mouseHasBeenPressedB,arrUp,arrDown;
	String ChosenWords = "";
	static ContainerTranslationTable containerTable;
	private GuiButtonTabTranslation TabTranslation;
	private GuiButtonTabResearch TabResearch;
	private GuiButtonArrowUp upArrow;
	private GuiButtonArrowDown downArrow;
	private HydromancyResearchHandler ResearchHandler;
	private EntityPlayer player;
	private GuiButtonWordToTranslate[] words;
	private Random rnd = new Random();
	private GuiButtonChooseWord[] fakewords;
	private GuiButtonChooseWord therightone;
	private int chosenid = -1;
	private TranslationMinigame minigame;
	String pressedbut = null;
	//private FontRenderer fontrenderer;

	List<String> KnownWords;

	int pageNumber = 0;
	private byte[] glyphWidth = new byte[65536];
	
	private HydromancyPlayerProperties playerProperties;

	public GuiTranslationTable(IInventory playerInv, TileEntityTranslationTable te, EntityPlayer player) {
		super(containerTable = new ContainerTranslationTable(playerInv, te));
		this.player = player;
		//this.zLevel = 30f;
		this.playerInv = playerInv;
		this.te = te;
		
		xDefaultSize = 182; // 182
		yDefaultSize = 172; //to be used eventually 172
		
		//this is size of bounding box
		this.xSize = 182; 
		this.ySize = yDefaultSize;

		/*if(this.te.getChosenWords() != null)
		{
			this.ChosenWords = this.te.getChosenWords();
		}
		this.isWordSelectionPage = false;*/

		//KnownWords = playerProperties.getKnownWords();
		//String[] researchesUpdated = playerProperties.getResearchStates();
		
		playerProperties = (HydromancyPlayerProperties) player.getCapability(HydromancyCapabilities.PLAYER_PROPERTIES, null);
		//this.hasPaper = this.te.hasPaper(); //determines if should display the lost paper GUI
		this.researchActive = this.te.researchInProgress;
		wordsTBC = playerProperties.getWTBC();
		//this.hasTranslationPage = this.te.hasMatchingTranslationStone();

		//this.OldhasPaper = hasPaper;

	}

	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		int guiX = (width - xDefaultSize) / 2;
		int guiY = (height - ySize) / 2;
		//this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table.png"));
		//this.drawTexturedModalRect(guiX, guiY, 0, 0, 176, this.ySize);
		//this.drawTexturedModalRect(guiX+176, guiY, 176, 0, 80, this.ySize);
		//System.out.println(te.getStackSize(0));
		if(te.isTabResearch == false) {	
			TabResearch.drawSwitchTab(mc, mouseX, mouseY);
			
			//this.hasPaper = this.te.hasPaper();
			this.researchActive = this.te.researchInProgress;
			//this.hasTranslationPage = this.te.hasMatchingTranslationStone();
			
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table.png"));
			this.drawTexturedModalRect(guiX, guiY, 0, 0, 182, this.ySize);

			if(this.researchActive) //displays the lost paper GUI
			{							
	
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f); // 212 , 236
				this.drawTexturedModalRect(guiX+31, guiY+13, 0, 172, 120, 33);
				this.drawTexturedModalRect(guiX+31, guiY+46, 0, 172, 120, 33);
				
				if(minigame != null && minigame.pageText != null && minigame.text != null)
				{
					int sizeX = 118;
					int offsetX = 0;
					int offsetY = 0;
					int x = guiX+33;
					int y = guiY+49;
					
					char[] numbercheck = TranslationTableUtils.convertToBasicLatin(minigame.text).toCharArray();
					for(int i = 0; i < numbercheck.length; i++)
					{
						if(x + offsetX + fontRendererObj.getStringWidth(String.valueOf(numbercheck[i])) >= (x+sizeX))
						{
							offsetY += fontRendererObj.FONT_HEIGHT+1;
							offsetX = 0;
						}	
						
						if(Character.isLetter(numbercheck[i]))
						{
							fontRendererObj = this.mc.standardGalacticFontRenderer;
						}
						else
						{
							fontRendererObj = this.mc.fontRendererObj;
						}
							
						fontRendererObj.drawString(String.valueOf(numbercheck[i]), x + offsetX, guiY + 15 + offsetY, 0xFFFFFF);
						
						offsetX += fontRendererObj.getStringWidth(String.valueOf(numbercheck[i]));
					}
					offsetY = 0;
					offsetX = 0;
					if(!wordsTBC){
						for(int i = 0; i < minigame.textToTranslationA.length; i++)
						{
							if(x + offsetX + fontRendererObj.getStringWidth(minigame.textToTranslationA[i]) >= (x+sizeX))
							{
								offsetY += fontRendererObj.FONT_HEIGHT+1;
								offsetX = 0;
							}
							
							if(minigame.textToTranslationB[i] == false)
							{
								fontRendererObj = this.mc.standardGalacticFontRenderer;
								words[i].drawButton(mc, mouseX, mouseY);
							}
							else
							{
								fontRendererObj = this.mc.fontRendererObj;
								words[i] = null;
							}
							
							fontRendererObj.drawString(minigame.textToTranslationA[i], x + offsetX, y + offsetY, 0xFFFFFF);
							offsetX += fontRendererObj.getStringWidth(minigame.textToTranslationA[i]) + fontRendererObj.getStringWidth(" ");
							//System.out.println(offsetX);
							
							if(words[i] != null && words[i].hovering && Mouse.isButtonDown(0) == true && words[i].mousePressed(mc, mouseX, mouseY))
							{
								mouseHasBeenPressedA = true;
								chosenid = i;
							}
							if(mouseHasBeenPressedA && !Mouse.isButtonDown(0))
							{
								mouseHasBeenPressedA = false;
								playerProperties.setWTBC(true);
								this.initGui();
							}
						}
					}
					else
					{
						/*upArrow.id = 2;
						downArrow.id = 3;*/
						if(!buttonList.contains(upArrow)){
							buttonList.add(upArrow);
						}
						if(!buttonList.contains(downArrow)){
							buttonList.add(downArrow);
						}
						
						fontRendererObj.drawSplitString(minigame.textToTranslationA[chosenid], x, y, sizeX, 0xFFFFFF);
						
						if(whole == null || whole.size() == 0)
						{
							whole.addAll(Arrays.asList(fakewords));
							whole.add(therightone);
						}
						else
						{
							for(GuiButtonChooseWord but : whole)
							{
								if (but != null && but.yPosition >= (49 + guiY) && but.yPosition <= (49 + guiY + fontRendererObj.FONT_HEIGHT*3 + 2)){		
									but.drawButton(mc, mouseX, mouseY);
								}
								if(extraY != 0)
									but.yPosition += extraY;
								
								if(but.hovering && Mouse.isButtonDown(0) == true && but.mousePressed(mc, mouseX, mouseY))
								{
									mouseHasBeenPressedA = true;
									pressedbut = but.displayString;
								}
								
								if(mouseHasBeenPressedA == true && pressedbut != null && !Mouse.isButtonDown(0))
								{
									mouseHasBeenPressedA = false;
									minigame.textToTranslationA[chosenid] = pressedbut;
									minigame.textToTranslationB[chosenid] = true;
									playerProperties.setWTBC(false);
									playerProperties.setChosenId(-1);
									playerProperties.setWordOptions((String[])null);
									playerProperties.setChosenY((int[])null);
									pressedbut = null;
									chosenid = -1;
									wordOptions = null;
									yCoord = null;
									saveTranslation();
									saveWordSequence();
									initGui();
									whole.clear();
									break;
								}
							}
							for(int i = 0; i < whole.size(); i++)
							{
								if(whole.get(i).yPosition < guiY + 49){
									arrUp = true;
									break;
								}
							}
							for(int i = 0; i < whole.size(); i++)
							{
								if(whole.get(i).yPosition > guiY + 49 + 3*fontRendererObj.FONT_HEIGHT + 2)
								{
									arrDown = true;
									break;
								}
							}
					
						}
						
						if(arrUp)
						{
							upArrow.drawAButton(mc, mouseX, mouseY);
							arrUp = false;
						}
						else
						{
							buttonList.remove(upArrow);
						}
						if(arrDown)
						{
							downArrow.drawAButton(mc, mouseX, mouseY);
							arrDown = false;
						}
						else
						{
							buttonList.remove(downArrow);
						}
						
						if(extraY != 0){
							extraY = 0;
						}
					}
				}
				else
				{
					initGui();
				}
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			}	
			TabTranslation.drawSwitchTab(mc, mouseX, mouseY);
		} else {
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			
			
			//this.zLevel = 8.0f;
			//ResearchHandler.drawResearch(mc, mouseX, mouseY);
					
		}

	} 
	
	@Override
	public void onGuiClosed() {
		HydromancyPacketHandler.INSTANCE.sendToServer(new SUpdateTranslationTable(te));
		HydromancyPacketHandler.INSTANCE.sendToServer(new SSyncHydromancyPlayerProperties(player));
		super.onGuiClosed();
	}


	@Override
	public void initGui() {
		int guiX = (width - xDefaultSize) / 2;
		int guiY = (height - ySize) / 2;
		TabTranslation = new GuiButtonTabTranslation(0,guiX,guiY-29,te.isTabResearch);
		TabResearch = new GuiButtonTabResearch(1,guiX+27,guiY-29,te.isTabResearch);
		upArrow = new GuiButtonArrowUp(2,guiX+138,guiY+49);
		downArrow = new GuiButtonArrowDown(3,guiX+138,guiY+69);
		ResearchHandler = new HydromancyResearchHandler(5,guiX+15,guiY+19,153,134,this.te,player);
		ResearchHandler.sendInstances(TabResearch, TabTranslation);
		
		buttonList.clear();
		buttonList.add(TabResearch);
		buttonList.add(TabTranslation);
		
		if(te.isTabResearch == false) {
			//this.hasPaper = this.te.hasPaper();
			this.researchActive = this.te.researchInProgress; //change to HPP | bound to tile entity only for testing purposes
			if(this.researchActive)
			{	
				int sizeX = 118;
				int sizeY = 32;
				int offsetX = 0;
				int offsetY = 0;
				int x = guiX+33;
				int y = guiY+49;

				if(minigame == null)
				{
					minigame = new TranslationMinigame(te, te.activeResearch, player);
				}
				
				wordsTBC = playerProperties.getWTBC();
				
				if(wordsTBC)
				{
					buttonList.add(upArrow);
					buttonList.add(downArrow);
					
					if(playerProperties.getChosenId() != -1 && playerProperties.getWordOptions() != null && playerProperties.getWordOptions().length > 0)
					{
						loadWordSequence();
					}
					else
					{
						newWordSequence();
					}
					
					if(minigame.text == null)
					loadTranslation();
					
					fontRendererObj = mc.standardGalacticFontRenderer;
					fakewords = new GuiButtonChooseWord[wordOptions.length];
					for(int i = 0; i < wordOptions.length; i++){
						if(playerProperties.getChosenId() == -1 || playerProperties.getWordOptions() == null || playerProperties.getWordOptions().length == 0){
							wordOptions[i] = minigame.pageText.replace("\\t","").replaceAll(" +", " ").trim().split(" ")[rnd.nextInt(minigame.pageText.replace("\\t","").replaceAll(" +", " ").trim().split(" ").length)];
						}
						fakewords[i] = new GuiButtonChooseWord(x + fontRendererObj.getStringWidth(minigame.textToTranslationA[chosenid]) + 10, y + yCoord[i], fontRendererObj.getStringWidth(wordOptions[i]), fontRendererObj.FONT_HEIGHT, wordOptions[i]);
						offsetY += fontRendererObj.FONT_HEIGHT;
						
					}
					//System.out.println(Arrays.toString(xCoord) + " " + xCoord[xCoord.length-1]);
					therightone = new GuiButtonChooseWord(x + fontRendererObj.getStringWidth(minigame.textToTranslationA[chosenid]) + 10, y + yCoord[yCoord.length-1], fontRendererObj.getStringWidth(minigame.textToTranslationA[chosenid]), fontRendererObj.FONT_HEIGHT, minigame.textToTranslationA[chosenid]);

					saveWordSequence();
				}
				else
				{
					if(playerProperties.getTextToTranslationA() != null && playerProperties.getTextToTranslationA().length > 0)
					{
						loadTranslation();
						System.out.println(minigame.text);
					}
					System.out.println(Arrays.toString(minigame.textToTranslationB) + " " + contains(minigame.textToTranslationB, false));
					//System.out.println(!contains(minigame.textToTranslationB, false) + " | " + (te.getStackInSlot(2).isItemEqual(new ItemStack(Items.paper))) + " | " + (te.getStackInSlot(3) == null));
					if(!contains(minigame.textToTranslationB, false) && te.handler.getStackInSlot(2).isItemEqual(new ItemStack(Items.PAPER)) && te.handler.getStackInSlot(3) == null)
					{
						System.out.println("it is translated");
						if(String.join(" ", minigame.textToTranslationA).contentEquals(minigame.text))
						{
							te.decrStackSize(2, 1);
							ItemStack stack = new ItemStack(HydromancyItemHandler.translatedPage,1);
							stack.setTagCompound(getNBTFromResearch());
							te.setInventorySlotContents(3, stack);
							researchActive = false;
							te.researchInProgress = false;
							playerProperties.setResearchStateResearched(minigame.researchName);
							minigame = null;
							playerProperties.disposeMinigame();
						}
						else
						{
							te.decrStackSize(2, 1);
							ItemStack stack = new ItemStack(HydromancyItemHandler.translatedPage,1);
							stack.setTagCompound(getNBTFromResearch());
							te.setInventorySlotContents(3, stack);
							researchActive = false;
							te.researchInProgress = false;
							playerProperties.setResearchStateAvailable(minigame.researchName);
							minigame = null; //this destroys all minigame variables
							playerProperties.disposeMinigame();
						}
						HydromancyPacketHandler.INSTANCE.sendToServer(new SUpdateTranslationTable(te));
						initGui();
						return;
					}
					
					words = new GuiButtonWordToTranslate[minigame.textToTranslationB.length];
					for(int i = 0; i < minigame.textToTranslationA.length; i++)
					{
						if(minigame.textToTranslationB[i] == false)
						{
							fontRendererObj = this.mc.standardGalacticFontRenderer;
						}
						else
						{
							fontRendererObj = this.mc.fontRendererObj;
						}
						
						if(x + offsetX + fontRendererObj.getStringWidth(minigame.textToTranslationA[i]) >= (x+sizeX))
						{
							offsetY += fontRendererObj.FONT_HEIGHT+1;
							offsetX = 0;
						}
						
						if(minigame.textToTranslationB[i] == false)
						{
							words[i] = new GuiButtonWordToTranslate(x + offsetX, y + offsetY, fontRendererObj.getStringWidth(minigame.textToTranslationA[i]), fontRendererObj.FONT_HEIGHT, minigame.textToTranslationA[i]);
						}
						offsetX += fontRendererObj.getStringWidth(minigame.textToTranslationA[i]) + fontRendererObj.getStringWidth(" ");
						//System.out.println(offsetX);
					}
					
					saveTranslation();
				}
				
			}
			this.slotVisibility = true;
		} else {
			buttonList.add(ResearchHandler);			
			this.slotVisibility = false;
			//buttonList.add(new HydromancyResearchHandler(8,guiX+13,guiY+13,151,140,this));
		}
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {

		if(button.id == 0)
		{
			te.isTabResearch = false;
			initGui();
		}else if(button.id == 1)
		{
			te.isTabResearch = true;
			initGui();
		}
		else if(button.id == 2)
		{
			extraY += 10;
		}
		else if(button.id == 3)
		{
			extraY -= 10;
		}
	}

	private void newWordSequence()
	{
		wordOptions = new String[3+rnd.nextInt(3)];
		int guiX = (width - xDefaultSize) / 2;
		int guiY = (height - ySize) / 2;
		int sizeX = 118;
		int sizeY = 32;
		int offsetX = 0;
		int offsetY = 0;
		int x = guiX+30;
		int y = guiY+49;
		
		List<Integer> yCoords = new ArrayList<Integer>();
		for(int i = 0; i < wordOptions.length + 1; i++)
		{
			yCoords.add(offsetY);
			offsetY += fontRendererObj.FONT_HEIGHT + 1;
			//System.out.println(offsetX);
		}
		List<Integer> usedNumbersY = new ArrayList<Integer>();
		yCoord = new int[yCoords.size()];
		Arrays.fill(yCoord, -1);
		boolean can;
		int oneONums;
		for(int i = 0; i < yCoord.length; i++)
		{
			while(yCoord[i] == -1){
				can = true;
				oneONums = rnd.nextInt(yCoords.size());
				for(int j = 0; j < usedNumbersY.size(); j++)
				{
					if(oneONums == usedNumbersY.get(j))
					{
						can = false;
						break;
					}
				}
				if(can == true){
					yCoord[i] = yCoords.get(oneONums);
					usedNumbersY.add(oneONums);
				}
			}
		
		}
	}
	
	private void loadWordSequence()
	{
		wordOptions = playerProperties.getWordOptions();
		chosenid = playerProperties.getChosenId();
		yCoord = playerProperties.getChosenY();
	}
	
	private void saveWordSequence()
	{
		playerProperties.setChosenId(chosenid);
		playerProperties.setWordOptions(wordOptions);
		playerProperties.setChosenY(yCoord);
	}	
	
	private void loadTranslation()
	{
		minigame.textToTranslationA = playerProperties.getTextToTranslationA();
		minigame.textToTranslationB = playerProperties.getTextToTranslationB();
		minigame.text = playerProperties.getTextToTranslation();
		minigame.pageText = playerProperties.getPageText();
		minigame.researchName = playerProperties.getResearchName();
	}
	
	private void saveTranslation()
	{
		playerProperties.setTextToTranslationA(minigame.textToTranslationA);
		playerProperties.setTextToTranslationB(minigame.textToTranslationB);
		playerProperties.setTextToTranslation(minigame.text);
		playerProperties.setPageText(minigame.pageText);
		playerProperties.setResearchName(minigame.researchName);
	}
	
	private boolean contains(boolean[] arr, boolean var)
	{
		if(arr != null)
			for(boolean bool : arr)
			{
				if(bool == var)
				{
					return true;
				}
			}
		return false;

	}
	
	private NBTTagCompound getNBTFromResearch()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("research", String.join(" ", minigame.textToTranslationA).contentEquals(minigame.text) ? minigame.researchName : "scribble");
		return nbt;
	}
}
/* UNUSED CODE - just wanted to have order in above methods so I put it here
 * 
 * 
 * 
 *dgbl
 *
 *String CodedText = "";
 *	int PageNumber = 0;
 *	if(this.te.getStackInSlot(0) != null)
 *	{
 *		if(this.te.getStackInSlot(0).hasTagCompound())
 *		{
 *			CodedText = this.te.getStackInSlot(0).getTagCompound().getString("UndecipheredText");
 *			PageNumber = this.te.getStackInSlot(0).getTagCompound().getInteger("PageNumber");
 *		}
 *	}
 *	FontRenderer fontrenderer = this.mc.fontRenderer; 
				int k1 = 6839882;
	
				fontrenderer.drawSplitString("Lexicon Lost Page #: " + PageNumber + "/100",  guiX - 75, guiY - 35, 150, (k1 & 16711422) >> 1);
				fontrenderer.drawSplitString("This text seems to be written in an old language that says",  guiX - 65, guiY - 10, 130, (k1 & 16711422) >> 1);
	
				fontrenderer = this.mc.standardGalacticFontRenderer;
				fontrenderer.drawSplitString(CodedText,  guiX - 65, guiY + 25, 125, (k1 & 16711422) >> 1);
	
				fontrenderer = this.mc.fontRenderer; 
				fontrenderer.drawSplitString("I should match the words with words I already know",  guiX - 65, guiY + 60, 130, (k1 & 16711422) >> 1);
	
				fontrenderer = this.mc.standardGalacticFontRenderer; //Draws the enchantment text
				fontrenderer.drawSplitString(this.ChosenWords,  guiX + (this.hasPaper ?  133 : 48), guiY + 10, 120, (k1 & 16711422) >> 1);
	
				fontrenderer = this.mc.fontRenderer; // changes it back to normal
				fontrenderer.drawSplitString(this.ChosenWords,  guiX + (this.hasPaper ?  133 : 48), guiY + 45, 120, (k1 & 16711422) >> 1);
 *
 *	
 *			if(this.isWordSelectionPage)
			{
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/WordSelectionPage.png"));
				this.drawTexturedModalRect(guiX - 150, guiY - 40, 10, 10, 70, this.ySize + 70);
	
			}
			
			if(this.OldhasPaper != this.hasPaper) // if the paper changes update the initGui/container
			{
				this.OldhasPaper = this.hasPaper;
				this.isWordSelectionPage = false;
				this.containerTable.slotList(hasPaper);
				this.initGui();
				
			}
 *
 *ig
 *
 *----------------------------------------OLD CODE----------------------------------------
			//this.hasTranslationPage = this.te.hasMatchingTranslationStone();
	
			/*String CodedText = "";
			int PageNumber = 0;
			if(this.te.getStackInSlot(0) != null)
			{
				if(this.te.getStackInSlot(0).hasTagCompound())
				{
					CodedText = this.te.getStackInSlot(0).getTagCompound().getString("UndecipheredText");
					PageNumber = this.te.getStackInSlot(0).getTagCompound().getInteger("PageNumber");
				}
				//	HydromancyPlayerProperties.get(Minecraft.getMinecraft().thePlayer).addWord(CodedText.split(" ")[0]);
			}
	
			if(this.hasPaper && !this.isWordSelectionPage)
			{
				buttonList.add(new GuiButton(0 , guiX - 60, guiY + 80, 65, 20, "Show List"));
			}
	
			if(this.isWordSelectionPage)
			{
				if(this.pageNumber * 23 + 23 < this.KnownWords.size())
				{
	
					buttonList.add(new GuiButton(2 , guiX - 120, guiY - 38 + 230, 15, 15, "->")); 
					for(int i = 0; i < 23; i ++)
					{
						buttonList.add(new GuiButtonChosenWords(10 + i +this.pageNumber * 23, guiX - 150, guiY - 38 + i * 10, this.KnownWords.get(i + this.pageNumber * 23), this.hasTranslationPage,  CodedText));
					}
				}else 
				{
					for(int i = this.pageNumber * 23; i < this.KnownWords.size(); i ++)
					{
						buttonList.add(new GuiButtonChosenWords(10 + i, guiX - 150, guiY - 38 + (i - this.pageNumber*23)* 10, this.KnownWords.get(i), this.hasTranslationPage, CodedText));
					}
				}
	
				buttonList.add(new GuiButton(1 , guiX - 150, guiY - 38 + 230, 15, 15, "<-")); 		
				buttonList.add(new GuiButton(3 , guiX - 60, guiY + 100, 95, 20, "Clear ALL words")); 
				buttonList.add(new GuiButton(4 , guiX - 60, guiY + 120, 95, 20, "Clear LAST word")); 
				buttonList.add(new GuiButton(5 , guiX - 60, guiY + 140, 95, 20, "Check Translation")); 
			}
			super.initGui();
 *
 */



