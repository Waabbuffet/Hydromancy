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

public class PlaceBlock implements IMessage, IMessageHandler<PlaceBlock , IMessage> {


	ItemStack Block;
	int X,Y,Z, Meta;



	public PlaceBlock(){ }


	public PlaceBlock(ItemStack block, int x, int y, int z, int meta)
	{
		Block = block;
		X = x;
		Y = y;
		Z = z;
		Meta = meta;
	}





	@Override
	public IMessage onMessage(PlaceBlock message, MessageContext ctx) {

		EntityPlayer p = ctx.getServerHandler().playerEntity;
		World world = p.worldObj;

		Block b = net.minecraft.block.Block.getBlockFromItem(message.Block.getItem());

		if(b != null)
		{
			world.setBlock(message.X, message.Y, message.Z, b);
			world.setBlockMetadataWithNotify(message.X, message.Y, message.Z, message.Meta, 2);
		}

		return null;
	}






	@Override
	public void fromBytes(ByteBuf buf) {

		this.Block = ByteBufUtils.readItemStack(buf);
		this.X = buf.readInt();
		this.Y = buf.readInt();
		this.Z = buf.readInt();
		this.Meta = buf.readInt();




	}

	@Override
	public void toBytes(ByteBuf buf) {

		ByteBufUtils.writeItemStack(buf, Block);
		buf.writeInt(X);
		buf.writeInt(Y);
		buf.writeInt(Z);
		buf.writeInt(Meta);
	}


}
