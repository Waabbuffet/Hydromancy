package com.waabbuffet.hydromancy.world;

import java.util.Random;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.blocks.coral.BlockCoral;
import com.waabbuffet.hydromancy.blocks.coral.BlockCoralBase;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class HydromancyWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub

		int posX = chunkX * 16 + random.nextInt(16);
		int posZ = chunkZ * 16 + random.nextInt(16);
		int posY = world.getTopSolidOrLiquidBlock(posX, posZ);
		int cd = 0;

		if(cd == 0)
		{
			if(world.getBiomeGenForCoords(posX, posZ).isEqualTo(BiomeGenBase.ocean) || world.getBiomeGenForCoords(posX, posZ).isEqualTo(BiomeGenBase.deepOcean))
			{
				cd = 30;
				int whichCoral = random.nextInt(7);
				BlockCoralBase coral;
				
				
				switch(whichCoral)
				{
				case 0:
					coral = (BlockCoralBase) HydromancyBlocksHandler.Block_Coral;
					break;
				case 1:
					coral = (BlockCoralBase) HydromancyBlocksHandler.Block_Coral1;
					break;
				case 2:
					coral = (BlockCoralBase) HydromancyBlocksHandler.Block_Coral2;
					break;
				case 3:
					coral = (BlockCoralBase)HydromancyBlocksHandler.Block_Coral3;
					break;
				case 4:
					coral = (BlockCoralBase) HydromancyBlocksHandler.Block_Coral4;
					break;
				case 5:
					coral = (BlockCoralBase)HydromancyBlocksHandler.Block_Coral5;
					break;
				case 6:
					coral = (BlockCoralBase)HydromancyBlocksHandler.Block_Coral6;
					break;
				default:
					coral = (BlockCoralBase) HydromancyBlocksHandler.Block_Coral2;
				}

				coral.spawnCoralRequirements(world, posX, posY, posZ);
			

			}
		}else {
			cd--;
		}
	}



}
