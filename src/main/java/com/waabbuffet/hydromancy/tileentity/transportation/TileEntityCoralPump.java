package com.waabbuffet.hydromancy.tileentity.transportation;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.PlaceBlock;
import com.waabbuffet.hydromancy.util.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityCoralPump extends TileEntity {

	Block CurrentFluidBlock;
	String DestinationDirection = "";
	int Cooldown, Range = 5;


	public TileEntityCoralPump() {
		// TODO Auto-generated constructor stub
		this.CurrentFluidBlock = HydromancyBlocksHandler.Block_Purified_Water;
	}

	public void setDestinationDirection(int meta) {
		if(meta == 0)
		{
			// north
			DestinationDirection = "NORTH";
		}else if(meta == 2)
		{
			//south
			DestinationDirection = "SOUTH";
		}else if(meta== 3)
		{
			//west
			DestinationDirection = "WEST";
		}else if(meta ==  1)
		{
			//east
			DestinationDirection = "EAST";
		}
	}



	@Override
	public Packet getDescriptionPacket() {
		//Debug
		//	System.out.println("[DEBUG]:Server sent tile sync packet");

		NBTTagCompound tagCompound = new NBTTagCompound();
		this.writeToNBT(tagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		//Debug
		//	System.out.println("[DEBUG]:Client recived tile sync packet");

		readFromNBT(pkt.func_148857_g());
		worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.markDirty();
	}




	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub

		this.CurrentFluidBlock = Block.getBlockById(nbt.getInteger("CurrentFluidBlock"));
		this.DestinationDirection = nbt.getString("DestinationDirection");

		super.readFromNBT(nbt);
	}


	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub

		if(this.CurrentFluidBlock != null)
			nbt.setInteger("CurrentFluidBlock", Block.getIdFromBlock(CurrentFluidBlock));


		if(this.DestinationDirection != null && this.DestinationDirection != "")
			nbt.setString("DestinationDirection", DestinationDirection);


		super.writeToNBT(nbt);
	}


	@Override
	public void updateEntity() {



		if(this.Cooldown <= 0)
		{
			this.Cooldown = 100;

			//sets fluid type
			if(this.worldObj.getBlock(xCoord, yCoord - 1, zCoord).equals(Blocks.water) || this.worldObj.getBlock(xCoord, yCoord - 1, zCoord).equals(Blocks.lava) || this.worldObj.getBlock(xCoord, yCoord - 1, zCoord).equals(HydromancyBlocksHandler.Block_Purified_Water))
			{
				this.CurrentFluidBlock = this.worldObj.getBlock(xCoord, yCoord - 1, zCoord);
			}


			for(int i =0; i < this.Range; i ++)
			{
				BlockPos currentBlockPos = null;
				BlockPos infrontBlockPos = null;

				BlockPos behindTileBlockPos = null;


				Block currentBlock = null;
				Block infrontBlock = null;

				Block behindTileBlock = null;



				int currentBlockMeta = 0;
				int infrontBlockMeta = 0;

				int behindTileBlockMeta = 0;

				if(this.DestinationDirection.contains("EAST"))
				{
					currentBlockPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord).west(i);
					infrontBlockPos = currentBlockPos.west();
					behindTileBlockPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord).east();

				}else if(this.DestinationDirection.contains("WEST"))
				{
					currentBlockPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord).east(i);
					infrontBlockPos = currentBlockPos.east();
					behindTileBlockPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord).west();

				}else if(this.DestinationDirection.contains("NORTH"))
				{
					currentBlockPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord).north(i);
					infrontBlockPos = currentBlockPos.north();
					behindTileBlockPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord).south();

				}else if(this.DestinationDirection.contains("SOUTH"))
				{
					currentBlockPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord).south(i);
					infrontBlockPos = currentBlockPos.south();
					behindTileBlockPos = new BlockPos(this.xCoord, this.yCoord, this.zCoord).north();

				}

				currentBlock = this.worldObj.getBlock(currentBlockPos.getX(), currentBlockPos.getY(), currentBlockPos.getZ());
				infrontBlock = this.worldObj.getBlock(infrontBlockPos.getX(), infrontBlockPos.getY(), infrontBlockPos.getZ());
				behindTileBlock = this.worldObj.getBlock(behindTileBlockPos.getX(), behindTileBlockPos.getY(), behindTileBlockPos.getZ());

				currentBlockMeta = this.worldObj.getBlockMetadata(currentBlockPos.getX(), currentBlockPos.getY(), currentBlockPos.getZ());
				infrontBlockMeta = this.worldObj.getBlockMetadata(infrontBlockPos.getX(), infrontBlockPos.getY(), infrontBlockPos.getZ());
				behindTileBlockMeta = this.worldObj.getBlockMetadata(behindTileBlockPos.getX(), behindTileBlockPos.getY(), behindTileBlockPos.getZ());

				if(currentBlock == null || infrontBlock == null)
				{
					return;
				}

				if(i == 1)
				{

					if(behindTileBlock.equals(this.CurrentFluidBlock) && behindTileBlockMeta == 0)
					{

						if(currentBlock.equals(Blocks.air) || this.isWantedNonSourceBlock(currentBlock, currentBlockMeta))
						{

							if(!this.worldObj.isRemote)
								HydromancyPacketHandler.INSTANCE.sendToServer(new PlaceBlock(new ItemStack(this.CurrentFluidBlock), currentBlockPos.getX(), currentBlockPos.getY(), currentBlockPos.getZ(), 0, behindTileBlockPos.getX(),behindTileBlockPos.getY(), behindTileBlockPos.getZ()));
						}
					}
				}


				if(currentBlock.equals(this.CurrentFluidBlock) && currentBlockMeta == 0)
				{
					if(infrontBlock.equals(Blocks.air) || this.isWantedNonSourceBlock(infrontBlock, infrontBlockMeta))
					{
						if(!this.worldObj.isRemote)
							HydromancyPacketHandler.INSTANCE.sendToServer(new PlaceBlock(new ItemStack(this.CurrentFluidBlock), infrontBlockPos.getX(), infrontBlockPos.getY(), infrontBlockPos.getZ(), 0, currentBlockPos.getX(),currentBlockPos.getY(), currentBlockPos.getZ()));
					}
				}
			}

		}else {this.Cooldown--;}


		super.updateEntity();
	}

	public boolean isWantedNonSourceBlock(Block block, int meta)
	{
		if(block.equals(this.CurrentFluidBlock) && meta != 0)
		{
			return true;
		}

		return false;
	}
}







