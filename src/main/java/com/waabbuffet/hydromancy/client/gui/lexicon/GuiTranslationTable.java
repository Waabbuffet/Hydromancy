package com.waabbuffet.hydromancy.client.gui.lexicon;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonChosenWords;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonPageChanger;
import com.waabbuffet.hydromancy.inventory.containers.ContainerPurifier;
import com.waabbuffet.hydromancy.inventory.containers.ContainerTranslationTable;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.util.Reference;
import com.waabbuffet.hydromancy.util.TranslationTableUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GuiTranslationTable extends GuiContainer {

	private IInventory playerInv;
	private TileEntityTranslationTable te;
	private int xDefaultSize;
	private int yDefaultSize;

	boolean hasPaper, OldhasPaper, hasTranslationPage, isWordSelectionPage;
	String ChosenWords = "";
	static ContainerTranslationTable containerTable ;

	List<String> KnownWords;

	int pageNumber = 0;

	public GuiTranslationTable(IInventory playerInv, TileEntityTranslationTable te, EntityPlayer player) {
		super(containerTable = new ContainerTranslationTable(playerInv, te));

		this.playerInv = playerInv;
		this.te = te;
		
		xDefaultSize = 176; // 176
		yDefaultSize = 166; //to be used eventually 166
		
		//this is size of bounding box
		this.xSize = 330; //made it super large to fit everything 
		this.ySize = yDefaultSize;

		if(this.te.getChosenWords() != null)
		{
			this.ChosenWords = this.te.getChosenWords();
		}
		this.isWordSelectionPage = false;

		KnownWords = HydromancyPlayerProperties.get(player).getKnownWords();
		
		this.hasPaper = this.te.hasPaper(); //determines if should display the lost paper GUI
		//this.hasTranslationPage = this.te.hasMatchingTranslationStone();

		this.OldhasPaper = hasPaper;

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		int guiX = (width - xDefaultSize) / 2;
		int guiY = (height - ySize) / 2;

		this.hasPaper = this.te.hasPaper();
		//this.hasTranslationPage = this.te.hasMatchingTranslationStone();
		
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table.png"));
		this.drawTexturedModalRect(guiX + (hasPaper ?  85 : 0), guiY, 0, 0, 256, this.ySize);



		if(this.hasPaper) //displays the lost paper GUI
		{							
			String CodedText = "";
			int PageNumber = 0;
			if(this.te.getStackInSlot(0) != null)
			{
				if(this.te.getStackInSlot(0).hasTagCompound())
				{
					CodedText = this.te.getStackInSlot(0).getTagCompound().getString("UndecipheredText");
					PageNumber = this.te.getStackInSlot(0).getTagCompound().getInteger("PageNumber");
				}
			}

			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/GUIShopMaybe.png"));
			this.drawTexturedModalRect(guiX - 85, guiY - 40, 44, 3, 168, 233); // 212 , 236

			FontRenderer fontrenderer = this.mc.fontRenderer; 
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


		}

		if(this.isWordSelectionPage)
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
	} 

	@Override
	public void onGuiClosed() {
		// TODO Auto-generated method stub
		this.te.setChosenWords(ChosenWords);
		super.onGuiClosed();
	}


	@Override
	public void initGui() {

		int guiX = (width - xDefaultSize) / 2;
		int guiY = (height - ySize) / 2;

		buttonList.clear();


		this.hasPaper = this.te.hasPaper();
		//this.hasTranslationPage = this.te.hasMatchingTranslationStone();

		String CodedText = "";
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
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		if(button.id == 0)
		{
			this.isWordSelectionPage = true;

			initGui();
		}else if(button.id == 1)
		{
			if(this.pageNumber > 0)
			{
				this.pageNumber --;
				initGui();
			}
		}else if(button.id == 2)
		{
			if((this.pageNumber * 23) < this.KnownWords.size())
			{
				this.pageNumber++;
				initGui();
			}
		}else if(button.id == 3)
		{
			this.ChosenWords = "";
			initGui();

		}else if(button.id == 4)
		{
			String Words[] = this.ChosenWords.split(" ");
			this.ChosenWords = "";

			for(int i =0; i < Words.length - 1; i ++)
			{
				if(Words[i] != " ")
					this.ChosenWords = ChosenWords + Words[i] + " "; 	
			}
			this.ChosenWords = this.ChosenWords.trim();

			initGui();
		}else if(button.id == 5)
		{
			if(this.ChosenWords != null)
			{
				if(this.te.getStackInSlot(0).hasTagCompound())
				{
					if(this.ChosenWords.contains(this.te.getStackInSlot(0).getTagCompound().getString("UndecipheredText")))
					{
						//Send Packet here but for now just sysout!
						Minecraft.getMinecraft().thePlayer.closeScreen();
						Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("You did it!"));
					}
				}
			}

			initGui();
		}else if(button.id >= 10)
		{
			int wordID = button.id - 10;

			if(this.ChosenWords.split(" ").length < 11)
			{
				this.ChosenWords = this.ChosenWords + " " + this.KnownWords.get(wordID);
				this.ChosenWords = this.ChosenWords.trim();
			}
		}

		super.actionPerformed(button);
	}	
}



