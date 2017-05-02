package com.waabbuffet.hydromancy.packet.packets;

import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SUpdateTranslationTable implements IMessage, IMessageHandler<SUpdateTranslationTable, IMessage>{

	private boolean translationTableTab;
	private boolean isResearchActive;
	private TileEntityTranslationTable table;
	private NBTTagCompound nbt;
	int xCoord,yCoord,zCoord;
	
	public SUpdateTranslationTable(){}
	
	public SUpdateTranslationTable(TileEntityTranslationTable table){
		nbt = new NBTTagCompound();
		/*this.translationTableTab = translationTableTab;
		this.isResearchActive = isResearchActive;	
		this.table = table;
		xCoord = table.xCoord;
		yCoord = table.yCoord;
		zCoord = table.zCoord;*/
		table.writeToNBT(nbt);
	}
	
	@Override
	public IMessage onMessage(SUpdateTranslationTable message, MessageContext ctx) {
		table = (TileEntityTranslationTable) ctx.getServerHandler().playerEntity.getEntityWorld().getTileEntity(message.nbt.getInteger("x"), message.nbt.getInteger("y"), message.nbt.getInteger("z"));
		table.readFromNBT(message.nbt);
		/*table.isTabResearch = message.translationTableTab;
		table.researchInProgress = message.isResearchActive;
		
		System.out.println("SERVER recieved packet");
		System.out.println("tab: " + message.translationTableTab);
		System.out.println("active: " + message.isResearchActive);*/
		
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
		
		/*translationTableTab = nbt.getBoolean("ttt");
		isResearchActive = nbt.getBoolean("ira");
		xCoord = nbt.getInteger("x");
		yCoord = nbt.getInteger("y");
		zCoord = nbt.getInteger("z");*/
	}

	@Override
	public void toBytes(ByteBuf buf) {
		/*nbt = new NBTTagCompound();
		
		nbt.setBoolean("ttt", translationTableTab);
		nbt.setBoolean("ira", isResearchActive);
		nbt.setInteger("x", xCoord);
		nbt.setInteger("y", yCoord);
		nbt.setInteger("z", zCoord);*/
		
		ByteBufUtils.writeTag(buf, nbt);
		
	}
	
}
