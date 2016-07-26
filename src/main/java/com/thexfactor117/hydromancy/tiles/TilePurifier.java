package com.thexfactor117.hydromancy.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * 
 * @author TheXFactor117
 *
 */
public class TilePurifier extends TileEntity implements IFluidHandler
{
	public FluidTank waterTank = new FluidTank(4000);
	public boolean needsUpdate = false;
	public int updateTimer = 0;
	
	@Override
	public void updateEntity()
	{

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
}
