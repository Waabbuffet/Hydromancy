package com.waabbuffet.hydromancy.client.renderer.entity.spells;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.entity.spells.EntityMagicPortal;
import com.waabbuffet.hydromancy.spells.particles.HydromancyParticleHandler;

public class RenderMagicPortal extends Render {

	int i =0;
	private static final ResourceLocation projectile = new ResourceLocation("textures/atlas/items.png");

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1){
		doRenderSpellProjectile((EntityMagicPortal)entity, d0, d1, d2, f, f1);
	}

	private void doRenderSpellProjectile(EntityMagicPortal entity, double d, double d1, double d2, float f, float f1){
		
		
		GL11.glPushMatrix();
		
		i ++;
		
		GL11.glTranslated(d, d1, d2);
		GL11.glScalef(1.2f, 1.2f, 1.2f);
		
		IIcon IIcon = HydromancyParticleHandler.TestGui;
		
		Tessellator tessellator = Tessellator.instance;
		Minecraft.getMinecraft().renderEngine.bindTexture(projectile);

		tessellator.startDrawingQuads();
	
		GL11.glRotatef(180F - RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);
		
		// x, z ,y (-z + x)
		tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, IIcon.getMinU(), IIcon.getMaxV());
		tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, IIcon.getMaxU(), IIcon.getMaxV());
		tessellator.addVertexWithUV(1.0D, 0D, 0.0D, IIcon.getMaxU(), IIcon.getMinV());
		tessellator.addVertexWithUV(0.0D, 0D, 0.0D, IIcon.getMinU(), IIcon.getMinV());
		
		tessellator.addVertexWithUV(0.0D, 0D, 0.0D, IIcon.getMinU(), IIcon.getMinV());
		tessellator.addVertexWithUV(1.0D, 0D, 0.0D, IIcon.getMaxU(), IIcon.getMinV());
		tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, IIcon.getMaxU(), IIcon.getMaxV());
		tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, IIcon.getMinU(), IIcon.getMaxV());
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor3f(1f, 1f, 1f);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(32826); // RESCALE_NORMAL_EXT 
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
		GL11.glDepthMask(false);
	/*	
		tessellator.addVertexWithUV(0.0D, 0.0D, 1.0D, IIcon.getMinU(), IIcon.getMaxV());
		tessellator.addVertexWithUV(0.0D, 1.0D, 1.0D, IIcon.getMaxU(), IIcon.getMaxV());
		tessellator.addVertexWithUV(0D, 1D, 0.0D, IIcon.getMaxU(), IIcon.getMinV());
		tessellator.addVertexWithUV(0.0D, 0D, 0.0D, IIcon.getMinU(), IIcon.getMinV());
			
		tessellator.addVertexWithUV(0.0D, 0.25D, 3.0D, HydromancyParticleHandler.RedBeam.getMinU(),HydromancyParticleHandler.RedBeam.getMaxV());
		tessellator.addVertexWithUV(0.0D, 0.75D, 3.0D, HydromancyParticleHandler.RedBeam.getMaxU(), HydromancyParticleHandler.RedBeam.getMaxV());
		tessellator.addVertexWithUV(0D, 0.75D, 0.0D, HydromancyParticleHandler.RedBeam.getMaxU(), HydromancyParticleHandler.RedBeam.getMinV());
		tessellator.addVertexWithUV(0.0D, 0.25D, 0.0D, HydromancyParticleHandler.RedBeam.getMinU(), HydromancyParticleHandler.RedBeam.getMinV());
	*/
		

		tessellator.draw();
		
		
		GL11.glPopMatrix();
		

	}

	private void renderIcon(IIcon IIcon, int renderColor){
		Tessellator tessellator = Tessellator.instance;
		float f = 1.0F;
		float f1 = 0.5F;
		float f2 = 0.25F;

		tessellator.startDrawingQuads();
		RenderHelper.disableStandardItemLighting();
		tessellator.setColorRGBA((renderColor & 0xFF0000) >> 16, (renderColor & 0x00FF00) >> 8, renderColor & 0x0000FF, 255);

		tessellator.addVertexWithUV(0.0F - f1, 0.0F - f2, 0.0D, IIcon.getMinU(), IIcon.getMaxV());
		tessellator.addVertexWithUV(f - f1, 0.0F - f2, 0.0D, IIcon.getMaxU(), IIcon.getMaxV());
		tessellator.addVertexWithUV(f - f1, f - f2, 0.0D, IIcon.getMaxU(), IIcon.getMinV());
		tessellator.addVertexWithUV(0.0F - f1, f - f2, 0.0D, IIcon.getMinU(), IIcon.getMinV());
		tessellator.draw();
		RenderHelper.enableStandardItemLighting();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity){
		return null;
	}
}