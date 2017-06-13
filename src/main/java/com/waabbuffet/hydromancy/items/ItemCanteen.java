package com.waabbuffet.hydromancy.items;

import java.util.List;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionTypesHandler;
import com.waabbuffet.hydromancy.tileEntity.TileEntityPurifiedWater;
import com.waabbuffet.hydromancy.util.PurifiedWaterUtil;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCanteen extends ItemFood
{
	public ItemCanteen(String name) {
		super(1, false);

		this.setCreativeTab(CreativeTabs.BREWING);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setMaxStackSize(1);
		GameRegistry.register(this);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

		if(stack.getTagCompound().getInteger("WaterAmount") >= 1000)
		{
			stack.getTagCompound().setInteger("WaterAmount", stack.getTagCompound().getInteger("WaterAmount") - 1000);
		}else
			stack.getTagCompound().setInteger("WaterAmount", 0);

		if (entityLiving instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			entityplayer.getFoodStats().addStats(this, stack);
			worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));

			entityplayer.addPotionEffect(new PotionEffect(HydromancyPotionHandler.whispering_thoughts, 3600));
		}

		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {

		RayTraceResult trace = this.rayTrace(worldIn, playerIn, true);

		if(trace != null)
		{
			if(trace.getBlockPos() != null)
			{
				Block water = worldIn.getBlockState(trace.getBlockPos()).getBlock();
				Block air = worldIn.getBlockState(trace.getBlockPos().up()).getBlock();

				if(water.equals(HydromancyBlockHandler.purified_water))
				{
					TileEntityPurifiedWater water_tile = (TileEntityPurifiedWater) worldIn.getTileEntity(trace.getBlockPos());

					if(stack.hasTagCompound())
					{
						if(stack.getTagCompound().getInteger("WaterAmount") < 5000)
						{
							stack.getTagCompound().setInteger("WaterAmount",stack.getTagCompound().getInteger("WaterAmount") + 1000);

							if(water_tile.getpurity() < stack.getTagCompound().getInteger("purity") || stack.getTagCompound().getInteger("purity") == 0)
							{
								stack.getTagCompound().setInteger("purity", water_tile.getpurity());
							}
							worldIn.setBlockToAir(trace.getBlockPos());
						}
					}else 
					{
						NBTTagCompound tag = new NBTTagCompound();
						tag.setInteger("WaterAmount", 1000);
						tag.setInteger("purity", water_tile.getpurity());
						stack.setTagCompound(tag);
						worldIn.setBlockToAir(trace.getBlockPos());
					}
					
					return new ActionResult(EnumActionResult.FAIL, stack);
				}else if(air.equals(Blocks.AIR))
				{
					if(PurifiedWaterUtil.hasPurifyingSource(worldIn, trace.getBlockPos().up(), 5))
					{
						if(stack.hasTagCompound())
						{
							int amount = stack.getTagCompound().getInteger("WaterAmount");
							if(amount >= 1000)
							{
								TileEntityPurifiedWater pure = new TileEntityPurifiedWater();
								pure.setpurity(stack.getTagCompound().getInteger("purity"));
								stack.getTagCompound().setInteger("WaterAmount", amount - 1000);
								
								worldIn.setBlockState(trace.getBlockPos().up(), HydromancyBlockHandler.purified_water.getDefaultState());
								worldIn.setTileEntity(trace.getBlockPos().up(), pure);
								
								if(amount == 1000)
								{
									stack.getTagCompound().setInteger("purity", 0);
								}
								
								return new ActionResult(EnumActionResult.FAIL, stack);
							}
						}
					}
				}
			}
		}

		if(stack.hasTagCompound())
		{
			if(stack.getTagCompound().getInteger("WaterAmount") >= 1000)
			{
				return super.onItemRightClick(stack, worldIn, playerIn, hand);
			}
		}

		return new ActionResult(EnumActionResult.PASS, stack);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) 
	{
		if(stack.hasTagCompound())
		{ 
			tooltip.add("Water Amount: " + stack.getTagCompound().getInteger("WaterAmount") + " mB");
			tooltip.add("Water Purity: " + stack.getTagCompound().getInteger("purity") + " %");
		}

		super.addInformation(stack, playerIn, tooltip, advanced);
	}



	protected RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
	{
		float f = playerIn.rotationPitch;
		float f1 = playerIn.rotationYaw;
		double d0 = playerIn.posX;
		double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
		double d2 = playerIn.posZ;
		Vec3d vec3d = new Vec3d(d0, d1, d2);
		float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
		float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
		float f4 = -MathHelper.cos(-f * 0.017453292F);
		float f5 = MathHelper.sin(-f * 0.017453292F);
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d3 = 5.0D;
		if (playerIn instanceof net.minecraft.entity.player.EntityPlayerMP)
		{
			d3 = ((net.minecraft.entity.player.EntityPlayerMP)playerIn).interactionManager.getBlockReachDistance();
		}
		Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
		return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
	}
}
