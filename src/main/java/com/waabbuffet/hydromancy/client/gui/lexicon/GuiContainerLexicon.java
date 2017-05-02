package com.waabbuffet.hydromancy.client.gui.lexicon;

import org.lwjgl.input.Mouse;

import com.waabbuffet.hydromancy.client.gui.lexicon.util.GuiContainerVisibility;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiSlider;
import com.waabbuffet.hydromancy.inventory.containers.ContainerLexicon;
import com.waabbuffet.hydromancy.inventory.containers.LexiconInventory;
import com.waabbuffet.hydromancy.inventory.containers.SlotResearchNote;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SUpdateLexiconInventory;
import com.waabbuffet.hydromancy.packet.packets.SUpdateTranslationTable;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiContainerLexicon extends GuiContainerVisibility{
	private ResourceLocation res = new ResourceLocation(Reference.MODID + ":textures/gui/LexiconContainerGui.png");
	private ContainerLexicon container;
	private LexiconInventory inventory;
	private GuiSlider slider;
	
	public GuiContainerLexicon(ContainerLexicon container) {
		super(container);
		this.container = container;
		//this.inventory = container.inventory;
		this.xSize = 194;
		this.ySize = 166;
	}
	
	@Override
	public void initGui()
	{
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;
		slider = new GuiSlider(0, guiX + 174, guiY + 8, 150, 12, 15);
		slider.disabled = true;
		
		buttonList.clear();
		buttonList.add(slider);
		
		super.initGui();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mx, int my) {
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;
		
		this.mc.getTextureManager().bindTexture(res);
		this.drawTexturedModalRect(guiX, guiY, 0, 0, this.xSize, this.ySize);

		//I am not happy about this action, but it looks like the only way
		int nullCount = 0;
		int firstOne = 0;
		for(int i = 36; i<container.inventorySlots.size(); i++)
		{
			if(i >= container.inventorySlots.size())
			{
				return;
			}
			if(container.getSlot(i).getStack() == null)
			{
				nullCount++;
				if(nullCount == 1)
					firstOne = i;
			}
			
			if(nullCount > 1 && container.inventorySlots.size() > 36)
			{
				container.removeAdditionalAndUpdate(container.getSlot(firstOne));
				return;
			}
		}
		if(nullCount == 0 && container.inventorySlots.size() > 36)
		{
			container.addAdditionalAndUpdate();
		}
		
		slider.segments = (int)Math.ceil((float)container.inventory.getSizeInventory()/9-3);
		
		if(Math.ceil((float)container.inventory.getSizeInventory()/9) > 4)
		{
			slider.disabled = false;
			
			if(slider.idelta != 0)
				container.updateSlotPositions((byte)slider.idelta);
		}
		else
		{
			if(!slider.disabled)
				if(container.inventory.getSizeInventory() > 0)
					while(container.getSlot(36).yDisplayPosition < 8)
						container.updateSlotPositions((byte) 1);

			slider.sliderValue = 0;
			slider.disabled = true;
		}
	}
	
	
	@Override
	public void onGuiClosed()
	{
		HydromancyPacketHandler.INSTANCE.sendToServer(new SUpdateLexiconInventory(container.getItemInventory()));
	}
}
