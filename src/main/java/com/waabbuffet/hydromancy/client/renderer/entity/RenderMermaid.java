package com.waabbuffet.hydromancy.client.renderer.entity;

import com.waabbuffet.hydromancy.entity.water.EntityMermaid;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderMermaid extends RenderLiving{
	public static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":" + "textures/entity/Mermaid 1.png");
	protected ModelBase modelEntity;
	

	public RenderMermaid(ModelBase p_i1262_1_, float p_i1262_2_) {
		super( p_i1262_1_, p_i1262_2_);
		modelEntity = ((ModelBase) mainModel);
	}
	public void renderMermaid(EntityMermaid entity, double x, double y, double z, float u, float v){
		super.doRender(entity, x, y, z, u, v);
	}
	
	public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float u, float v){
		renderMermaid((EntityMermaid)entityLiving, x, y, z, u , v);
	}
	public void doRender(Entity entity, double x, double y, double z, float u, float v){
		renderMermaid((EntityMermaid)entity, x, y, z, u , v);
	}

	@Override
	public void bindTexture(ResourceLocation location) {
		  this.renderManager.renderEngine.bindTexture(location);
	}
	
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}
}
