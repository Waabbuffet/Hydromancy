package com.waabbuffet.hydromancy.client.gui;

import com.waabbuffet.hydromancy.client.gui.TileEntity.GuiPurifier;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.inventory.containers.ContainerPurifier;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class HydromancyGuiHandler implements IGuiHandler {

	
	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
       
    	if(ID == 0){
   		 	return null;
    	}else if(ID == 1)
    	{
    		return new ContainerPurifier(player.inventory, (TileEntityPurifier) world.getTileEntity(x, y, z));
    	}
    	
    	
    	
    
    	return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        
    	if(ID == 0){
    		return new GuiLexicon();
    	}else if(ID == 1)
    	{
    		return new GuiPurifier(player.inventory, (TileEntityPurifier) world.getTileEntity(x, y, z));
    	}
    	
    		
    	return null;
    }
    
    
    
}