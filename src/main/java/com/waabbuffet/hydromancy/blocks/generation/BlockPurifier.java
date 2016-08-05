package com.waabbuffet.hydromancy.blocks.generation;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockPurifier extends Block implements ITileEntityProvider
{
	
	@SideOnly(Side.CLIENT)
    private IIcon TopAndBottm;
	
    @SideOnly(Side.CLIENT)
    private IIcon Front;
	
	
	public BlockPurifier() 
	{
        super(Material.iron);
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.isBlockContainer = true;
    }

	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(player.getHeldItem() != null)
		{
			if(player.getHeldItem().isItemEqual(new ItemStack(Items.water_bucket)))
			{
				TileEntityPurifier purifier = (TileEntityPurifier) world.getTileEntity(x, y, z);

				if(purifier != null)
				{
					if((purifier.waterTank.getFluidAmount() + 1000 ) <= purifier.waterTank.getCapacity())
					{
						purifier.fill(ForgeDirection.getOrientation(side), new FluidStack(FluidRegistry.WATER, 1000), true);
						player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
					}
				}	
				return true;
			}
		}else 
		{
			player.openGui(Hydromancy.instance, 1, world, x, y, z);
		}
		
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}
	
	
	
    @Override
    public TileEntity createNewTileEntity(World world, int meta) 
    {
    	return new TileEntityPurifier();
    }
    
   
    
   /* 
    * Taken from Block Furnace!
    */
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
	
        return side == 1 ? this.TopAndBottm : (side == 0 ? this.TopAndBottm : (side != meta ? this.blockIcon : this.Front));
    }
	
	
	@Override
	public void registerBlockIcons(IIconRegister IconRegister) {
		
			this.blockIcon = IconRegister.registerIcon(Reference.MODID + ":Purifier base");
	        this.Front = IconRegister.registerIcon(Reference.MODID + ":Purifier base with hole");
	        this.TopAndBottm = IconRegister.registerIcon(Reference.MODID + ":Purifier top, Glass and Sponge");
	}
    
    
    public void onBlockPlacedBy(World World, int x, int y, int z, EntityLivingBase EntityLiving, ItemStack ItemStack)
    {
        int l = MathHelper.floor_double((double)(EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

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

        if (ItemStack.hasDisplayName())
        {
            ((TileEntityFurnace)World.getTileEntity(x, y, z)).func_145951_a(ItemStack.getDisplayName());
        }
    }
    
}