package com.waabbuffet.hydromancy.blocks.lexicon;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockTranslationTable extends Block implements ITileEntityProvider{

	public BlockTranslationTable() {
		super(Material.clay);
		// TODO Auto-generated constructor stub
	}



	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {


		player.openGui(Hydromancy.instance, 2, world, x, y, z);

		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}



	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityTranslationTable();
	}


}
