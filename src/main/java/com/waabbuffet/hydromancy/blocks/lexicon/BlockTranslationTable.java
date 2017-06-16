package com.waabbuffet.hydromancy.blocks.lexicon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.waabbuffet.hydromancy.Hydromancy;
import com.waabbuffet.hydromancy.tileEntity.TileEntityTranslationTable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockTranslationTable extends Block implements ITileEntityProvider{

	//Colliders and bounding boxes
	private static final AxisAlignedBB BB_N = new AxisAlignedBB(0D, 0D, 3.25/16D, 1D, 15.5/16D, 12.75/16D);
	private static final AxisAlignedBB MAIN_N = new AxisAlignedBB(0D, 8/16D, 3.25/16D, 1D, 9/16D, 12.75/16D);
	private static final AxisAlignedBB SECOND_N = new AxisAlignedBB(2/16D, 2.75/16D, 4.9/16D, 14.25/16D, 3.25/16D, 11.125/16D);
	private static final AxisAlignedBB LEG1_N = new AxisAlignedBB(1.125/16D, 0D, 4.3/16D, 2.125/16D, 8/16D, 5.3/16D);
	private static final AxisAlignedBB LEG2_N = new AxisAlignedBB(1.125/16D, 0D, 10.7/16D, 2.125/16D, 8/16D, 11.7/16D);
	private static final AxisAlignedBB LEG3_N = new AxisAlignedBB(13.9/16D, 0D, 4.3/16D, 14.9/16D, 8/16D, 5.3/16D);
	private static final AxisAlignedBB LEG4_N = new AxisAlignedBB(13.9/16D, 0D, 10.7/16D, 14.9/16D, 8/16D, 11.7/16D);
	private static final AxisAlignedBB BB_W = new AxisAlignedBB(3.25/16D, 0D, 0D, 12.75/16D, 15.5/16D, 1D);
	private static final AxisAlignedBB MAIN_W = new AxisAlignedBB(3.25/16D, 8/16D, 0D, 12.75/16D, 9/16D, 1D);
	private static final AxisAlignedBB SECOND_W = new AxisAlignedBB(4.9/16D, 2.75/16D, 2/16D, 11.125/16D, 3.25/16D, 14.25/16D);
	private static final AxisAlignedBB LEG1_W = new AxisAlignedBB(4.3/16D, 0D, 1.125/16D, 5.3/16D, 8/16D, 2.125/16D);
	private static final AxisAlignedBB LEG2_W = new AxisAlignedBB(10.7/16D, 0D, 1.125/16D, 11.7/16D, 8/16D, 2.125/16D);
	private static final AxisAlignedBB LEG3_W = new AxisAlignedBB(4.3/16D, 0D, 13.9/16D, 5.3/16D, 8/16D, 14.9/16D);
	private static final AxisAlignedBB LEG4_W = new AxisAlignedBB(10.7/16D, 0D, 13.9/16D, 11.7/16D, 8/16D, 14.9/16D);
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	private List<AxisAlignedBB> bb = new ArrayList<AxisAlignedBB>();
	private final Random random = new Random();
	
	public BlockTranslationTable(String name) {
		super(Material.WOOD);
		setHardness(1.9f);
		
		setRegistryName(name);
		setUnlocalizedName(name);
		
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		player.openGui(Hydromancy.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess access, BlockPos pos)
	{
		int meta = getMetaFromState(state);
		if(meta == 0 || meta == 2)
			return BB_N;
		else
			return BB_W;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn)
	{
		bb.clear();
		
		int meta = getMetaFromState(state);
		if(meta == 0 || meta == 2)
		{
			bb.add(MAIN_N);
			bb.add(SECOND_N);
			bb.add(LEG1_N);
			bb.add(LEG2_N);
			bb.add(LEG3_N);
			bb.add(LEG4_N);
		}
		else
		{
			bb.add(MAIN_W);
			bb.add(SECOND_W);
			bb.add(LEG1_W);
			bb.add(LEG2_W);
			bb.add(LEG3_W);
			bb.add(LEG4_W);
		}
		
		for(AxisAlignedBB bba : bb)
		{
			//System.out.println(bba);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, bba);
		}
	}
	
	@Override
	public BlockRenderLayer getBlockLayer()
	{
		//cutout cause of the glass texture in the magnifier
		return BlockRenderLayer.CUTOUT;
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
	public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
		TileEntityTranslationTable te = (TileEntityTranslationTable) world.getTileEntity(pos);

        if (te != null)
        {
            for (int i = 0; i < te.handler.getSlots(); i++)
            {
                ItemStack itemstack = te.handler.getStackInSlot(i);

                if (itemstack != null)
                {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int j = this.random.nextInt(21) + 10;

                        if (j > itemstack.stackSize)
                        {
                            j = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j;
                        EntityItem entityitem = new EntityItem(world, (double)((float)pos.getX() + f), (double)((float)pos.getY() + f1), (double)((float)pos.getZ() + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

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
        }

        super.breakBlock(world, pos, state);
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTranslationTable();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
}
