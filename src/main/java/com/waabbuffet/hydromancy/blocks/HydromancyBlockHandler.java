package com.waabbuffet.hydromancy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DModel.Mesh;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.waabbuffet.hydromancy.blocks.fluid.FluidPurifiedWater;
import com.waabbuffet.hydromancy.util.Reference;

public class HydromancyBlockHandler 
{
	private static ModelResourceLocation purified_water_location =new ModelResourceLocation(Reference.MODID +":purified_0_water","fluid");

	public static Fluid purified_water_fluid;
	public static Block purified_water;

	public static void init()
	{
		purified_water_fluid = new FluidPurifiedWater();
		purified_water = new BlockPurifiedWater("purified_0_water", purified_water_fluid);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders()
	{
		//	registerRender(BlockExit);	
		registerFluidRender(purified_water);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID +":" + block.getUnlocalizedName().substring(5),"inventory"));
	}

	@SideOnly(Side.CLIENT)
	public static void registerFluidRender(final Block block)
	{
		Item item = Item.getItemFromBlock(block);


		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition()
		{
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return purified_water_location;
			}
		});


		ModelLoader.setCustomStateMapper(block, new StateMapperBase()
		{
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return  purified_water_location;
			}
		});
	}
}
