package com.waabbuffet.hydromancy.blocks.lexicon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	private final Random random = new Random();
	
	public BlockTranslationTable() {
		super(Material.wood);
		setHardness(1.9f);
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
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z){
		int blockMeta = access.getBlockMetadata(x, y, z);
		if(blockMeta == 2 || blockMeta == 3){		
			//setBlockBounds(0.0f, 0.0f, (float) 3.5/16, 1.0f, (float) 9/16, (float) 12.5/16); // this one and the bottom one unrenders too soon
			setBlockBounds(0.0f, 0.0f, (float) 3.25/16, 1.0f, (float) 15.5/16, (float) 12.75/16);
		}
		else if(blockMeta == 4 || blockMeta == 5 ){
			//setBlockBounds((float) 3.5/16, 0.0f, 0.0f, (float) 12.5/16, (float) 9/16, 1.0f);
			setBlockBounds((float) 3.25/16, 0.0f, 0.0f, (float) 12.75/16, (float) 15.5/16, 1.0f);
		}
	}

	// TODO: make model not disappear when still looking at it
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB blockBounds, List list, Entity entity){ 
		this.addCollMainTable(world,x,y,z);
		super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, entity);
		this.addCollLeg1(world,x,y,z);		
		super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, entity);
		this.addCollLeg2(world,x,y,z);		
		super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, entity);
		this.addCollLeg3(world,x,y,z);		
		super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, entity);
		this.addCollLeg4(world,x,y,z);		
		super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, entity);
		this.addCollSecTable(world,x,y,z);		
		super.addCollisionBoxesToList(world, x, y, z, blockBounds, list, entity);
	}
	
	/* collision parts */
	public void addCollMainTable(IBlockAccess acces, int x, int y, int z)
    {
        int meta = acces.getBlockMetadata(x, y, z);

        if (meta == 2 || meta == 3)
        {
            this.setBlockBounds(0.0f, (float) 8/16, (float) 3.25/16, 1.0f, (float) 9/16, (float) 12.75/16);
        }
        else
        {
            this.setBlockBounds((float) 3.25/16, (float) 8/16, 0.0f, (float) 12.75/16, (float) 9/16, 1.0f);
        }
    }
	public void addCollLeg1(IBlockAccess acces, int x, int y, int z)
    {
        int meta = acces.getBlockMetadata(x, y, z);

        if (meta == 2 || meta == 3)
        {
            this.setBlockBounds((float) 1.125/16, 0, (float) 4.3/16, (float) 2.125/16, (float) 8/16, (float) 5.3/16);
        }
        else
        {
            this.setBlockBounds((float) 4.3/16, 0, (float) 1.125/16, (float) 5.3/16, (float) 8/16, (float) 2.125/16);
        }
    }
	public void addCollLeg2(IBlockAccess acces, int x, int y, int z)
    {
        int meta = acces.getBlockMetadata(x, y, z);

        if (meta == 2 || meta == 3)
        {
            this.setBlockBounds((float) 1.125/16, 0, (float) 10.7/16, (float) 2.125/16, (float) 8/16, (float) 11.7/16);
        }
        else
        {
            this.setBlockBounds((float) 10.7/16, 0, (float) 1.125/16, (float) 11.7/16, (float) 8/16, (float) 2.125/16);
        }
    }
	public void addCollLeg3(IBlockAccess acces, int x, int y, int z)
    {
        int meta = acces.getBlockMetadata(x, y, z);

        if (meta == 2 || meta == 3)
        {
            this.setBlockBounds((float) 13.9/16, 0, (float) 4.3/16, (float) 14.9/16, (float) 8/16, (float) 5.3/16);
        }
        else
        {
            this.setBlockBounds((float) 4.3/16, 0, (float) 13.9/16, (float) 5.3/16, (float) 8/16, (float) 14.9/16);
        }
    }
	public void addCollLeg4(IBlockAccess acces, int x, int y, int z)
    {
        int meta = acces.getBlockMetadata(x, y, z);

        if (meta == 2 || meta == 3)
        {
            this.setBlockBounds((float) 13.9/16, 0, (float) 10.7/16, (float) 14.9/16, (float) 8/16, (float) 11.7/16);
        }
        else
        {
            this.setBlockBounds((float) 10.7/16, 0, (float) 13.9/16, (float) 11.7/16, (float) 8/16, (float) 14.9/16);
        }
    }
	public void addCollSecTable(IBlockAccess acces, int x, int y, int z)
    {
        int meta = acces.getBlockMetadata(x, y, z);

        if (meta == 2 || meta == 3)
        {
            this.setBlockBounds((float) 2/16, (float) 2.75/16, (float) 4.9/16, (float) 14.25/16, (float) 3.25/16, (float) 11.125/16);
        }
        else
        {
            this.setBlockBounds((float) 4.9/16, (float) 2.75/16, (float) 2/16, (float) 11.125/16, (float) 3.25/16, (float) 14.25/16);
        }
    }
	/*end of collision parts*/
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
		TileEntityTranslationTable te = (TileEntityTranslationTable) world.getTileEntity(x, y, z);

        if (te != null)
        {
            for (int i1 = 0; i1 < te.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = te.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int j1 = this.random.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }
}
