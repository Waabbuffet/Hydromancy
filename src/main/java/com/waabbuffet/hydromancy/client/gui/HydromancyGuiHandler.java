package com.waabbuffet.hydromancy.client.gui;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiContainerLexicon;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiLexicon;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslationTable;
import com.waabbuffet.hydromancy.inventory.LexiconInventory;
import com.waabbuffet.hydromancy.inventory.containers.ContainerLexicon;
import com.waabbuffet.hydromancy.inventory.containers.ContainerTranslationTable;
import com.waabbuffet.hydromancy.tileEntity.TileEntityTranslationTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import scala.reflect.internal.util.Position;

public class HydromancyGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch(ID)
		{
			case 1:
				return new ContainerTranslationTable(player.inventory, (TileEntityTranslationTable) world.getTileEntity(new BlockPos(x, y, z)));
			case 2:
				return new ContainerLexicon(player, new LexiconInventory(player.getHeldItemMainhand()));
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch(ID)
		{
			case 0:
				return new GuiLexicon(null);
			case 1:
				return new GuiTranslationTable(player.inventory, (TileEntityTranslationTable) world.getTileEntity(new BlockPos(x, y, z)), player);
			case 2:
				return new GuiContainerLexicon(new ContainerLexicon(player, new LexiconInventory(player.getHeldItemMainhand())));
			case 3:
				return new GuiLexicon(player);
			default:
				return null;
		}
	}
}
