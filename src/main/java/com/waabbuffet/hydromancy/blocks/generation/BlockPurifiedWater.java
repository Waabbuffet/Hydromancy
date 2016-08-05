package com.waabbuffet.hydromancy.blocks.generation;

import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifiedWater;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockPurifiedWater extends BlockFluidClassic implements ITileEntityProvider{

	
	@SideOnly(Side.CLIENT)
    private IIcon flowingIcon;
	
    @SideOnly(Side.CLIENT)
    private IIcon stillIcon;
    
	
	public BlockPurifiedWater(Fluid fluid, Material material) {
		super(fluid, material);
		
		//this.setCreativeTab(CreativeTabs.tabMisc);
		this.setBlockUnbreakable();
	}
	
	
	 @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int side, int meta)
	    {
	        return (side == 0 || side == 1)? stillIcon : flowingIcon;
	    }
		
	
		@Override
		public void registerBlockIcons(IIconRegister IconRegister) {
			this.stillIcon = IconRegister.registerIcon(Reference.MODID + ":purified_0_water_still");
			this.flowingIcon = IconRegister.registerIcon(Reference.MODID + ":purified_0_water_flow");
		}


		@Override
		public TileEntity createNewTileEntity(World world, int meta) {
			// TODO Auto-generated method stub
			
			
			if(meta == 0)
			{
				return new TileEntityPurifiedWater();
			}
				return null;
		}
	
		@Override
		public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean displaceIfPossible(World world, int x, int y, int z) {
			// TODO Auto-generated method stub
			return false;
		}
}
