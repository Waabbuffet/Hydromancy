package com.waabbuffet.hydromancy.blocks;

import com.waabbuffet.hydromancy.tileEntity.TileEntityPurifiedWater;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockPurifiedWater extends BlockFluidClassic implements ITileEntityProvider
{
	public BlockPurifiedWater(String name, Fluid fluid) 
	{
		super(fluid, Material.WATER);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setCreativeTab(CreativeTabs.MATERIALS);
	
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityPurifiedWater();
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		
		return false;
	}
}
