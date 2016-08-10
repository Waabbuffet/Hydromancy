package com.waabbuffet.hydromancy.blocks.transportation;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;
import com.waabbuffet.hydromancy.tileentity.transportation.TileEntityCoralPump;
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

public class BlockCoralPump extends Block implements ITileEntityProvider
{
	
	@SideOnly(Side.CLIENT)
    private IIcon TopAndBottmSOUTH, TopAndBottmNORTH, TopAndBottmWEST ,TopAndBottmEAST;
	
    @SideOnly(Side.CLIENT)
    private IIcon Front;
	
	
	public BlockCoralPump() 
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
	
		
		return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}
	
	
	
    @Override
    public TileEntity createNewTileEntity(World world, int meta) 
    {
    	
    	return new TileEntityCoralPump();
    }
    
   
    
   /* 
    * Taken from Block Furnace!
    */
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	IIcon Arrow = Front;
    	switch(meta)
    	{
    	case 2:
    		//North
    		Arrow = this.TopAndBottmNORTH;
    		break;
    	case 3:
    		//South
    		Arrow = this.TopAndBottmSOUTH;
    		break;
    	case 4:
    		//west
    		Arrow = this.TopAndBottmWEST;
    		break;
    	case 5:
    		//East
    		Arrow = this.TopAndBottmEAST;
    		break;        		
    	}
    	
        return side == 1 ? Arrow : (side == 0 ? TopAndBottmSOUTH : (side != meta ? this.blockIcon : this.Front));
    }
	
	
	@Override
	public void registerBlockIcons(IIconRegister IconRegister) {
		
			this.blockIcon = IconRegister.registerIcon(Reference.MODID + ":CoralPumpSide");
	        this.Front = IconRegister.registerIcon(Reference.MODID + ":CoralPumpFront");
	        this.TopAndBottmSOUTH = IconRegister.registerIcon(Reference.MODID + ":CoralPumpTopAndBottomSOUTH");
	        this.TopAndBottmNORTH = IconRegister.registerIcon(Reference.MODID + ":CoralPumpTopAndBottomNORTH");
	        this.TopAndBottmEAST = IconRegister.registerIcon(Reference.MODID + ":CoralPumpTopAndBottomEAST");
	        this.TopAndBottmWEST = IconRegister.registerIcon(Reference.MODID + ":CoralTopAndBottomWEST");
	        
	}
	
	
    
    @Override
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
        
        ((TileEntityCoralPump) World.getTileEntity(x, y, z)).setDestinationDirection(l);
        
    }
    
}