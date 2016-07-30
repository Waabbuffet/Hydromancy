package com.waabbuffet.hydromancy.client.renderer;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.events.ClientTickEventHandler;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;


public class RenderLexicon implements IItemRenderer {


	ModelBook model = new ModelBook();
	ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/models/Lexicon.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();

		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.bindTexture(texture);

		float opening = 0F;
		float pageFlip = 0F;

		float ticks = ClientTickEventHandler.ticksWithLexicaOpen;
		if(ticks > 0 && ticks < 10) {
			ticks += ClientTickEventHandler.partialTicks;

		}

		GL11.glTranslatef(0.3F + 0.02F * ticks, 0.475F + 0.01F * ticks, -0.2F - 0.01F * ticks);
		GL11.glRotatef(87.5F + ticks * 5, 0F, 1F, 0F);
		GL11.glRotatef(ticks * 2.5F, 0F, 0F, 1F);
		GL11.glScalef(0.9F, 0.9F, 0.9F);
		opening = ticks / 12F;

		float pageFlipTicks = ClientTickEventHandler.pageFlipTicks;

		if(pageFlipTicks > 0)
			pageFlipTicks -= ClientTickEventHandler.partialTicks;

		pageFlip = pageFlipTicks / 5F;

		model.render(null, 0F, 0F, pageFlip, opening, 0F, 1F / 16F);


		GL11.glPopMatrix();
	}

}











