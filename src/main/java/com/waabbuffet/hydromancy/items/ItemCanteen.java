package com.waabbuffet.hydromancy.items;

import java.util.List;

import com.waabbuffet.hydromancy.blocks.HydromancyBlockHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionHandler;
import com.waabbuffet.hydromancy.potion.HydromancyPotionTypesHandler;

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
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {

		if(itemStackIn.hasTagCompound())
		{
			if(itemStackIn.getTagCompound().getInteger("WaterAmount") >= 1000)
			{
				return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
			}
		}
		return new ActionResult(EnumActionResult.FAIL, itemStackIn);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) 
	{
		if(stack.hasTagCompound())
		{ 
			tooltip.add("Water Amount: " + stack.getTagCompound().getInteger("WaterAmount") + " mB");
			tooltip.add("Average Water Purity: " + stack.getTagCompound().getInteger("chicekn") + " %");
		}

		super.addInformation(stack, playerIn, tooltip, advanced);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		
		Block water = worldIn.getBlockState(pos.up()).getBlock();
		if(water.equals(HydromancyBlockHandler.purified_water))
		{
			if(stack.hasTagCompound())
			{
				if(stack.getTagCompound().getInteger("WaterAmount") < 5000)
				{
					stack.getTagCompound().setInteger("WaterAmount",stack.getTagCompound().getInteger("WaterAmount") + 1000);
					worldIn.setBlockToAir(pos.up());
				}
			}else 
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("WaterAmount", 1000);
				stack.setTagCompound(tag);
				worldIn.setBlockToAir(pos.up());
			}
		}else if(water.equals(Blocks.AIR))
		{
			if(stack.hasTagCompound())
			{
				int amount = stack.getTagCompound().getInteger("WaterAmount");
				if(amount >= 1000)
				{
					stack.getTagCompound().setInteger("WaterAmount", amount - 1000);
					worldIn.setBlockState(pos.up(), HydromancyBlockHandler.purified_water.getDefaultState());
				}
			}
		}

		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
