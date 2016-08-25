package com.waabbuffet.hydromancy.blocks.lexicon;

import java.util.ArrayList;
import java.util.List;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockTranslationTable extends Block implements ITileEntityProvider{

	private int l;
	
	public BlockTranslationTable() {
		super(Material.clay);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {


		player.openGui(Hydromancy.instance, 2, world, x, y, z);
		return true;
		//return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityTranslationTable();
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World World, int x, int y, int z, EntityLivingBase EntityLiving, ItemStack ItemStack)
    {
        l = MathHelper.floor_double((double)(EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            World.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            World.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            World.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            World.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }    
    }
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess acces, int x, int y, int z){
		int blockMeta = acces.getBlockMetadata(x, y, z);
		if(blockMeta == 2 || blockMeta == 3){		
			//setBlockBounds(0.0f, 0.0f, (float) 3.5/16, 1.0f, (float) 9/16, (float) 12.5/16); // this one and the bottom one unrenders too soon
			setBlockBounds(0.0f, 0.0f, (float) 3.25/16, 1.0f, (float) 15.5/16, (float) 12.75/16);
		}
		else if(blockMeta == 4 || blockMeta == 5 ){
			//setBlockBounds((float) 3.5/16, 0.0f, 0.0f, (float) 12.5/16, (float) 9/16, 1.0f);
			setBlockBounds((float) 3.25/16, 0.0f, 0.0f, (float) 12.75/16, (float) 15.5/16, 1.0f);
		}
	}

	// TODO: add collisions
	
	/*@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB blockBounds, List list, Entity collidingEntity){ 
		int blockMeta = world.getBlockMetadata(x, y, z);
		
		if(blockMeta == 2 || blockMeta == 3){ //south and north
			//blockBounds = AxisAlignedBB.getBoundingBox(0.0f, (float) 8/16, (float) 3.5/16, 1.0f, (float) 9/16, (float) 12.5/16);
			//blockBounds = blockBounds.
			//this.setBlockBounds(0.0f, 0.0f, (float) 3.5/16, 1.0f, (float) 9/16, (float) 12.5/16);
			AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(0.0f, (float) 8/16, (float) 3.5/16, 1.0f, (float) 9/16, (float) 12.5/16);

	        if (axisalignedbb1 != null && blockBounds.intersectsWith(axisalignedbb1))
	        {
	            list.add(axisalignedbb1);
	        }
			
			//super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, collidingEntity);
			/*AxisAlignedBB blockBounds2 = AxisAlignedBB.getBoundingBox((float) 1.5/16, 0, (float) 4.5/16, (float) 2.5/16, (float) 8/16, (float) 6.5/16);
			super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, collidingEntity);
			AxisAlignedBB blockBounds3 = AxisAlignedBB.getBoundingBox((float) 13.5/16, 0, (float) 4.5/16, (float) 2.5/16, (float) 8/16, (float) 14.5/16);
			super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, collidingEntity);
			AxisAlignedBB blockBounds4 = AxisAlignedBB.getBoundingBox((float) 1.5/16, 0, (float) 11.5/16, (float) 2.5/16, (float) 8/16, (float) 13.5/16);
			super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, collidingEntity);
			AxisAlignedBB blockBounds5 = AxisAlignedBB.getBoundingBox((float) 13.5/16, 0, (float) 11.5/16, (float) 2.5/16, (float) 8/16, (float) 13.5/16);
			super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, collidingEntity);
		}
		else if(blockMeta == 4 || blockMeta == 5 ){ //east and west
		//	setBlockBounds((float) 3.5/16, (float) 8/16, 0.0f, (float) 12.5/16, (float) 9/16, 1.0f);
			
		}
		
	       
	}*/
}
