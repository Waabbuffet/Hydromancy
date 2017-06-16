package com.waabbuffet.hydromancy.proxy;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.capabilities.HydromancyCapabilities;
import com.waabbuffet.hydromancy.client.gui.HydromancyGuiHandler;
import com.waabbuffet.hydromancy.items.HydromancyItemHandler;
import com.waabbuffet.hydromancy.lexicon.LexiconPageHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionTypesHandler;
import com.waabbuffet.hydromancy.tileEntity.HydromancyTileEntityHandler;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void PreInit(FMLPreInitializationEvent event) 
	{
		OBJLoader.INSTANCE.addDomain(Reference.MODID);

		HydromancyItemHandler.init();
		HydromancyBlockHandler.init();
		HydromancyTileEntityHandler.register();
		
		HydromancyPotionHandler.initPotions();
		HydromancyPotionTypesHandler.registerPotions();
		
		HydromancyCapabilities.register();
		LexiconPageHandler.init();
		
		HydromancyPacketHandler.init();
		
		registerModel(HydromancyBlockHandler.translation_table);
	}
	
	public void registerModel(Block item)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(item), 0, new ModelResourceLocation(item.getRegistryName(),"inventory"));
	}

	public void Init(FMLInitializationEvent event) 
	{
		// TODO Auto-generated method stub
		NetworkRegistry.INSTANCE.registerGuiHandler(Hydromancy.instance, new HydromancyGuiHandler());
		
	}

	public void PostInit(FMLPostInitializationEvent event) 
	{
		// TODO Auto-generated method stub
		
	}
}
