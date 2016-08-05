package com.waabbuffet.hydromancy.packet.packets;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.blocks.generation.BlockPurifiedWater;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifiedWater;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class UpdateFluidPurity implements IMessage, IMessageHandler<UpdateFluidPurity , IMessage> {

	boolean isArray;
	int X,Y,Z, Size;
	int[] X1, Y1, Z1, Meta1;


	public UpdateFluidPurity(){ }


	public UpdateFluidPurity(int x, int y, int z)
	{
		
		X = x;
		Y = y;
		Z = z;
		isArray = false;
	}


	public UpdateFluidPurity(int[] x, int[] y, int[] z, int size)
	{
		
		X1 = x;
		Y1 = y;
		Z1 = z;
		Size = size;
		isArray = true;
	}


	@Override
	public IMessage onMessage(UpdateFluidPurity message, MessageContext ctx) {

		EntityPlayer p = ctx.getServerHandler().playerEntity;
		World world = p.worldObj;

		if(!message.isArray)
		{
			if(world.getBlock(message.X, message.Y, message.Z) instanceof BlockPurifiedWater)
			{
				if(world.getBlockMetadata(message.X, message.Y, message.Z) == 0)
				{
					TileEntityPurifiedWater water = (TileEntityPurifiedWater) world.getTileEntity(message.X, message.Y, message.Z);
					
					if(water == null)
					{
						return null;
					}
					
					water.increasePurity(10);
				}
			}else if(world.getBlock(message.X, message.Y, message.Z) instanceof BlockLiquid)
			{
				
				world.setBlock(message.X, message.Y, message.Z, HydromancyBlocksHandler.Block_Purified_Water);
				world.setBlockMetadataWithNotify(message.X, message.Y, message.Z, 0, 2);
			}
			
		}else
		{
			for(int i =0; i < message.Size; i++)
			{
				if(world.getBlock(message.X1[i], message.Y1[i], message.Z1[i]) instanceof BlockPurifiedWater)
				{
					if(world.getBlockMetadata(message.X1[i], message.Y1[i], message.Z1[i]) == 0)
					{
						TileEntityPurifiedWater water = (TileEntityPurifiedWater) world.getTileEntity(message.X1[i], message.Y1[i], message.Z1[i]);
						
						if(water == null)
						{
							continue;
						}
						
						water.increasePurity(10);
					
					}
				}else if(world.getBlock(message.X1[i], message.Y1[i], message.Z1[i]) instanceof BlockLiquid)
				{
					
					world.setBlock(message.X1[i], message.Y1[i], message.Z1[i], HydromancyBlocksHandler.Block_Purified_Water);
					world.setBlockMetadataWithNotify(message.X1[i], message.Y1[i], message.Z1[i], 0, 2);
				}
				
			}
		}

		return null;
	}



	@Override
	public void fromBytes(ByteBuf buf) {

		this.isArray = buf.readBoolean();
		
		if(!this.isArray)
		{
			this.X = buf.readInt();
			this.Y = buf.readInt();
			this.Z = buf.readInt();
		}else
		{
			this.Size = buf.readInt();
			this.X1 = new int[Size];
			this.Y1 = new int[Size];
			this.Z1 = new int[Size];
			
			for(int i =0; i < this.Size; i ++)
			{
				this.X1[i] = buf.readInt();
				this.Y1[i] = buf.readInt();
				this.Z1[i] = buf.readInt();
			}
		}

	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeBoolean(this.isArray);

		if(!this.isArray)
		{

			buf.writeInt(X);
			buf.writeInt(Y);
			buf.writeInt(Z);

		}else
		{
		
			buf.writeInt(this.Size);

			for(int i = 0; i < this.Size; i ++)
			{
				
				buf.writeInt(X1[i]);
				buf.writeInt(Y1[i]);
				buf.writeInt(Z1[i]);

			}
		}
	}


}
