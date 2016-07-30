package com.waabbuffet.hydromancy.util;

import net.minecraft.util.EnumFacing;

public class BlockPos {

	int BlockX,  BlockY, BlockZ;

	public BlockPos(int blockX, int blockY, int blockZ) {
		
		BlockX = blockX;
		BlockY = blockY;
		BlockZ = blockZ;
	}
	
	
	public int getX() {
		return BlockX;
	}
	
	public int getY() {
		return BlockY;
	}
	
	public int getZ() {
		return BlockZ;
	}
	
	  public BlockPos offset(EnumFacing facing, int n)
	    {
	        return n == 0 ? this : new BlockPos(this.getX() + facing.getFrontOffsetX() * n, this.getY() + facing.getFrontOffsetY() * n, this.getZ() + facing.getFrontOffsetZ() * n);
	    }
	
	 public BlockPos up()
	    {
	        return this.up(1);
	    }

	    /**
	     * Offset this BlockPos n blocks up
	     */
	    public BlockPos up(int n)
	    {
	        return this.offset(EnumFacing.UP, n);
	    }

	    /**
	     * Offset this BlockPos 1 block down
	     */
	    public BlockPos down()
	    {
	        return this.down(1);
	    }

	    /**
	     * Offset this BlockPos n blocks down
	     */
	    public BlockPos down(int n)
	    {
	        return this.offset(EnumFacing.DOWN, n);
	    }

	    /**
	     * Offset this BlockPos 1 block in northern direction
	     */
	    public BlockPos north()
	    {
	        return this.north(1);
	    }

	    /**
	     * Offset this BlockPos n blocks in northern direction
	     */
	    public BlockPos north(int n)
	    {
	        return this.offset(EnumFacing.NORTH, n);
	    }

	    /**
	     * Offset this BlockPos 1 block in southern direction
	     */
	    public BlockPos south()
	    {
	        return this.south(1);
	    }

	    /**
	     * Offset this BlockPos n blocks in southern direction
	     */
	    public BlockPos south(int n)
	    {
	        return this.offset(EnumFacing.SOUTH, n);
	    }

	    /**
	     * Offset this BlockPos 1 block in western direction
	     */
	    public BlockPos west()
	    {
	        return this.west(1);
	    }

	    /**
	     * Offset this BlockPos n blocks in western direction
	     */
	    public BlockPos west(int n)
	    {
	        return this.offset(EnumFacing.WEST, n);
	    }

	    /**
	     * Offset this BlockPos 1 block in eastern direction
	     */
	    public BlockPos east()
	    {
	        return this.east(1);
	    }

	    /**
	     * Offset this BlockPos n blocks in eastern direction
	     */
	    public BlockPos east(int n)
	    {
	        return this.offset(EnumFacing.EAST, n);
	    }

	
	
}
