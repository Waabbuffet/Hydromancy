package com.waabbuffet.hydromancy.client.gui.lexicon.util;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslationTable;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.util.IllegalException;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.Mod.Instance;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import scala.Console;

public class TranslationTableResearch extends GuiButton{
	ResourceLocation res = new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table_Research.png");
	ResourceLocation res1 = new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table_Research_3.png");
	public String unlocalizedName,description;
	public boolean hovered,rendered;
	private boolean render = true;
	private boolean initialized;
	private String shape;
	TranslationTableResearch dependency;
	public int cost,posmodifier,u,v,defu,defv,guiareax,guiareay,guiareawidth,guiareaheight,defwidth,defheight;
	private int dmy;
	private float[] controlpoints;
	private float[] halfpoints;
	int[] conColor = {0,0,0,255};
	private FloatBuffer fb;
	private ItemStack renderItem;
	private RenderItem itemRenderer = new RenderItem();
	public static enum researchStates {
		LOCKED,
		IN_PROGRESS,
		AVAILABLE,
		RESEARCHED
	}
	public researchStates researchState;
	public TranslationTableResearch(int ID, int x, int y, int guiareax, int guiareay, int guiareawidth, int guiareaheight){
		super(ID, x, y, 26, 26, "");
		researchState = researchStates.LOCKED;
		this.guiareax = guiareax;
		this.guiareay = guiareay;
		this.guiareawidth = guiareawidth;
		this.guiareaheight = guiareaheight;
	}
	
	/**
	 * Adds research. Args.: dependency (null for independend branch), uniqueName, unlocalizedDescription, pageCost, item, shape(root, square, circle), rgba color (null if branch is independent branch).
	 * </br>
	 * Dependency and dependent research cannot be apart more than width/height of research table draggable area, it can be changed, but don't do that, it is not necessary. If it is necessary, modify this file and optimize curve rendering!
	 */
	public void addResearch(TranslationTableResearch dependency, String uniqueName, String unlocalizedDescription, int pageCost, ItemStack item, String shape, int[] color){	
		this.unlocalizedName = uniqueName;
		this.description = unlocalizedDescription;
		this.dependency = dependency;
		this.cost = pageCost;
		this.shape = shape;
		
		if(color != null){
			this.conColor = color;
		}
		this.renderItem = item;
		
		switch(shape){	
			case "square":
				this.u = 27;
				this.v = 173;
				this.width = 22;
				this.height = 22;
				break;
			case "circle":
				this.u = 50;
				this.v = 173;
				this.width = 22;
				this.height = 22;
				break;
			case "root":
			default:
				this.u = 0;
				this.v = 173;
				this.width = 26; //those values are going to change
				this.height = 26;
				this.posmodifier = 1;
				break;
		}
		this.defu = u;
		this.defv = v;
		this.defwidth = width; //those are actually used
		this.defheight = height;
		//this.hex = Integer.parseInt(Color.getColor(String.format("%02x%02x%02x", conColor[0], conColor[1], conColor[2])));//, 16);
	}

	public void updateXY(int x, int y){
		this.xPosition += x;
		if(initialized == true)
		dmy -= y;
		this.yPosition -= y;
		
		 
	}
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my) {}
	//do not override!
	public void drawActualButton(Minecraft minecraft, int mx, int my) throws IllegalException {
		//System.out.println();
		if((this.unlocalizedName != null) && (!this.unlocalizedName.isEmpty()) && ((this.description != null) && (!this.description.isEmpty()))){
			if ((this.width != 0) && (this.height != 0)){	
				hovered = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height;
				if (xPosition >= guiareax-defwidth && yPosition >= guiareay-defheight && xPosition < guiareax+guiareawidth && yPosition < guiareay+guiareaheight)
					rendered = true;
				else
					rendered = false;
				if (dependency != null){
					if(dependency.researchState == researchStates.RESEARCHED){
						this.researchState = researchStates.AVAILABLE;
					}
					if ((xPosition < this.guiareax && dependency.xPosition > dependency.guiareax+dependency.guiareawidth) || (dependency.xPosition < dependency.guiareax && xPosition > guiareax+guiareawidth) || (yPosition < guiareay && dependency.yPosition > dependency.guiareay+dependency.guiareaheight) || (dependency.yPosition < dependency.guiareay && yPosition > guiareay+guiareaheight)) {
						throw new IllegalException("You can't place researches so far apart");
					}
					/* Notes to everyone working around this class:
					 * Do not mess with curves unless you know exactly what are you doing. Or at least do not push it.
					 * Notes about numbers:
					 * yPos rendering is much harder to deal with, with that being said:
					 * 
					 * halfpoint "curved line part" height is 16px (what a magic number).
					 * Previous line is not apparently right cause idk, may cause issues, let's wait for further testing but keep the number there for future use.
					 */
					//GL11.glEnable(GL11.GL_COLOR);
					//this.drawRect(50, 50, 200, 200, 0xff000f00);
					//(xPosition > guiareax-defwidth && yPosition > guiareay-defheight && xPosition <= guiareax+guiareawidth && yPosition <= guiareay+guiareaheight) || (dependency.xPosition > guiareax-dependency.defwidth && dependency.yPosition > guiareay-dependency.defheight && dependency.xPosition <= guiareax+guiareawidth && dependency.yPosition <= guiareay+guiareaheight)
					if(rendered == true || dependency.rendered == true){			
						if (this.xPosition != dependency.xPosition && this.yPosition != dependency.yPosition){
							/*
							 	 NR
							 	 |
							 	  --
							 	 	|
							 	    D
							 	    
							 	finished
							 */
							if (xPosition < dependency.xPosition && yPosition < dependency.yPosition)
							{
								controlpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + defheight - posmodifier, this.zLevel-1,
															 this.xPosition + this.defwidth/2, dependency.yPosition + dependency.posmodifier, this.zLevel-1,										 						 
															 dependency.xPosition + dependency.defwidth/2, this.yPosition + this.defheight - posmodifier, this.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.posmodifier, dependency.zLevel-1};														 
							
								if(this.rendered == true && dependency.rendered == false){
									initialized = true;	
									if (dependency.yPosition+dependency.defheight >= dependency.guiareay && dependency.yPosition < dependency.guiareay+dependency.guiareaheight && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2+5) >= guiareay)
									{
										if (dependency.xPosition+dependency.height >= guiareax+guiareaheight){
											halfpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + defheight - posmodifier, this.zLevel-1,
																	  this.xPosition + this.defwidth/2, dependency.yPosition + dependency.posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, this.yPosition + defheight - posmodifier, this.zLevel-1,
																	  guiareax+guiareawidth, dependency.yPosition - (dependency.yPosition - yPosition)/2 + dependency.posmodifier, dependency.zLevel-1};	
										}
									}
									else if (dependency.xPosition+dependency.width >= dependency.guiareax && dependency.xPosition < dependency.guiareax+dependency.guiareawidth){
										if (dependency.yPosition >= guiareay+guiareaheight && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2+11) >= guiareay+guiareaheight && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2-8) < guiareay+guiareaheight){
											halfpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + height - posmodifier, this.zLevel-1,
																	  this.xPosition + this.defwidth/2, dependency.yPosition + dependency.posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, this.yPosition + height - posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, guiareay + guiareaheight + dmy, dependency.zLevel-1};
											render = true;
										}
										else if (dependency.yPosition >= dependency.guiareay+guiareaheight && yPosition+height >= guiareay+guiareaheight && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2-8) >= guiareay+guiareaheight){
											this.drawRect(xPosition+defwidth/2-0.5, yPosition+height-posmodifier, xPosition+defwidth/2+0.5, guiareay+guiareaheight, conColor);
											render = false;
										}
									}
								}
								else if(this.rendered == false && dependency.rendered == true){
									initialized = true;
									if (yPosition >= guiareay-defheight && yPosition < guiareay+guiareaheight){
										if (xPosition < guiareax){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.posmodifier, dependency.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, this.yPosition + defheight - posmodifier, this.zLevel-1,
																	  xPosition + defwidth/2, dependency.yPosition + dependency.posmodifier, this.zLevel-1,
																	  guiareax, yPosition + defheight + (dependency.yPosition - yPosition)/2, this.zLevel-1};
										}
									}
									else if (this.xPosition >= guiareax && this.xPosition < guiareax+guiareawidth){
										if (yPosition < guiareay && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2-11) <= guiareay && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2+5) >= guiareay){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.posmodifier, dependency.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, this.yPosition + height - posmodifier, this.zLevel-1,
																	  this.xPosition + this.defwidth/2, dependency.yPosition + dependency.posmodifier, this.zLevel-1,
																	  this.xPosition + defwidth/2, guiareay + dmy, this.zLevel-1};
											render = true;
										}
										else if (yPosition < guiareay && dependency.yPosition > guiareay && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2+5) < guiareay){
											this.drawRect(dependency.xPosition+dependency.defwidth/2-0.5, dependency.yPosition+dependency.posmodifier, dependency.xPosition+dependency.defwidth/2+0.5, guiareay-1, conColor);
											render = false;
										}
									}
								}
								else if (dependency.rendered == false && rendered == false)
								{
									render = false;
								}
								else {
									dmy = 0;
									render = true;
								}
							}
							/*
							 	 	NR
							 	    |
							 	  --
						 	 	 |
						 	     D
						 	     finished
							 */
							else if (xPosition > dependency.xPosition && yPosition < dependency.yPosition)
							{	
								controlpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + defheight - posmodifier, this.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2 + 14, dependency.yPosition + dependency.posmodifier, this.zLevel-1,
															 this.xPosition + this.defwidth/2 - 14, this.yPosition + defheight - posmodifier, this.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.posmodifier, dependency.zLevel-1};
								if(this.rendered == true && dependency.rendered == false){
									initialized = true;	
									if (dependency.yPosition+dependency.defheight >= dependency.guiareay && dependency.yPosition < dependency.guiareay+dependency.guiareaheight && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2+5) >= guiareay)
									{
										if (dependency.xPosition < dependency.guiareax){
											halfpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + defheight - posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2 + 14, dependency.yPosition + dependency.posmodifier, this.zLevel-1,				  
																	  this.xPosition + this.defwidth/2 - 14, this.yPosition + defheight - posmodifier, this.zLevel-1,
																	  guiareax, dependency.yPosition - (dependency.yPosition - yPosition)/2 + dependency.posmodifier, dependency.zLevel-1};
										}
									}
									else if (dependency.xPosition+dependency.width >= dependency.guiareax && dependency.xPosition < dependency.guiareax+dependency.guiareawidth){
										if (dependency.yPosition >= guiareay+guiareaheight && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2+11) >= guiareay+guiareaheight && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2-8) < guiareay+guiareaheight){
											halfpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + height - posmodifier, this.zLevel-1,
													  				  dependency.xPosition + dependency.defwidth/2 + 14, dependency.yPosition + dependency.posmodifier, this.zLevel-1,
																	  this.xPosition + this.defwidth/2 - 14, this.yPosition + height - posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, guiareay + guiareaheight + dmy, dependency.zLevel-1};
											render = true;
										}
										else if (dependency.yPosition >= dependency.guiareay+guiareaheight && yPosition+height >= guiareay+guiareaheight && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2-8) >= guiareay+guiareaheight){
											this.drawRect(xPosition+defwidth/2-0.5, yPosition+height-posmodifier, xPosition+defwidth/2+0.5, guiareay+guiareaheight, conColor);
											render = false;
										}
									}
								}
								else if(this.rendered == false && dependency.rendered == true){
									initialized = true;
									//System.out.println(this.rendered + " " + dependency.rendered);
									if (yPosition >= guiareay-defheight && yPosition < guiareay+guiareaheight){
										if (xPosition+defwidth >= guiareax+guiareawidth){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.posmodifier, dependency.zLevel-1,
																	  this.xPosition + this.defwidth/2 - 14, this.yPosition + defheight - posmodifier, this.zLevel-1,
																	  guiareax + guiareawidth + 14, dependency.yPosition + dependency.posmodifier, this.zLevel-1,
																	  guiareax + guiareawidth, yPosition + defheight + (dependency.yPosition - yPosition)/2, this.zLevel-1};
										}
									}
									else if (this.xPosition >= guiareax && this.xPosition < guiareax+guiareawidth){
										if (yPosition < guiareay && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2-11) < guiareay && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2+5) >= guiareay){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.posmodifier, dependency.zLevel-1,
																	  this.xPosition + this.defwidth/2 - 14, this.yPosition + height - posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2 + 14, dependency.yPosition + dependency.posmodifier, this.zLevel-1,
																	  this.xPosition + defwidth/2, guiareay + dmy, this.zLevel-1};
											render = true;
											System.out.println("ntbmd");
										}
										else if (yPosition < guiareay && dependency.yPosition >= guiareay && yPosition+height+((dependency.yPosition+dependency.posmodifier-(yPosition+height-posmodifier))/2+5) < guiareay){
											this.drawRect(dependency.xPosition+dependency.defwidth/2-0.5, dependency.yPosition+dependency.posmodifier, dependency.xPosition+dependency.defwidth/2+0.5, guiareay-1, conColor);
											render = false;
											System.out.println("rýl šit");
										}
									}
								}
								else if (dependency.rendered == false && rendered == false)
								{
									render = false;
								}
								else {
									dmy = 0;
									render = true;
								}
							}
							/*
							 		D
							 		|
							 	  --
							 	 |
							 	 NR
							 	 
							 	 finished
							 */
							else if (xPosition < dependency.xPosition && yPosition > dependency.yPosition)
							{
								controlpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, dependency.zLevel-1,
															 this.xPosition + this.defwidth/2 + 19, this.yPosition + posmodifier, this.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2 - 19, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,
															 this.xPosition + defwidth/2, this.yPosition + posmodifier, this.zLevel-1};
								if(this.rendered == true && dependency.rendered == false){
									initialized = true;
									if (dependency.yPosition >= dependency.guiareay-dependency.defheight && dependency.yPosition < dependency.guiareay+dependency.guiareaheight+dependency.defheight)
									{
										if (dependency.xPosition >= dependency.guiareax+dependency.guiareawidth){
											halfpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2 - 19, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,				  
																	  this.xPosition + this.defwidth/2 + 19, this.yPosition + posmodifier, this.zLevel-1,
																	  dependency.guiareax + guiareawidth, dependency.yPosition + (yPosition - dependency.yPosition)/2 - dependency.posmodifier, dependency.zLevel-1};
											
										}
									}
									else if (dependency.xPosition >= dependency.guiareax && dependency.xPosition < dependency.guiareax+dependency.guiareawidth){
										if (dependency.yPosition < dependency.guiareay && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2+8) > guiareay/* && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2-8) <= guiareay*/){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, guiareay + dmy - 2, dependency.zLevel-1,
																	  this.xPosition + this.defwidth/2 + 19, this.yPosition + posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2 - 19, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,
																	  this.xPosition + defwidth/2, this.yPosition + posmodifier, this.zLevel-1};
											render = true;
										}
										else if (dependency.yPosition < dependency.guiareay && yPosition > guiareay && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2+8) <= guiareay){
											this.drawRect(xPosition+defwidth/2-0.5, yPosition+posmodifier, xPosition+defwidth/2+0.5, guiareay-1, conColor);
											render = false;
										}
									}
								}
								else if(this.rendered == false && dependency.rendered == true){
									initialized = true;
									if (yPosition >= guiareay-defheight && yPosition < guiareay+guiareaheight){
										if (xPosition < guiareax){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, dependency.zLevel-1,
																	  this.xPosition + this.defwidth/2 + 19, this.yPosition + posmodifier, this.zLevel-1,
																	  guiareax - 19, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,
																	  guiareax, yPosition - (yPosition - dependency.yPosition)/2, this.zLevel-1};
										}
									}
									else if (this.xPosition >= guiareax && this.xPosition < guiareax+guiareawidth){
										if (this.yPosition >= dependency.guiareay+guiareaheight && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2+8) >= guiareay + guiareaheight && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2-8) < guiareay+guiareaheight){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.height - dependency.posmodifier, dependency.zLevel-1,
																	  this.xPosition + this.defwidth/2 + 19, this.yPosition + posmodifier, this.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2 - 19, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,
																	  this.xPosition + defwidth/2, guiareay + guiareaheight + dmy, this.zLevel-1};
											render = true;
										}
										else if (yPosition >= guiareay+guiareaheight && dependency.yPosition+dependency.height >= guiareay+guiareaheight && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2-8) >= guiareay+guiareaheight){
											this.drawRect(dependency.xPosition+dependency.defwidth/2-0.5, dependency.yPosition + dependency.defheight - dependency.posmodifier, dependency.xPosition+dependency.defwidth/2+0.5, guiareay+guiareaheight+1, conColor);
											render = false;
										}
									}
								}
								else if (dependency.rendered == false && rendered == false)
								{
									render = false;
								}
								else {
									dmy = 0;
									render = true;
								}
							}
							/*
							 	 D
							 	 |
							 	  --
							 	 	|
							 	    NR
							 	   finished
							 */
							else if (xPosition > dependency.xPosition && yPosition > dependency.yPosition)
							{
								controlpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, dependency.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2, this.yPosition + posmodifier, this.zLevel-1,
															 this.xPosition + this.defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,
															 this.xPosition + defwidth/2, this.yPosition + posmodifier, this.zLevel};
							
								if(this.rendered == true && dependency.rendered == false){
									initialized = true;
									if (dependency.yPosition >= dependency.guiareay-dependency.defheight && dependency.yPosition < dependency.guiareay+dependency.guiareaheight+dependency.defheight)
									{
										if (dependency.xPosition < guiareax){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,				  
													  				  dependency.guiareax, dependency.yPosition + (yPosition - dependency.yPosition)/2 - dependency.posmodifier, dependency.zLevel-1,
													  				  this.xPosition + defwidth/2, this.yPosition + posmodifier, this.zLevel-1,
																	  this.xPosition + this.defwidth/2, this.yPosition + posmodifier, this.zLevel-1,};
											
										}
									}
									else if (dependency.xPosition >= dependency.guiareax && dependency.xPosition < dependency.guiareax+dependency.guiareawidth){
										if (dependency.yPosition < dependency.guiareay && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2+8) > guiareay/* && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2-8) <= guiareay*/){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, guiareay + dmy - 2, dependency.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, yPosition + posmodifier, this.zLevel-1,
																	  this.xPosition + this.defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,
																	  this.xPosition + defwidth/2, this.yPosition + posmodifier, this.zLevel-1};
											render = true;
										}
										else if (yPosition >= guiareay && dependency.yPosition < dependency.guiareay && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2+8) <= guiareay){
											this.drawRect(xPosition+defwidth/2-0.5, yPosition+posmodifier+1, xPosition+defwidth/2+0.5, guiareay-1, conColor);
											render = false;
										}
									}
								}
								else if(this.rendered == false && dependency.rendered == true){
									initialized = true;
									//System.out.println(this.rendered + " " + dependency.rendered);
									if (yPosition >= guiareay-defheight && yPosition < guiareay+guiareaheight){
										if (xPosition >= dependency.guiareax+dependency.guiareawidth){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, dependency.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, this.yPosition + posmodifier, this.zLevel-1,
																	  this.xPosition + defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,
																	  guiareax+guiareawidth, yPosition - (yPosition - dependency.yPosition)/2, this.zLevel-1};
										}
									}
									else if (this.xPosition >= guiareax && this.xPosition < guiareax+guiareawidth){
										if (this.yPosition >= dependency.guiareay+guiareaheight && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2+8) >= guiareay + guiareaheight && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2-8) < guiareay+guiareaheight){
											halfpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.height - dependency.posmodifier, dependency.zLevel-1,
																	  dependency.xPosition + dependency.defwidth/2, yPosition + posmodifier, this.zLevel-1,
																	  this.xPosition + this.defwidth/2, dependency.yPosition + dependency.defheight - dependency.posmodifier, this.zLevel-1,
																	  this.xPosition + defwidth/2, guiareay + guiareaheight + dmy, this.zLevel-1};
											render = true;
										}
										else if (dependency.yPosition+dependency.height < guiareay+guiareaheight && yPosition+height >= guiareay+guiareaheight && dependency.yPosition+dependency.height+((yPosition+posmodifier-(dependency.yPosition+dependency.height-dependency.posmodifier))/2-8) >= guiareay+guiareaheight){
											this.drawRect(dependency.xPosition+dependency.defwidth/2-0.5, dependency.yPosition + dependency.defheight - dependency.posmodifier - 1, dependency.xPosition+dependency.defwidth/2+0.5, guiareay+guiareaheight+1, conColor);
											render = false;
											System.out.println(dependency.yPosition+dependency.defheight);
										}
									}
								}
								else if (dependency.rendered == false && rendered == false)
								{
									render = false;
								}
								else {
									dmy = 0;
									render = true;
								}
							}
							if(render == true){
								if(((rendered == true && dependency.rendered == false) || (rendered == false && dependency.rendered == true)) && halfpoints != null && halfpoints.length != 0){
									fb = BufferUtils.createFloatBuffer(halfpoints.length*3);
									for (int i = 0; i < 12; i++)
									{
										fb.put(halfpoints[i]);
									}
									fb.rewind();
								} else {
									fb = BufferUtils.createFloatBuffer(controlpoints.length*3);
									for (int i = 0; i < 12; i++)
									{
										fb.put(controlpoints[i]);
									}
									fb.rewind();
								}
							
								//System.out.println("it's drawing");
								GL11.glPushMatrix();
									GL11.glDisable(GL11.GL_TEXTURE_2D);			
									GL11.glDisable(GL11.GL_LIGHTING);
									GL11.glMap1f(GL11.GL_MAP1_VERTEX_3, 0.0f, 1.0f, 3, 4, fb);
									GL11.glColor4d(conColor[0], conColor[1], conColor[2], conColor[3]);
									GL11.glEnable(GL11.GL_MAP1_VERTEX_3);
							        GL11.glBegin(GL11.GL_LINE_STRIP);      
							        for (int i=0; i<=30; i++)
							            GL11.glEvalCoord1f((float)(i/30f));
							        GL11.glEnd();
									//GL11.glPointSize(5f);
							        GL11.glBegin(GL11.GL_POINTS);
							            GL11.glVertex3f(controlpoints[0],controlpoints[1],controlpoints[2]); //cause function glVertex3fv is apparently not in gl anymore
							            GL11.glVertex3f(controlpoints[3],controlpoints[4],controlpoints[5]);
							            GL11.glVertex3f(controlpoints[6],controlpoints[7],controlpoints[8]);
							            GL11.glVertex3f(controlpoints[9],controlpoints[10],controlpoints[11]);
									GL11.glEnd();

									//GL11.glEnable(GL11.GL_LIGHTING);
						        GL11.glPopMatrix(); 
							}
							GL11.glDisable(GL11.GL_MAP1_VERTEX_3);
							GL11.glEnable(GL11.GL_TEXTURE_2D);
							GL11.glColor4f(1,1,1,1);
						}
						else if (this.xPosition == dependency.xPosition && this.yPosition != dependency.yPosition){				
							this.drawRect(xPosition, yPosition+0.5, dependency.xPosition, dependency.yPosition-0.5, conColor); //horizontal
						}
						else {
							this.drawRect(xPosition+0.5, yPosition, dependency.xPosition-0.5, dependency.yPosition, conColor); //vertical
						}
					}					
				}
				if(xPosition > guiareax-defwidth && yPosition > guiareay-defheight && xPosition < guiareax+guiareawidth && yPosition < guiareay+guiareaheight){
					minecraft.getTextureManager().bindTexture(res);
					
					GL11.glEnable(GL11.GL_BLEND);
					//edges
					if (xPosition < guiareax-defwidth/2 && (!(xPosition > guiareax+guiareawidth-defwidth/2)) && (!(yPosition < guiareay-defheight/2)) && (!(yPosition > guiareay+guiareaheight-defheight/2))){
						this.drawTexturedModalRect(xPosition + width/2, yPosition, u + width/2, v, width/2, height);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition, u + width/2, v+52, width/2, height);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition, u + width/2, v+52, width/2, height);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition, u + width/2, v+26, width/2, height);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					else if (xPosition > guiareax+guiareawidth-defwidth/2 && (!(xPosition < guiareax-defwidth/2)) && (!(yPosition < guiareay-defheight/2)) && (!(yPosition > guiareay+guiareaheight-defheight/2))){
						this.drawTexturedModalRect(xPosition, yPosition, u, v, width/2, height);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+52, width/2, height);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+52, width/2, height);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+26, width/2, height);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					else if (yPosition < guiareay-defheight/2 && (!(xPosition < guiareax-defwidth/2)) && (!(xPosition > guiareax+guiareawidth-defwidth/2)) && (!(yPosition > guiareay+guiareaheight-defheight/2))){
						this.drawTexturedModalRect(xPosition, yPosition + height/2, u, v + height/2, width, height/2);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition + height/2, u, v+52+height/2, width, height/2);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition + height/2, u, v+52+height/2, width, height/2);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition + height/2, u, v+26+height/2, width, height/2);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					else if (yPosition > guiareay+guiareaheight-defheight/2 && (!(xPosition < guiareax-defwidth/2)) && (!(xPosition > guiareax+guiareawidth-defwidth/2)) && (!(yPosition < guiareay-defheight/2))){
						this.drawTexturedModalRect(xPosition, yPosition, u, v, width, height/2);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+52, width, height/2);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+52, width, height/2);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+26, width, height/2);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					//corners
					else if (xPosition < guiareax-defwidth/2 && yPosition < guiareay-defheight/2){
						this.drawTexturedModalRect(xPosition + width/2, yPosition + height/2, u + width/2, v + height/2, width/2, height/2);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition + height/2, u + width/2, v+52+height/2, width/2, height/2);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition + height/2, u + width/2, v+52+height/2, width/2, height/2);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition + height/2, u + width/2, v+26+height/2, width/2, height/2);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					else if (xPosition > guiareax+guiareawidth-defwidth/2 && yPosition > guiareay+guiareaheight-defheight/2){
						this.drawTexturedModalRect(xPosition, yPosition, u, v, width/2, height/2);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+52, width/2, height/2);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+52, width/2, height/2);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+26, width/2, height/2);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					else if (xPosition < guiareax-defwidth/2 && yPosition > guiareay+guiareaheight-defheight/2){
						this.drawTexturedModalRect(xPosition + width/2, yPosition, u + width/2, v, width/2, height/2);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition, u + width/2, v+52, width/2, height/2);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition, u + width/2, v+52, width/2, height/2);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition + width/2, yPosition, u + width/2, v+26, width/2, height/2);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					else if (xPosition > guiareax+guiareawidth-defwidth/2 && yPosition < guiareay-defheight/2){
						this.drawTexturedModalRect(xPosition, yPosition + height/2, u, v + height/2, width/2, height/2);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition + height/2, u, v+52+height/2, width/2, height/2);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition + height/2, u, v+52+height/2, width/2, height/2);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition + height/2, u, v+26+height/2, width/2, height/2);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					//normal
					else {
						this.drawTexturedModalRect(xPosition, yPosition, u, v, width, height);
						GL11.glEnable(GL11.GL_BLEND);
						switch(researchState)
						{
						case AVAILABLE:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+52, width, height);
							break;
						case IN_PROGRESS:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+52, width, height);
							break;
						case LOCKED:
							minecraft.getTextureManager().bindTexture(res);
							this.drawTexturedModalRect(xPosition, yPosition, u, v+26, width, height);
							break;
						case RESEARCHED:
							break;
						default:
							break;
						}
						GL11.glDisable(GL11.GL_BLEND);
					}
					
					//itemRenderer.renderItemIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, renderItem, xPosition+defwidth/2-8, yPosition+defheight/2-8);
					if(xPosition >= guiareax-20 && xPosition < guiareax+guiareawidth+5 && yPosition >= guiareay-20 && yPosition < guiareay+guiareaheight-5)
						drawItemStack(renderItem, xPosition+defwidth/2-8, yPosition+defheight/2-8, "");
					//Instead of dealing with glcolor I am using transparent texture
				}
			}
		}
	}
	private static void drawRect(double p_73734_0_, double p_73734_1_, double p_73734_2_, double p_73734_3_, int[] p_73734_4_)
    {
        double j1;

        if (p_73734_0_ < p_73734_2_)
        {
            j1 = p_73734_0_;
            p_73734_0_ = p_73734_2_;
            p_73734_2_ = j1;
        }

        if (p_73734_1_ < p_73734_3_)
        {
            j1 = p_73734_1_;
            p_73734_1_ = p_73734_3_;
            p_73734_3_ = j1;
        }

        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4d(p_73734_4_[0], p_73734_4_[1], p_73734_4_[2], p_73734_4_[3]);
        tessellator.startDrawingQuads();
        tessellator.addVertex((double)p_73734_0_, (double)p_73734_3_, 0.0D);
        tessellator.addVertex((double)p_73734_2_, (double)p_73734_3_, 0.0D);
        tessellator.addVertex((double)p_73734_2_, (double)p_73734_1_, 0.0D);
        tessellator.addVertex((double)p_73734_0_, (double)p_73734_1_, 0.0D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
	
	
	public void drawItemStack(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_)
	{
		Minecraft mc = Minecraft.getMinecraft();
		
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		itemRenderer.zLevel = 200.0F;
		FontRenderer font = null;
		if (p_146982_1_ != null) font = p_146982_1_.getItem().getFontRenderer(p_146982_1_);
		if (font == null) font = mc.fontRenderer;
		itemRenderer.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_);
		itemRenderer.renderItemOverlayIntoGUI(font, mc.getTextureManager(), p_146982_1_, p_146982_2_, p_146982_3_ , p_146982_4_);
		this.zLevel = 0.0F;
		itemRenderer.zLevel = 0.0F; 
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	
	}
}
