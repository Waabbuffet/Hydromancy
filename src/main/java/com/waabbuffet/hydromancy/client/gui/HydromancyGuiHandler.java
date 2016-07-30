package com.waabbuffet.hydromancy.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class HydromancyGuiHandler implements IGuiHandler {

	
	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
       
    	if(ID == 0){
  //  		 return new ContainerTileEntityKingdomStructureBlock(player.inventory, (TileEntityKingdomStructureBlock) world.getTileEntity(x, y, z));
    	}
    	
    	
    	
    
    	return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        
    	if(ID == 0){
    	//	return new GuiTileEntityKingdomStructure(player.inventory, (TileEntityKingdomStructureBlock) world.getTileEntity(x, y, z));
    	}
    	
    		
    	return null;
    }
    
    
    
}