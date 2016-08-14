package com.waabbuffet.hydromancy.blocks.coral;

import java.util.Random;

import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.PlayerLexiconUpdate;
import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class BlockCoral extends BlockBush {

	public BlockCoral() {
		super(Material.cactus);
		// TODO Auto-generated constructor stub
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public boolean isOpaqueCube() {
		// TODO Auto-generated method stub
		
		return false;
	}


	@Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
		// TODO Auto-generated method stub



		super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
	}

	@Override
	public boolean onBlockActivated(World world, int p_149727_2_,
			int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_,
			int p_149727_6_, float p_149727_7_, float p_149727_8_,
			float p_149727_9_) {


	if(!world.isRemote)
	{
		HydromancyPlayerProperties.get(p_149727_5_).unlockPage(0, 0, true);
		HydromancyPacketHandler.INSTANCE.sendTo(new PlayerLexiconUpdate(0, 0), (EntityPlayerMP) p_149727_5_);
	}

		return super.onBlockActivated(world, p_149727_2_, p_149727_3_,
				p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_,
				p_149727_9_);
	}

}
