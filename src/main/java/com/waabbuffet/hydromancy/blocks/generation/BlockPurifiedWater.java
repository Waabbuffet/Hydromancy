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
		
		
		//Add a block that will generate pure water by scanning some area maybe a 7 by 7 square and the more coral in the area the faster it will
		//Produce purified water...can even add if a red is next to a green than work faster maybe?
		//Will eventually consume the coral though
		
		
		//Coral 1: has to be next to a coral to live but cannot be next to coral 1
		//Coral 2: needs 1 block of water on 3 sides to live
		//Coral 3: needs a 3 by 3 of water to live or coral 1
		//Coral 4: Will consume the coral on operation but will be the fastest producer, can be by anything
		//Coral 5: 
		
		/* Will produce purified water on a rate equal to the amount of coral in the square, the rest of blocks have to be covered in water
		 * Coral 1: Needs One side of coral 2, 4, 7 to live does not count corners, (Adds + 2) 
		 * Coral 2: Needs 5 sides of water including corners (Adds + 3)
		 * Coral 3: Needs all sides to be covered in coral (Adds + 1 to multiplier), cannot be within a 3 by 3 of the same coral
		 * Coral 4: max 2 piece of coral next to it adds 0 to multiplier 
		 * Coral 5: Needs all sides to be covered in water in a 3 by 3
		 * Coral 6: 
		 * Coral 7: 
		 */
		
		
		// Another block will work on heat...will use some sort of connecting 
		
}
