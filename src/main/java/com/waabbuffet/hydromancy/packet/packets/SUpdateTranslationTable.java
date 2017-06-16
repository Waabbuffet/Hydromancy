package com.waabbuffet.hydromancy.packet.packets;

import com.waabbuffet.hydromancy.tileEntity.TileEntityTranslationTable;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SUpdateTranslationTable implements IMessage, IMessageHandler<SUpdateTranslationTable, IMessage>{

	private boolean translationTableTab;
	private boolean isResearchActive;
	private TileEntityTranslationTable table;
	private NBTTagCompound nbt;
	int xCoord,yCoord,zCoord;
	
	public SUpdateTranslationTable(){}
	
	public SUpdateTranslationTable(TileEntityTranslationTable table){
		nbt = new NBTTagCompound();
		table.writeToNBT(nbt);
	}
	
	@Override
	public IMessage onMessage(SUpdateTranslationTable message, MessageContext ctx) {
		table = (TileEntityTranslationTable) ctx.getServerHandler().playerEntity.getEntityWorld().getTileEntity(new BlockPos(message.nbt.getInteger("x"), message.nbt.getInteger("y"), message.nbt.getInteger("z")));
		table.readFromNBT(message.nbt);
		
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {	
		ByteBufUtils.writeTag(buf, nbt);
		
	}
	
}
