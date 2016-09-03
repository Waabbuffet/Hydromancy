package com.waabbuffet.hydromancy.client.renderer;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderTranslationTable extends TileEntitySpecialRenderer{
	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/blocks/translation_table.png");
	private static final ResourceLocation ttobject_empty = new ResourceLocation(Reference.MODID + ":models/blocks/translation_table.obj");
	//others to be added
	private static final ResourceLocation ttobject_dstone = new ResourceLocation(Reference.MODID + ":models/blocks/translation_table_ds.obj");
	private static final ResourceLocation ttobject_scrolls = new ResourceLocation(Reference.MODID + ":models/blocks/translation_table_scrolls.obj");
	private static final ResourceLocation ttobject_full = new ResourceLocation(Reference.MODID + ":models/blocks/translation_table_full.obj");

	private static IModelCustom model;
	private static IModelCustom model_scrolls;
	private static IModelCustom model_dstone;
	private static IModelCustom model_full;

	//private boolean paper;
	//private boolean stone;

	public RenderTranslationTable() {
		model = AdvancedModelLoader.loadModel(ttobject_empty);
		model_scrolls = AdvancedModelLoader.loadModel(ttobject_scrolls);
		model_dstone = AdvancedModelLoader.loadModel(ttobject_dstone);
		model_full = AdvancedModelLoader.loadModel(ttobject_full);
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float timeSinceLastTick) {
		TileEntityTranslationTable te2 = (TileEntityTranslationTable) te;
		float rotation = te2.rotation;
		boolean paper = te2.hasPaper();
		boolean stone = te2.hasStone();		

		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glTranslated(posX + 0.5, posY, posZ + 0.5);
		GL11.glScalef(0.0674f, 0.0674f/*0.118f*/, 0.0674f);
		GL11.glPushMatrix();
		GL11.glRotatef(rotation, 0F, 1F, 0F);
		if (paper == false && stone == false){
			model.renderAll();					
		}
		else if (paper == true && stone == false){
			model_scrolls.renderAll();
		}
		else if (paper == false && stone == true){
			model_dstone.renderAll();
		}
		else if (paper == true && stone == true){
			model_full.renderAll();
		}
		//System.out.println(paper + " " + stone);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
