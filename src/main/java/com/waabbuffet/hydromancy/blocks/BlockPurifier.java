package com.waabbuffet.hydromancy.blocks;

import com.waabbuffet.hydromancy.tileentity.TileEntityPurifier;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPurifier extends Block implements ITileEntityProvider
{
	public BlockPurifier(String name) 
	{
        super(Material.iron);
        this.setBlockName(name);
        this.setBlockTextureName(Reference.MODID + ":" + name);
        this.setHardness(2.0f);
        this.setResistance(6.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.isBlockContainer = true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) 
    {
        return new TileEntityPurifier();
    }
}