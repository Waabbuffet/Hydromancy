package com.waabbuffet.hydromancy.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMermaid extends ModelBase
{
	//fields
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer tail4;
	ModelRenderer tail5;
	ModelRenderer tail6;
	ModelRenderer headfin2;
	ModelRenderer headfin3;
	ModelRenderer tail1fin;
	ModelRenderer tail2fin;
	ModelRenderer tail3fin;
	ModelRenderer trident;
	ModelRenderer breast;

	public ModelMermaid()
	{
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);

		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(128, 128);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);

		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(128, 128);
		rightarm.mirror = true;
		setRotation(rightarm, -0.0872665F, 0F, 0F);

		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(128, 128);
		leftarm.mirror = true;
		setRotation(leftarm, 0.5235988F, 0F, 0F);

		tail1 = new ModelRenderer(this, 0, 33);
		tail1.addBox(-4F, 0F, -2F, 8, 8, 4);
		tail1.setRotationPoint(0F, 11F, 0F);
		tail1.setTextureSize(128, 128);
		tail1.mirror = true;
		setRotation(tail1, -0.2617994F, 0F, 0F);

		tail2 = new ModelRenderer(this, 0, 45);
		tail2.addBox(-3.5F, 0F, -1.5F, 7, 6, 3);
		tail2.setRotationPoint(0F, 18F, -1.5F);
		tail2.setTextureSize(128, 128);
		tail2.mirror = true;
		setRotation(tail2, -0.6981317F, 0F, 0F);

		tail3 = new ModelRenderer(this, 0, 54);
		tail3.addBox(-3F, 0F, -1F, 6, 6, 2);
		tail3.setRotationPoint(0F, 22F, -4.033333F);
		tail3.setTextureSize(128, 128);
		tail3.mirror = true;
		setRotation(tail3, -1.396263F, 0F, 0F);

		tail4 = new ModelRenderer(this, 0, 16);
		tail4.addBox(-2F, 0F, -0.5F, 4, 8, 1);
		tail4.setRotationPoint(-1.466667F, 23F, -9.033334F);
		tail4.setTextureSize(128, 128);
		tail4.mirror = true;
		setRotation(tail4, -1.570796F, 0.3490659F, 0F);

		tail5 = new ModelRenderer(this, 0, 16);
		tail5.addBox(-2F, 0F, -0.5F, 4, 8, 1);
		tail5.setRotationPoint(1F, 23F, -9.033334F);
		tail5.setTextureSize(128, 128);
		tail5.mirror = true;
		setRotation(tail5, -1.570796F, -0.3490659F, 0F);

		tail6 = new ModelRenderer(this, 0, 16);
		tail6.addBox(-2F, 0F, -0.5F, 4, 8, 1);
		tail6.setRotationPoint(0F, 23F, -9.033334F);
		tail6.setTextureSize(128, 128);
		tail6.mirror = true;
		setRotation(tail6, -1.570796F, 0F, 0F);

		headfin2 = new ModelRenderer(this, 32, 0);
		headfin2.addBox(3.5F, -4.5F, -2.5F, 1, 3, 4);
		headfin2.setRotationPoint(0F, 0F, 0F);
		headfin2.setTextureSize(128, 128);
		headfin2.mirror = true;
		setRotation(headfin2, 0F, -0.296706F, 0F);

		headfin3 = new ModelRenderer(this, 32, 0);
		headfin3.addBox(-4.5F, -4.5F, -2.5F, 1, 3, 4);
		headfin3.setRotationPoint(0F, 0F, 0F);
		headfin3.setTextureSize(128, 128);
		headfin3.mirror = true;
		setRotation(headfin3, 0F, 0.296706F, 0F);

		tail1fin = new ModelRenderer(this, 24, 33);
		tail1fin.addBox(-0.5F, 0F, -3.5F, 1, 8, 4);
		tail1fin.setRotationPoint(0F, 11F, 0F);
		tail1fin.setTextureSize(128, 128);
		tail1fin.mirror = true;
		setRotation(tail1fin, -0.2617994F, 0F, 0F);

		tail2fin = new ModelRenderer(this, 20, 45);
		tail2fin.addBox(-0.5F, 0F, -2.5F, 1, 6, 3);
		tail2fin.setRotationPoint(0F, 18F, -1.5F);
		tail2fin.setTextureSize(128, 128);
		tail2fin.mirror = true;
		setRotation(tail2fin, -0.6981317F, 0F, 0F);

		tail3fin = new ModelRenderer(this, 16, 54);
		tail3fin.addBox(-0.5F, 0F, -1.5F, 1, 7, 2);
		tail3fin.setRotationPoint(0F, 22F, -4.033333F);
		tail3fin.setTextureSize(128, 128);
		tail3fin.mirror = true;
		setRotation(tail3fin, -1.396263F, 0F, 0F);

		trident = new ModelRenderer(this, 56, 21);
		trident.addBox(-0.5F, -10F, -2F, 1, 16, 16);
		trident.setRotationPoint(6F, 6F, 3F);
		trident.setTextureSize(128, 128);
		trident.mirror = true;
		setRotation(trident, 0F, 0F, 0F);

		breast = new ModelRenderer(this, 16, 16);
		breast.addBox(-4F, 0F, -2F, 8, 4, 4);
		breast.setRotationPoint(0F, 1F, 0F);
		breast.setTextureSize(128, 128);
		breast.mirror = true;
		setRotation(breast, 0.3490659F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		tail3.render(f5);
		tail4.render(f5);
		tail4.render(f5);
		tail4.render(f5);
		headfin2.render(f5);
		headfin3.render(f5);
		tail1fin.render(f5);
		tail2fin.render(f5);
		tail3fin.render(f5);
		trident.render(f5);
		breast.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}


	protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
	{
		// move child rotation point to be relative to parent
		parChild.rotationPointX -= parParent.rotationPointX;
		parChild.rotationPointY -= parParent.rotationPointY;
		parChild.rotationPointZ -= parParent.rotationPointZ;
		// make rotations relative to parent
		parChild.rotateAngleX -= parParent.rotateAngleX;
		parChild.rotateAngleY -= parParent.rotateAngleY;
		parChild.rotateAngleZ -= parParent.rotateAngleZ;
		// create relationship
		parParent.addChild(parChild);
	}
}

