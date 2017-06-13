package com.waabbuffet.hydromancy.blocks.purification;

import com.waabbuffet.hydromancy.tileEntity.consume.TileEntityObelisk;
import com.waabbuffet.hydromancy.util.IUsePurifiedWater;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockObelisk extends Block implements IUsePurifiedWater, ITileEntityProvider {

	public BlockObelisk(String name) {
		super(Material.IRON);

		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}



	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY,
			float hitZ) {

		if(heldItem == null)
		{
			if(hand.equals(EnumHand.MAIN_HAND))
			{
				TileEntityObelisk entity = (TileEntityObelisk) worldIn.getTileEntity(pos);

				if(entity != null)
				{
					entity.turnOn();
				}
			}
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem,
				side, hitX, hitY, hitZ);
	}

	@Override
	public float getFailChance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockRadius() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int getSpeedInTicks() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public int getAmountIncrease() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getMaxBlocks() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public ItemStack[] cost_item() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack[] produce_item() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxPurityValue() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public int getMinPurityValue() {
		// TODO Auto-generated method stub
		return 25;
	}

	@Override
	public boolean isPreserver() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityObelisk(this);
	}
}
