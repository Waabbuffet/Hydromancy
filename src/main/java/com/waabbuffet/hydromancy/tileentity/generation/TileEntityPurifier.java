package com.waabbuffet.hydromancy.tileentity.generation;

import scala.reflect.internal.Trees.This;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.PlaceBlock;
import com.waabbuffet.hydromancy.packet.packets.UpdateFluidPurity;
import com.waabbuffet.hydromancy.util.BlockPos;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;


public class TileEntityPurifier extends TileEntity implements IFluidHandler, IInventory
{
	public ItemStack[] inventory;

	public FluidTank waterTank;
	public int BurnTime, completionTime;


	public TileEntityPurifier() {
		waterTank = new FluidTank(4000);
		inventory = new ItemStack[this.getSizeInventory()];
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



	public int getBurnTime() {
		return BurnTime;
	}
	public int getCompletionTime() {
		return completionTime;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) 
	{
		return this.waterTank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) 
	{
		return this.drain(from, resource, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) 
	{
		return this.drain(from, maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) 
	{
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) 
	{
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) 
	{
		return new FluidTankInfo[] { this.waterTank.getInfo() };
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {


		this.waterTank.writeToNBT(nbt);
		nbt.setInteger("BurnTime", this.BurnTime);
		nbt.setInteger("completionTime", completionTime);


		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		nbt.setTag("Items", list);




		super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {


		this.waterTank = this.waterTank.readFromNBT(nbt);
		this.BurnTime = nbt.getInteger("BurnTime");
		this.completionTime = nbt.getInteger("completionTime");




		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot") & 255;
			this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
		}



		super.readFromNBT(nbt);
	}


	@Override
	public void updateEntity() {

	
		
		if(this.waterTank.getFluid() != null)
		{
			if(this.waterTank.getFluid().isFluidEqual(new FluidStack(FluidRegistry.WATER, 0)))
			{
				if(this.BurnTime > 0)
				{
					this.completionTime++;
					this.BurnTime--;
					
					if(this.completionTime == 2400)
					{
						//BlockPos pos = this.getWaterPos();
						BlockPos[] pos = this.get3by3WaterPos();
						int[] x = new int[pos.length], y = new int[pos.length], z = new int[pos.length];
						
					
						if(pos.length == 0)
						{
							return;
						}
						
						
						for(int i =0; i < pos.length; i ++)
						{
							x[i] = pos[i].getX();
							y[i] = pos[i].getY();
							z[i] = pos[i].getZ();
							
						}

						if(!this.worldObj.isRemote)
						{
						//	HydromancyPacketHandler.INSTANCE.sendToServer(new PlaceBlock(new ItemStack(HydromancyBlocksHandler.Block_Purified_Water), pos.getX(), pos.getY(), pos.getZ(), 0));
							HydromancyPacketHandler.INSTANCE.sendToServer(new UpdateFluidPurity(x, y,z, pos.length));
						}


						this.waterTank.drain(1000, true);
						this.completionTime = 0;	
					}
				}else
				{
					if(this.getStackInSlot(0) != null)
					{
						int TempBurning = TileEntityFurnace.getItemBurnTime(this.getStackInSlot(0));

						if(TempBurning > 0)
						{
							this.BurnTime = TempBurning;
							this.decrStackSize(0, 1);
						}

					}
				}
			}
		}




		super.updateEntity();
	}


	public BlockPos[] get3by3WaterPos()
	{
		BlockPos pos = new BlockPos(this.xCoord, this.yCoord -1, this.zCoord);
		int blockMeta = this.getBlockMetadata();
		
		BlockPos[] pos3by3 = new BlockPos[9];
		
		if(blockMeta == 2)
		{
			// north
			pos = pos.east().north(3);
		}else if(blockMeta == 3)
		{
			//south
			pos = pos.east().south();
		}else if(blockMeta == 4)
		{
			//west
			pos = pos.east(3).north();
		}else if(blockMeta ==  5)
		{
			//east
			pos = pos.north().west();
		}
		
		for(int x = 0; x < 3; x ++)
		{
			for(int z = 0; z <3; z ++)
			{
				Block A = this.worldObj.getBlock(pos.getX() + x, pos.getY(), pos.getZ() + z);

				if(A.equals(HydromancyBlocksHandler.Block_Purified_Water) || A.equals(Blocks.water))
				{
					pos3by3[z+ x*3] = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
					
				}
			}
		}
		
		return pos3by3;
	}
	
	
	public BlockPos getWaterPos()
	{
		BlockPos pos = new BlockPos(this.xCoord, this.yCoord -1, this.zCoord);
		int blockMeta = this.getBlockMetadata();
		//south = 3
		//north = 2
		//west = 4
		//east = 5
		
		if(blockMeta == 2)
		{
			// north
			pos = pos.east().north(3);
		}else if(blockMeta == 3)
		{
			//south
			pos = pos.east().south();
		}else if(blockMeta == 4)
		{
			//west
			pos = pos.east(3).north();
		}else if(blockMeta ==  5)
		{
			//east
			pos = pos.north().west();
		}

		for(int x = 0; x < 3; x ++)
		{
			for(int z = 0; z <3; z ++)
			{
				Block A = this.worldObj.getBlock(pos.getX() + x, pos.getY(), pos.getZ() + z);

				if(!A.equals(HydromancyBlocksHandler.Block_Purified_Water))
				{
					if(A.getBlockHardness(this.worldObj,pos.getX() + x, pos.getY(), pos.getZ() + z) < 3 || A.equals(Blocks.air))
					{
						return new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
					}
				}
			}
		}

		return null;
	}


	@Override
	public int getSizeInventory() {
		//change to be dependent on what tier the building is
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return null;
		return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}



	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (stack != null && stack.stackSize == 0)
			stack = null;

		this.inventory[index] = stack;
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64;
	}



	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}


	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}


}
