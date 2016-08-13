package com.waabbuffet.hydromancy.events;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.items.lexicon.ItemLexicon;
import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifiedWater;
import com.waabbuffet.hydromancy.util.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class CommonEventHandler {

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event) {

		ItemStack result = fillCustomBucket(event.world, event.target);

		if (result == null)
			return;

		event.result = result;
		event.setResult(Result.ALLOW);
	}

	public ItemStack fillCustomBucket(World world, MovingObjectPosition pos) 
	{
		Block blockID = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

		if ((blockID == HydromancyBlocksHandler.Block_Purified_Water) && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0)
		{

			ItemStack bucket = new ItemStack(HydromancyItemsHandler.purified_bucket);
			TileEntityPurifiedWater water = (TileEntityPurifiedWater) world.getTileEntity(pos.blockX, pos.blockY, pos.blockZ);

			if(water != null)
			{
				if(bucket.hasTagCompound())
				{

					bucket.getTagCompound().setInteger("Purity", water.getPurity());
				}else
				{

					NBTTagCompound tag = new NBTTagCompound();
					tag.setInteger("Purity", water.getPurity());
					bucket.setTagCompound(tag);
				}
			}

			world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);

			return bucket;
		} else
			return null;
	}



	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing e) {

		if (e.entity instanceof EntityPlayer) {
			HydromancyPlayerProperties.register((EntityPlayer) e.entity);
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {

		if (e.entity instanceof EntityPlayer) {
			HydromancyPlayerProperties.get((EntityPlayer) e.entity).syncLexicon();
		}


	}


	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone e) {
		NBTTagCompound nbt = new NBTTagCompound();


		HydromancyPlayerProperties.get(e.original).saveReviveRelevantNBTData(nbt, e.wasDeath);
		HydromancyPlayerProperties.get(e.entityPlayer).loadNBTData(nbt);
	}




}
