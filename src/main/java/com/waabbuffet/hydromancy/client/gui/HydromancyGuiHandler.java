package com.waabbuffet.hydromancy.client.gui;

import com.waabbuffet.hydromancy.client.gui.TileEntity.GuiPurifier;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiContainerLexicon;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslatedPage;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslationTable;
import com.waabbuffet.hydromancy.inventory.containers.ContainerLexicon;
import com.waabbuffet.hydromancy.inventory.containers.ContainerPurifier;
import com.waabbuffet.hydromancy.inventory.containers.ContainerTranslationTable;
import com.waabbuffet.hydromancy.inventory.containers.LexiconInventory;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class HydromancyGuiHandler implements IGuiHandler {

	
	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	switch(ID){
		 	case 1:
		 		return new ContainerPurifier(player.inventory, (TileEntityPurifier) world.getTileEntity(x, y, z));
			case 2:
				return new ContainerTranslationTable(player.inventory, (TileEntityTranslationTable) world.getTileEntity(x, y, z));
			case 4:
				return new ContainerLexicon(player, new LexiconInventory(player.getHeldItem()));
			default:
				return null;
       }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        
    	switch(ID)
    	{
	    	case 0:
	    		return new GuiLexicon(player);
	    	case 1:
	    		return new GuiPurifier(player.inventory, (TileEntityPurifier) world.getTileEntity(x, y, z));
	    	case 2:
	    		return new GuiTranslationTable(player.inventory, (TileEntityTranslationTable) world.getTileEntity(x, y, z), player);
	    	case 3:
	    		return new GuiTranslatedPage(player);
	    	case 4:
	    		return new GuiContainerLexicon(new ContainerLexicon(player, new LexiconInventory(player.getHeldItem())));
	    	default:
	    		return null;
		}	
    	
    	
    }
    
    
    
}