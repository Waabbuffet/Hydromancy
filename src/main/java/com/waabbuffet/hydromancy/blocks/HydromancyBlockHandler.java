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
import com.waabbuffet.hydromancy.blocks.lexicon.BlockTranslationTable;
import com.waabbuffet.hydromancy.blocks.purification.BlockObelisk;
import com.waabbuffet.hydromancy.blocks.purification.coral.BlockBlueCoral;
import com.waabbuffet.hydromancy.blocks.purification.coral.BlockGreenCoral;
import com.waabbuffet.hydromancy.blocks.purification.coral.BlockOrangeCoral;
import com.waabbuffet.hydromancy.blocks.purification.coral.BlockPurpleCoral;
import com.waabbuffet.hydromancy.blocks.purification.coral.BlockYellowCoral;
import com.waabbuffet.hydromancy.util.Reference;

public class HydromancyBlockHandler 
{
	private static ModelResourceLocation purified_water_location = new ModelResourceLocation(Reference.MODID +":purified_0_water","fluid");

	public static Fluid purified_water_fluid;
	public static Block purified_water, coral_blue, coral_green, coral_orange, coral_purple, coral_yellow, coral_pink, coral_black, 
	coral_cyan, block_obelisk;
	public static Block translation_table;
	public static Block purifier;

	public static void init()
	{
		purified_water_fluid = new FluidPurifiedWater();
		purified_water = new BlockPurifiedWater("purified_0_water", purified_water_fluid);
		
		coral_blue = new BlockBlueCoral("blue_coral");
		coral_green = new BlockGreenCoral("green_coral");
		coral_orange = new BlockOrangeCoral("orange_coral");
		coral_purple = new BlockPurpleCoral("purple_coral");
		coral_yellow = new BlockYellowCoral("yellow_coral");
		coral_pink = new BlockYellowCoral("pink_coral");
		coral_black = new BlockYellowCoral("black_coral");
		coral_cyan = new BlockYellowCoral("cyan_coral");
		block_obelisk = new BlockObelisk("obelisk");
		
		translation_table = new BlockTranslationTable("translation_table");
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders()
	{
		//	registerRender(BlockExit);	
		registerFluidRender(purified_water);
		
		registerRender(coral_blue);
		registerRender(coral_green);
		registerRender(coral_orange);
		registerRender(coral_purple);
		registerRender(coral_yellow);
		registerRender(coral_pink);
		registerRender(coral_black);
		registerRender(coral_cyan);
		
		registerRender(block_obelisk);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID +":" + block.getUnlocalizedName().substring(5),"inventory"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerFluidRenders()
	{
		registerFluidRender(purified_water);
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
