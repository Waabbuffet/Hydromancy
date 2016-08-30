package com.waabbuffet.hydromancy.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMermaid extends ModelBase
{
	//fields
	int AnimationChangeCD = 0;
	
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
	
	
	ModelRenderer Shead;
	ModelRenderer Sbody;
	ModelRenderer Srightarm;
	ModelRenderer Sleftarm;
	ModelRenderer Stail1;
	ModelRenderer Stail2;
	ModelRenderer Stail3;
	ModelRenderer Stail4;
	ModelRenderer Stail5;
	ModelRenderer Stail6;
	ModelRenderer Sheadfin2;
	ModelRenderer Sheadfin3;
	ModelRenderer Stail1fin;
	ModelRenderer Stail2fin;
	ModelRenderer Stail3fin;
	ModelRenderer Sbreast;


	protected double distanceMovedTotal = 0.0D;

	// don't make this too large or animations will be skipped

	protected static final double CYCLES_PER_BLOCK = 3.0D; 
	protected int cycleIndex = 0;
	protected float[][] undulationCycle = new float[][]
			{//tail 1, 2 , 3, 4, 5, 6, tailfin 1, 2, 3

			{60F, 56F, 56F, 70F  , 70F  , 70F  , 55F , 59F , 72F}, //Base 64F, 60F , 60F , 80F , 80F , 80F , 58F , 62F , 82
			{61F, 58F, 58F, 75F  , 75F  , 75F  , 56F , 60F , 75F},
			{64F, 60F, 60F, 80F  , 80F  , 80F  , 58F , 62F , 82F},
			{67F, 63F, 63F, 90F  , 90F  , 90F  , 59F , 64F , 90F}, // might need a smoother transition
			{69F, 66F, 66F, 100F , 100F , 100F , 60F , 68F , 102F},
			};

	public ModelMermaid()
	{
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 3F, -3F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(128, 128);
		body.mirror = true;
		setRotation(body, 1.239184F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -1.5F, 4, 12, 4);
		rightarm.setRotationPoint(-5F, 2F, 2F);
		rightarm.setTextureSize(128, 128);
		rightarm.mirror = true;
		setRotation(rightarm, 1.117011F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -1.5F, 4, 12, 4);
		leftarm.setRotationPoint(5F, 2F, 2F);
		leftarm.setTextureSize(128, 128);
		leftarm.mirror = true;
		setRotation(leftarm, 1.117011F, 0F, 0F);
		tail1 = new ModelRenderer(this, 0, 33);
		tail1.addBox(-4F, 0F, 1F, 8, 8, 4);
		tail1.setRotationPoint(0F, 6F, 7F);
		tail1.setTextureSize(128, 128);
		tail1.mirror = true;
		setRotation(tail1, 1.204277F, 0F, 0F);
		tail2 = new ModelRenderer(this, 0, 45);
		tail2.addBox(-3.5F, 0F, -1.5F, 7, 6, 3);
		tail2.setRotationPoint(0F, 6F, 14.5F);
		tail2.setTextureSize(128, 128);
		tail2.mirror = true;
		setRotation(tail2, 1.134464F, -0.0297429F, 0F);
		tail3 = new ModelRenderer(this, 0, 54);
		tail3.addBox(-3F, 0F, -2F, 6, 6, 2);
		tail3.setRotationPoint(0F, 7F, 18.96667F);
		tail3.setTextureSize(128, 128);
		tail3.mirror = true;
		setRotation(tail3, 1.151917F, 0F, 0F);
		tail4 = new ModelRenderer(this, 0, 16);
		tail4.addBox(-2F, 0F, -0.8F, 4, 8, 1);
		tail4.setRotationPoint(-1F, 10F, 22.96667F);
		tail4.setTextureSize(128, 128);
		tail4.mirror = true;
		setRotation(tail4, 1.570796F, -0.3490659F, 0F);
		tail5 = new ModelRenderer(this, 0, 16);
		tail5.addBox(-2F, 0F, -0.8F, 4, 8, 1);
		tail5.setRotationPoint(1F, 10F, 22.96667F);
		tail5.setTextureSize(128, 128);
		tail5.mirror = true;
		setRotation(tail5, 1.570796F, 0.3490659F, 0F);
		tail6 = new ModelRenderer(this, 0, 16);
		tail6.addBox(-2F, 0F, -0.8F, 4, 8, 1);
		tail6.setRotationPoint(0F, 10F, 22.96667F);
		tail6.setTextureSize(128, 128);
		tail6.mirror = true;
		setRotation(tail6, 1.570796F, 0F, 0F);
		headfin2 = new ModelRenderer(this, 32, 0);
		headfin2.addBox(3.5F, -4.5F, -1.5F, 1, 3, 4);
		headfin2.setRotationPoint(0F, 2F, -3F);
		headfin2.setTextureSize(128, 128);
		headfin2.mirror = true;
		setRotation(headfin2, 0F, 0.296706F, 0F);
		headfin3 = new ModelRenderer(this, 32, 0);
		headfin3.addBox(-4.5F, -4.5F, -1.5F, 1, 3, 4);
		headfin3.setRotationPoint(0F, 2F, -3F);
		headfin3.setTextureSize(128, 128);
		headfin3.mirror = true;
		setRotation(headfin3, 0F, -0.296706F, 0F);
		tail1fin = new ModelRenderer(this, 24, 33);
		tail1fin.addBox(-0.5F, 0F, 0F, 1, 8, 4);
		tail1fin.setRotationPoint(0F, 5F, 11F);
		tail1fin.setTextureSize(128, 128);
		tail1fin.mirror = true;
		setRotation(tail1fin, 1.047198F, 0F, 0F);
		tail2fin = new ModelRenderer(this, 20, 45);
		tail2fin.addBox(-0.5F, 1F, 1F, 1, 6, 3);
		tail2fin.setRotationPoint(0F, 9F, 16.5F);
		tail2fin.setTextureSize(128, 128);
		tail2fin.mirror = true;
		setRotation(tail2fin, 1.134464F, -0.0074467F, 0F);
		tail3fin = new ModelRenderer(this, 16, 54);
		tail3fin.addBox(-0.5F, 0F, -1F, 1, 7, 2);
		tail3fin.setRotationPoint(0F, 10F, 22.96667F);
		tail3fin.setTextureSize(128, 128);
		tail3fin.mirror = true;
		setRotation(tail3fin, 1.605703F, 0F, 0F);
		breast = new ModelRenderer(this, 16, 16);
		breast.addBox(-4F, 0F, -2F, 8, 4, 4);
		breast.setRotationPoint(0F, 2F, 0F);
		breast.setTextureSize(128, 128);
		breast.mirror = true;
		setRotation(breast, 1.519676F, 0F, 0F);


		Shead = new ModelRenderer(this, 0, 0);
		Shead.addBox(-4F, -8F, -4F, 8, 8, 8);
		Shead.setRotationPoint(0F, 0F, 0F);
		Shead.setTextureSize(128, 128);
		Shead.mirror = true;
		setRotation(Shead, 0F, 0F, 0F);

		Sbody = new ModelRenderer(this, 16, 16);
		Sbody.addBox(-4F, 0F, -2F, 8, 12, 4);
		Sbody.setRotationPoint(0F, 0F, 0F);
		Sbody.setTextureSize(128, 128);
		Sbody.mirror = true;
		setRotation(Sbody, 0F, 0F, 0F);
	
		Srightarm = new ModelRenderer(this, 40, 16);
		Srightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		Srightarm.setRotationPoint(-5F, 2F, 0F);
		Srightarm.setTextureSize(128, 128);
		Srightarm.mirror = true;
		setRotation(Srightarm, -0.296706F, 0F, 0F);

		Sleftarm = new ModelRenderer(this, 40, 16);
		Sleftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		Sleftarm.setRotationPoint(5F, 2F, 0F);
		Sleftarm.setTextureSize(128, 128);
		Sleftarm.mirror = true;
		setRotation(Sleftarm, 0F, 0F, 0F);

		Stail1 = new ModelRenderer(this, 0, 33);
		Stail1.addBox(-4F, 0F, -2F, 8, 8, 4);
		Stail1.setRotationPoint(0F, 11F, 0F);
		Stail1.setTextureSize(128, 128);
		Stail1.mirror = true;
		setRotation(Stail1, 0.2617994F, 0F, 0F);

		Stail2 = new ModelRenderer(this, 0, 45);
		Stail2.addBox(-3.5F, 0F, -1.5F, 7, 6, 3);
		Stail2.setRotationPoint(0F, 18F, 1.5F);
		Stail2.setTextureSize(128, 128);
		Stail2.mirror = true;
		setRotation(Stail2, 0.6981317F, 0F, 0F);

		Stail3 = new ModelRenderer(this, 0, 54);
		Stail3.addBox(-3F, 0F, -2F, 6, 6, 2);
		Stail3.setRotationPoint(0F, 21F, 3.966667F);
		Stail3.setTextureSize(128, 128);
		Stail3.mirror = true;
		setRotation(Stail3, 1.396263F, 0F, 0F);

		Stail4 = new ModelRenderer(this, 0, 16);
		Stail4.addBox(-2F, 0F, -0.5F, 4, 8, 1);
		Stail4.setRotationPoint(-1.466667F, 23F, 5.966667F);
		Stail4.setTextureSize(128, 128);
		Stail4.mirror = true;
		setRotation(Stail4, 1.570796F, -0.3490659F, 0F);

		Stail5 = new ModelRenderer(this, 0, 16);
		Stail5.addBox(-2F, 0F, -0.5F, 4, 8, 1);
		Stail5.setRotationPoint(1F, 23F, 5.966667F);
		Stail5.setTextureSize(128, 128);
		Stail5.mirror = true;
		setRotation(Stail5, 1.570796F, 0.3490659F, 0F);

		Stail6 = new ModelRenderer(this, 0, 16);
		Stail6.addBox(-2F, 0F, -0.5F, 4, 8, 1);
		Stail6.setRotationPoint(0F, 23F, 5.966667F);
		Stail6.setTextureSize(128, 128);
		Stail6.mirror = true;
		setRotation(Stail6, 1.570796F, 0F, 0F);

		Sheadfin2 = new ModelRenderer(this, 32, 0);
		Sheadfin2.addBox(3.5F, -4.5F, -2.5F, 1, 3, 4);
		Sheadfin2.setRotationPoint(0F, 0F, 0F);
		Sheadfin2.setTextureSize(128, 128);
		Sheadfin2.mirror = true;
		setRotation(Sheadfin2, 0F, 0.296706F, 0F);
		this.head.addChild(Sheadfin2);

		Sheadfin3 = new ModelRenderer(this, 32, 0);
		Sheadfin3.addBox(-4.5F, -4.5F, -2.5F, 1, 3, 4);
		Sheadfin3.setRotationPoint(0F, 0F, 0F);
		Sheadfin3.setTextureSize(128, 128);
		Sheadfin3.mirror = true;
		setRotation(Sheadfin3, 0F, -0.296706F, 0F);
		this.head.addChild(Sheadfin3);

		Stail1fin = new ModelRenderer(this, 24, 33);
		Stail1fin.addBox(-0.5F, 0F, -0.5F, 1, 8, 4);
		Stail1fin.setRotationPoint(0F, 11F, 0F);
		Stail1fin.setTextureSize(128, 128);
		Stail1fin.mirror = true;
		setRotation(Stail1fin, 0.2617994F, 0F, 0F);

		Stail2fin = new ModelRenderer(this, 20, 45);
		Stail2fin.addBox(-0.5F, 1F, 0.5F, 1, 6, 3);
		Stail2fin.setRotationPoint(0F, 19F, 1.5F);
		Stail2fin.setTextureSize(128, 128);
		Stail2fin.mirror = true;
		setRotation(Stail2fin, 0.6981317F, -0.0074467F, 0F);

		Stail3fin = new ModelRenderer(this, 16, 54);
		Stail3fin.addBox(-0.5F, 0F, -1.5F, 1, 7, 2);
		Stail3fin.setRotationPoint(0F, 21F, 4.966667F);
		Stail3fin.setTextureSize(128, 128);
		Stail3fin.mirror = true;
		setRotation(Stail3fin, 1.396263F, 0F, 0F);

		trident = new ModelRenderer(this, 56, 21);
		trident.addBox(-0.5F, -15F, -2F, 1, 16, 16);
		trident.setRotationPoint(-6F, 10F, -3F);
		trident.setTextureSize(128, 128);
		trident.mirror = true;
		setRotation(trident, 0F, 3.141593F, 0F);
		
		Sbreast = new ModelRenderer(this, 16, 16);
		Sbreast.addBox(-4F, 0F, -2F, 8, 4, 4);
		Sbreast.setRotationPoint(0F, 1F, 0F);
		Sbreast.setTextureSize(128, 128);
		Sbreast.mirror = true;
		setRotation(Sbreast, -0.3490659F, 0F, 0F);
		 
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
		tail5.render(f5);
		tail6.render(f5);
		tail1fin.render(f5);
		tail2fin.render(f5);
		tail3fin.render(f5);
		trident.render(f5);
		breast.render(f5);
		
		Shead.render(f5);
		Sbody.render(f5);
		Srightarm.render(f5);
		Sleftarm.render(f5);
		Stail1.render(f5);
		Stail2.render(f5);
		Stail3.render(f5);
		Stail4.render(f5);
		Stail5.render(f5);
		Stail6.render(f5);
		Stail1fin.render(f5);
		Stail2fin.render(f5);
		Stail3fin.render(f5);
		//	trident.render(f5);
		Sbreast.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	//(float parTime, float parSwingSuppress, float par3,  float parHeadAngleY, float parHeadAngleX, float par6, Entity parEntity)
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{

		this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
		
		this.Shead.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.Shead.rotateAngleX = f4 / (180F / (float)Math.PI);
		
		
		
		if(entity.isInWater())
		{
			this.setSwimmingAnimation();
			
			updateDistanceMovedTotal(entity);
			cycleIndex = (int) ((getDistanceMovedTotal(entity)*1) %undulationCycle.length);
	
			tail1.rotateAngleX = degToRad(undulationCycle[cycleIndex][0]) ;
			tail2.rotateAngleX = degToRad(undulationCycle[cycleIndex][1]) ;
			tail3.rotateAngleX = degToRad(undulationCycle[cycleIndex][2]) ;
			tail4.rotateAngleX = degToRad(undulationCycle[cycleIndex][3]) ;
			tail5.rotateAngleX = degToRad(undulationCycle[cycleIndex][4]) ;
			tail6.rotateAngleX = degToRad(undulationCycle[cycleIndex][5]) ;
			tail1fin.rotateAngleX = degToRad(undulationCycle[cycleIndex][6]) ;
			tail2fin.rotateAngleX = degToRad(undulationCycle[cycleIndex][7]) ;  
			tail3fin.rotateAngleX = degToRad(undulationCycle[cycleIndex][8]) ;  
		}else
		{
			this.setWalkingAnimation();
			
		}

		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	protected void setWalkingAnimation()
	{
		this.head.isHidden = true;
		this.body.isHidden = true;
		this.rightarm.isHidden = true;
		this.leftarm.isHidden = true;
		this.tail1.isHidden = true;
		this.tail2.isHidden = true;
		this.tail3.isHidden = true;
		this.tail4.isHidden = true;
		this.tail5.isHidden = true;
		this.tail6.isHidden = true;
		this.headfin2.isHidden = true;
		this.headfin3.isHidden = true;
		this.tail1fin.isHidden = true;
		this.tail2fin.isHidden = true;
		this.tail3fin.isHidden = true;
		this.breast.isHidden = true;
		this.trident.isHidden = false;
	
		
		this.Shead.isHidden = false;
		this.Sbody.isHidden = false;
		this.Srightarm.isHidden = false;
		this.Sleftarm.isHidden = false;
		this.Stail1.isHidden = false;
		this.Stail2.isHidden = false;
		this.Stail3.isHidden = false;
		this.Stail4.isHidden = false;
		this.Stail5.isHidden = false;
		this.Stail6.isHidden = false;
		this.Sheadfin2.isHidden = false;
		this.Sheadfin3.isHidden = false;
		this.Stail1fin.isHidden = false;
		this.Stail2fin.isHidden = false;
		this.Stail3fin.isHidden = false;
		this.Sbreast.isHidden = false;
		
	
		this.AnimationChangeCD = 60;
	}
	
	
	
	protected void setSwimmingAnimation()
	{
		this.Shead.isHidden = true;
		this.Sbody.isHidden = true;
		this.Srightarm.isHidden = true;
		this.Sleftarm.isHidden = true;
		this.Stail1.isHidden = true;
		this.Stail2.isHidden = true;
		this.Stail3.isHidden = true;
		this.Stail4.isHidden = true;
		this.Stail5.isHidden = true;
		this.Stail6.isHidden = true;
		this.Sheadfin2.isHidden = true;
		this.Sheadfin3.isHidden = true;
		this.Stail1fin.isHidden = true;
		this.Stail2fin.isHidden = true;
		this.Stail3fin.isHidden = true;
		this.Sbreast.isHidden = true;
		
		
		this.head.isHidden = false;
		this.body.isHidden = false;
		this.rightarm.isHidden = false;
		this.leftarm.isHidden = false;
		this.tail1.isHidden = false;
		this.tail2.isHidden = false;
		this.tail3.isHidden = false;
		this.tail4.isHidden = false;
		this.tail5.isHidden = false;
		this.tail6.isHidden = false;
		this.headfin2.isHidden = false;
		this.headfin3.isHidden = false;
		this.tail1fin.isHidden = false;
		this.tail2fin.isHidden = false;
		this.tail3fin.isHidden = false;
		this.breast.isHidden = false;
		this.trident.isHidden = true;
		
		this.AnimationChangeCD = 60;
		
		
	}
	

	protected void updateDistanceMovedTotal(Entity parEntity) 
	{
		distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, parEntity.prevPosZ);
	}

	protected double getDistanceMovedTotal(Entity parEntity) 
	{
		return (distanceMovedTotal);
	}

	protected float degToRad(float degrees)
	{
		return degrees * (float)Math.PI / 180 ;
	}
}

