package com.waabbuffet.hydromancy.packet.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PlaceAirBlock implements IMessage, IMessageHandler<PlaceAirBlock , IMessage> {



	int X,Y,Z;
	



	public PlaceAirBlock(){ }

	public PlaceAirBlock(int x, int y, int z)
	{
		
		X = x;
		Y = y;
		Z = z;
		
	}

	@Override
	public IMessage onMessage(PlaceAirBlock message, MessageContext ctx) {

		EntityPlayer p = ctx.getServerHandler().playerEntity;
		World world = p.worldObj;
		
		world.setBlockToAir(message.X, message.Y, message.Z);
	

		return null;
	}


	@Override
	public void fromBytes(ByteBuf buf) {

	
		this.X = buf.readInt();
		this.Y = buf.readInt();
		this.Z = buf.readInt();
		
		
	}

	@Override
	public void toBytes(ByteBuf buf) {

		
		buf.writeInt(X);
		buf.writeInt(Y);
		buf.writeInt(Z);
		
	}


}
