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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GuiTranslationTable extends GuiContainer {

	private IInventory playerInv;
	private TileEntityTranslationTable te;

	boolean hasPaper, isWordSelectionPage;
	String ChosenWords = "";
	
	List<String> KnownWords;
	
	int pageNumber = 0;

	public GuiTranslationTable(IInventory playerInv, TileEntityTranslationTable te, EntityPlayer player) {
		super(new ContainerTranslationTable(playerInv, te));

		this.playerInv = playerInv;
		this.te = te;

		this.xSize = 176;
		this.ySize = 166;

		if(this.te.getChosenWords() != null)
		{
			this.ChosenWords = this.te.getChosenWords();
		}

		KnownWords = HydromancyPlayerProperties.get(player).getKnownWords();
		
		
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		//TODO: Client inventory is not synced when player moves lost paper/stone out of slots

		this.hasPaper = this.te.hasPaper(); //determines if should display the lost paper GUI


		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table.png"));
		this.drawTexturedModalRect(guiX + (this.hasPaper ?  85 : 0), guiY, 0, 0, this.xSize, this.ySize);


		if(this.hasPaper) //displays the lost paper GUI
		{
			String CodedText = "";
			int PageNumber = 0;
			if(this.te.getStackInSlot(0) != null)
			{
				CodedText = this.te.getStackInSlot(0).getTagCompound().getString("UndecipheredText");
				PageNumber = this.te.getStackInSlot(0).getTagCompound().getInteger("PageNumber");
			}
			
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/GUIShopMaybe.png"));
			this.drawTexturedModalRect(guiX - 85, guiY - 40, 44, 3, 168, 233); // 212 , 236
			
			FontRenderer fontrenderer = this.mc.fontRenderer; //Draws the enchantment text
			int k1 = 6839882;
			
			fontrenderer.drawSplitString("Lexicon Lost Page #: " + PageNumber + "/100",  guiX - 75, guiY - 35, 150, (k1 & 16711422) >> 1);
			fontrenderer.drawSplitString("This text seems to be written in an old language that says",  guiX - 65, guiY - 10, 130, (k1 & 16711422) >> 1);
			
			fontrenderer = this.mc.standardGalacticFontRenderer;
			fontrenderer.drawSplitString(CodedText,  guiX - 65, guiY + 25, 125, (k1 & 16711422) >> 1);
			
			fontrenderer = this.mc.fontRenderer; 
			fontrenderer.drawSplitString("I should match the words with words I already know",  guiX - 65, guiY + 60, 130, (k1 & 16711422) >> 1);
			
		}

		if(this.isWordSelectionPage)
		{
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/WordSelectionPage.png"));
			this.drawTexturedModalRect(guiX - 150, guiY - 40, 10, 10, 70, this.ySize + 70);
			
			
		}
		
		
		
			FontRenderer fontrenderer = this.mc.standardGalacticFontRenderer; //Draws the enchantment text
			int k1 = 6839882;
			fontrenderer.drawSplitString(this.ChosenWords,  guiX + (this.hasPaper ?  133 : 48), guiY + 10, 120, (k1 & 16711422) >> 1);
	
			fontrenderer = this.mc.fontRenderer; // changes it back to normal
			fontrenderer.drawSplitString(this.ChosenWords,  guiX + (this.hasPaper ?  133 : 48), guiY + 45, 120, (k1 & 16711422) >> 1);
		
		
	}
	
	@Override
	public void onGuiClosed() {
		// TODO Auto-generated method stub
		
		this.te.setChosenWords(ChosenWords);
		
		super.onGuiClosed();
	}
	
	
	@Override
	public void initGui() {
		
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		buttonList.clear();
		//TODO: Client inventory is not synced when player moves lost paper/stone out of slots

		this.hasPaper = this.te.hasPaper(); //determines if should display the lost paper GUI

		
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
					buttonList.add(new GuiButtonChosenWords(10 + i +this.pageNumber * 23, guiX - 150, guiY - 38 + i * 10, this.KnownWords.get(i + this.pageNumber * 23)));
				}
			}else 
			{
				for(int i = this.pageNumber * 23; i < this.KnownWords.size(); i ++)
				{
					buttonList.add(new GuiButtonChosenWords(10 + i, guiX - 150, guiY - 38 + (i - this.pageNumber*23)* 10, this.KnownWords.get(i)));
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
			
			if(this.ChosenWords.contains(this.te.getStackInSlot(0).getTagCompound().getString("UndecipheredText")))
			{
				//Send Packet here but for now just sysout!
				Minecraft.getMinecraft().thePlayer.closeScreen();
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("You did it!"));
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




/*package com.waabbuffet.hydromancy.client.gui.lexicon;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonChosenWords;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonPageChanger;
import com.waabbuffet.hydromancy.inventory.containers.ContainerPurifier;
import com.waabbuffet.hydromancy.inventory.containers.ContainerTranslationTable;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
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
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GuiTranslationTable extends GuiContainer {

	private IInventory playerInv;
	private TileEntityTranslationTable te;

	boolean hasPaper, isWordSelectionPage;
	String ChosenWords = "";
	
	int pageNumber = 0;

	public GuiTranslationTable(IInventory playerInv, TileEntityTranslationTable te) {
		super(new ContainerTranslationTable(playerInv, te));

		this.playerInv = playerInv;
		this.te = te;

		this.xSize = 176;
		this.ySize = 166;

		if(this.te.getChosenWords() != null)
		{
			this.ChosenWords = this.te.getChosenWords();
		}

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		//TODO: Client inventory is not synced when player moves lost paper/stone out of slots

		this.hasPaper = this.te.hasPaper(); //determines if should display the lost paper GUI


		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table.png"));
		this.drawTexturedModalRect(guiX + (this.hasPaper ?  85 : 0), guiY, 0, 0, this.xSize, this.ySize);


		if(this.hasPaper) //displays the lost paper GUI
		{
			String CodedText = "";
			int PageNumber = 0;
			if(this.te.getStackInSlot(0) != null)
			{
				CodedText = this.te.getStackInSlot(0).getTagCompound().getString("UndecipheredText");
				PageNumber = this.te.getStackInSlot(0).getTagCompound().getInteger("PageNumber");
			}
			
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/GUIShopMaybe.png"));
			this.drawTexturedModalRect(guiX - 85, guiY - 40, 44, 3, 168, 233); // 212 , 236
			
			FontRenderer fontrenderer = this.mc.fontRenderer; //Draws the enchantment text
			int k1 = 6839882;
			
			fontrenderer.drawSplitString("Lexicon Lost Page #: " + PageNumber + "/100",  guiX - 75, guiY - 35, 150, (k1 & 16711422) >> 1);
			fontrenderer.drawSplitString("This text seems to be written in an old language that says",  guiX - 65, guiY - 10, 130, (k1 & 16711422) >> 1);
			
			fontrenderer = this.mc.standardGalacticFontRenderer;
			fontrenderer.drawSplitString(CodedText,  guiX - 65, guiY + 25, 125, (k1 & 16711422) >> 1);
			
			fontrenderer = this.mc.fontRenderer; 
			fontrenderer.drawSplitString("I should match the words with words I already know",  guiX - 65, guiY + 60, 130, (k1 & 16711422) >> 1);
			
		}

		if(this.isWordSelectionPage)
		{
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/WordSelectionPage.png"));
			this.drawTexturedModalRect(guiX - 150, guiY - 40, 10, 10, 70, this.ySize + 70);
			
			
		}
		
		
		
			FontRenderer fontrenderer = this.mc.standardGalacticFontRenderer; //Draws the enchantment text
			int k1 = 6839882;
			fontrenderer.drawSplitString(this.ChosenWords,  guiX + (this.hasPaper ?  133 : 48), guiY + 10, 120, (k1 & 16711422) >> 1);
	
			fontrenderer = this.mc.fontRenderer; // changes it back to normal
			fontrenderer.drawSplitString(this.ChosenWords,  guiX + (this.hasPaper ?  133 : 48), guiY + 45, 120, (k1 & 16711422) >> 1);
		
		
	}
	
	@Override
	public void onGuiClosed() {
		// TODO Auto-generated method stub
		
		this.te.setChosenWords(ChosenWords);
		
		super.onGuiClosed();
	}
	
	
	@Override
	public void initGui() {
		
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		buttonList.clear();
		//TODO: Client inventory is not synced when player moves lost paper/stone out of slots

		this.hasPaper = this.te.hasPaper(); //determines if should display the lost paper GUI

		
		if(this.hasPaper && !this.isWordSelectionPage)
		{
			buttonList.add(new GuiButton(0 , guiX - 60, guiY + 80, 65, 20, "Show List")); 	
	
		}
		
		if(this.isWordSelectionPage)
		{
			if(this.pageNumber * 23 + 23 < TranslationTableUtils.getNamePartsArray().length)
			{
				
				buttonList.add(new GuiButton(2 , guiX - 120, guiY - 38 + 230, 15, 15, "->")); 
				for(int i = 0; i < 23; i ++)
				{
					buttonList.add(new GuiButtonChosenWords(10 + i +this.pageNumber * 23, guiX - 150, guiY - 38 + i * 10, TranslationTableUtils.getNamePartsArray()[i + this.pageNumber * 23]));
				}
			}else 
			{
				for(int i = this.pageNumber * 23; i < TranslationTableUtils.getNamePartsArray().length; i ++)
				{
					buttonList.add(new GuiButtonChosenWords(10 + i, guiX - 150, guiY - 38 + (i - this.pageNumber*23)* 10, TranslationTableUtils.getNamePartsArray()[i]));
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
			if((this.pageNumber * 23) < TranslationTableUtils.getNamePartsArray().length)
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
			
			if(this.ChosenWords.contains(this.te.getStackInSlot(0).getTagCompound().getString("UndecipheredText")))
			{
				//Send Packet here but for now just sysout!
				Minecraft.getMinecraft().thePlayer.closeScreen();
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("You did it!"));
			}
			
			initGui();
		}else if(button.id >= 10)
		{
			
			int wordID = button.id - 10;
			
			if(this.ChosenWords.split(" ").length < 11)
			{
				this.ChosenWords = this.ChosenWords + " " + TranslationTableUtils.getNamePartsArray()[wordID];
				this.ChosenWords = this.ChosenWords.trim();
			}
		}
		

		super.actionPerformed(button);
	}
}

 */ 
