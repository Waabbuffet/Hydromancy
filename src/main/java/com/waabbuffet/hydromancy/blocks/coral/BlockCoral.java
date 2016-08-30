package com.waabbuffet.hydromancy.blocks.coral;

import java.util.Random;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.PlayerLexiconUpdate;
import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.util.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class BlockCoral extends BlockCoralBase {


	@Override
	public boolean isOpaqueCube() {
		// TODO Auto-generated method stub

		return false;
	}


	@Override
	public boolean canBlockStay(World world, int p_149718_2_, int p_149718_3_, int p_149718_4_) {

		return this.PlacementRequirements(world, p_149718_2_, p_149718_3_, p_149718_4_);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {

		return this.PlacementRequirements(world, x, y, z);
	}

	public boolean PlacementRequirements(World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x,y,z);

		Block blockWEST = world.getBlock(pos.west().getX(), pos.west().getY(), pos.west().getZ());
		Block blockEAST = world.getBlock(pos.east().getX(), pos.east().getY(), pos.east().getZ());
		Block blockNORTH = world.getBlock(pos.north().getX(), pos.north().getY(), pos.north().getZ());
		Block blockSOUTH = world.getBlock(pos.south().getX(), pos.south().getY(), pos.south().getZ());

		if(blockWEST.equals(HydromancyBlocksHandler.Block_Coral1) || blockWEST.equals(HydromancyBlocksHandler.Block_Coral3) || blockWEST.equals(HydromancyBlocksHandler.Block_Coral6))
		{
			return true;
		}else if((blockEAST.equals(HydromancyBlocksHandler.Block_Coral1) || blockEAST.equals(HydromancyBlocksHandler.Block_Coral3) || blockEAST.equals(HydromancyBlocksHandler.Block_Coral6)))
		{
			return true;
		}else if((blockNORTH.equals(HydromancyBlocksHandler.Block_Coral1) || blockNORTH.equals(HydromancyBlocksHandler.Block_Coral3) || blockNORTH.equals(HydromancyBlocksHandler.Block_Coral6)))
		{
			return true;
		}else if((blockSOUTH.equals(HydromancyBlocksHandler.Block_Coral1) || blockSOUTH.equals(HydromancyBlocksHandler.Block_Coral3) || blockSOUTH.equals(HydromancyBlocksHandler.Block_Coral6)))
		{
			return true;
		}

		return false;
	}
	
	
	
	
	@Override
	public void spawnCoralRequirements(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, HydromancyBlocksHandler.Block_Coral);
		world.setBlock(x + 1, y, z, HydromancyBlocksHandler.Block_Coral1);
	
	}

}
