package com.waabbuffet.hydromancy.blocks.purification.coral;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.tileEntity.TileEntityPurifyWater;
import com.waabbuffet.hydromancy.util.IUsePurifiedWater;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class BlockCoral extends BlockBush implements  IUsePurifiedWater, ITileEntityProvider
{
	public BlockCoral(String name) 
	{
		super(Material.SPONGE);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) 
	{
		return canSurvive(worldIn, pos);
	}
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		
		return canSurvive(worldIn, pos);
	}

	public boolean canSurvive(World worldIn, BlockPos pos)
	{
		BlockPos starting = new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1);
		boolean has_water = false, has_friend = false;
		
		for(int y = 0; y < 2; y ++)
		{
			for(int x = 0; x < 3; x ++)
			{
				for(int z = 0; z < 3; z ++)
				{
					IBlockState state = worldIn.getBlockState(new BlockPos(starting.getX() + x, starting.getY() + y, starting.getZ() + z));
					
					if(state.getBlock().equals(Blocks.WATER) || state.getBlock().equals(HydromancyBlockHandler.purified_water))
					{
						has_water = true;
					}else if(state.getBlock() instanceof BlockCoral)
					{
						if(state.getBlock() != this)
						{
							if(!isInsideFriends(state.getBlock()))
							{
								return false;
							}else
								has_friend = true;
						}
					}						
				}
			}
		}
	
		if(this.needsFriendToSurvive())
		{
			if(has_friend)
			{
				return has_water;
			}else
				return false;
		}
		return has_water;
	}

	public boolean isInsideFriends(Block block) 
	{
		Block[] friendsList = this.getFriendsList();
		if(friendsList != null)
		{
			for(Block block2 : friendsList)
			{
				if(block.equals(block2))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityPurifyWater(this);
	}
	
	@Override
	public float getFailChance() {
		// TODO Auto-generated method stub
		return 70;
	}
	
	@Override
	public int getBlockRadius() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getSpeedInTicks() {
		// TODO Auto-generated method stub
		return 3600;
	}

	@Override
	public int getAmountIncrease() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getMaxBlocks() {
		// TODO Auto-generated method stub
		return 1;
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
		return 5;
	}


	@Override
	public int getMinPurityValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//only coral allowed next to a piece of coral
	public abstract Block[] getFriendsList();
	public abstract boolean needsFriendToSurvive();
}
