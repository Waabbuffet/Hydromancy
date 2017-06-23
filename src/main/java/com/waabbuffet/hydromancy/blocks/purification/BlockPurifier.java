package com.waabbuffet.hydromancy.blocks.purification;

import javax.annotation.Nullable;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.tileEntity.TileEntityPurifyWater;
import com.waabbuffet.hydromancy.util.IUsePurifiedWater;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPurifier extends Block implements ITileEntityProvider, IUsePurifiedWater
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public BlockPurifier(String name) 
	{
        super(Material.IRON);
        
        setRegistryName(name);
		setUnlocalizedName(name);
        
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        
        GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
        
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.isBlockContainer = true;
    }

	
	/*@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(player.getHeldItemMainhand() != null)
		{
			if(player.getHeldItemMainhand().isItemEqual(new ItemStack(Items.WATER_BUCKET)))
			{
				TileEntityPurifyWater purifier = (TileEntityPurifyWater) world.getTileEntity(pos);

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
			else {
				player.openGui(Hydromancy.instance, 1, world, x, y, z);
				return true;
			}
		}else 
		{
			player.openGui(Hydromancy.instance, 1, world, x, y, z);
			return true;
		}
		
		//return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
	}*/
	
	
	
    @Override
    public TileEntity createNewTileEntity(World world, int meta) 
    {
    	return new TileEntityPurifyWater(this);
    }
    
    
    @Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
    
    @Override
	public int getMetaFromState(IBlockState state)
    {
        //return state.getValue(FACING).getIndex();
        return state.getValue(FACING).getHorizontalIndex();
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public float getFailChance() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getBlockRadius() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getSpeedInTicks() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getAmountIncrease() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getMaxBlocks() {
		// TODO Auto-generated method stub
		return 0;
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
		return 0;
	}


	@Override
	public int getMinPurityValue() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isPreserver() {
		// TODO Auto-generated method stub
		return false;
	}
    
}