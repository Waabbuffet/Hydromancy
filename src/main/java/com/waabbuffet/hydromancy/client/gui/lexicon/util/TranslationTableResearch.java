package com.waabbuffet.hydromancy.client.gui.lexicon.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslationTable;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.util.Reference;

import cpw.mods.fml.common.Mod.Instance;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
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
	public String unlocalizedName,description;
	public boolean hovered;
	private String shape;
	TranslationTableResearch dependency;
	public int cost;
	int u,v,hex,guiareax,guiareay,guiareawidth,guiareaheight,defwidth,defheight;
	private float[] controlpoints;
	int[] conColor = {255,255,255,255};
	private FloatBuffer fb;
	private ItemStack renderItem;
	private RenderItem itemRenderer = new RenderItem();
	
	public enum researchState {
		LOCKED,
		IN_PROGRESS,
		AVAILABLE,
		RESEARCHED
	}
	
	public TranslationTableResearch(int ID, int x, int y, int guiareax, int guiareay, int guiareawidth, int guiareaheight){
		super(ID, x, y, 26, 26, "");
		this.guiareax = guiareax;
		this.guiareay = guiareay;
		this.guiareawidth = guiareawidth;
		this.guiareaheight = guiareaheight;
	}
	
	/**
	 * Adds research. Args.: dependency (null for independend branch), uniqueName, unlocalizedDescription, pageCost, item, shape(root, square, circle), rgba color (null if branch is independent branch)
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
				this.v = 167;
				this.width = 22;
				this.height = 22;
				break;
			case "circle":
				this.u = 50;
				this.v = 167;
				this.width = 22;
				this.height = 22;
				break;
			case "root":
			default:
				this.u = 0;
				this.v = 167;
				this.width = 26; //those values are going to change
				this.height = 26;
				break;
		}
		this.defwidth = width; //those are actually used
		this.defheight = height;
		this.hex = Integer.parseInt(String.format("%02x%02x%02x", conColor[0], conColor[1], conColor[2]), 16);
	}

	public void updateXY(int x, int y){
		this.xPosition = this.xPosition + x;
		this.yPosition = this.yPosition - y;
		if(xPosition < guiareax){
			this.width = this.width + x;
		}
		else if (yPosition < guiareay){
			this.height = this.height - y;
		}
		else if (xPosition > guiareax+guiareawidth){
			this.width = this.width - x;
		}
		else if (yPosition > guiareay+guiareaheight){
			this.height = this.height + y;
		}
		 
	}
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my) {}
	//do not override!
	public void drawActualButton(Minecraft minecraft, int mx, int my) {
		hovered = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height;
		
		if((this.unlocalizedName != null) && (!this.unlocalizedName.isEmpty()) && ((this.description != null) && (!this.description.isEmpty()))){
			if ((this.width != 0) && (this.height != 0)){		
				if (dependency != null){
					if((xPosition > guiareax-defwidth && yPosition > guiareay-defheight && xPosition < guiareax+guiareawidth && yPosition < guiareay+guiareaheight) || (dependency.xPosition > guiareax-dependency.defwidth && dependency.yPosition > guiareay-dependency.defheight && dependency.xPosition < guiareax+guiareawidth && dependency.yPosition < guiareay+guiareaheight)){
						if (this.xPosition != dependency.xPosition && this.yPosition != dependency.yPosition){
							if (xPosition < dependency.xPosition && yPosition < dependency.yPosition)
								controlpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + defheight/2, this.zLevel-1,
															 this.xPosition + this.defwidth/2 + 20, dependency.yPosition + dependency.defheight/2, this.zLevel-1,										 						 
															 dependency.xPosition + dependency.defwidth/2 - 20, this.yPosition + this.defheight/2, this.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight/2, dependency.zLevel-1};														 
							else if (xPosition > dependency.xPosition && yPosition < dependency.yPosition)
								controlpoints = new float[] {this.xPosition + defwidth/2, this.yPosition + defheight/2, this.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2 - 20, dependency.yPosition + dependency.defheight/2, this.zLevel-1,
															 this.xPosition + this.defwidth/2 + 20, this.yPosition + this.defheight/2, this.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight/2, dependency.zLevel-1};
							else if (xPosition < dependency.xPosition && yPosition > dependency.yPosition)
								controlpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight/2, dependency.zLevel-1,
															 this.xPosition + this.defwidth/2 + 20, this.yPosition + this.defheight/2, this.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2 - 20, dependency.yPosition + dependency.defheight/2, this.zLevel-1,
															 this.xPosition + defwidth/2, this.yPosition + defheight/2, this.zLevel-1};
							else if (xPosition > dependency.xPosition && yPosition > dependency.yPosition)
								controlpoints = new float[] {dependency.xPosition + dependency.defwidth/2, dependency.yPosition + dependency.defheight/2, dependency.zLevel-1,
															 dependency.xPosition + dependency.defwidth/2 + 20, this.yPosition + this.defheight/2, this.zLevel-1,
															 this.xPosition + this.defwidth/2 - 20, dependency.yPosition + dependency.defheight/2, this.zLevel-1,
															 this.xPosition + defwidth/2, this.yPosition + defheight/2, this.zLevel};
	
							fb = BufferUtils.createFloatBuffer(controlpoints.length*3);
							for (int i = 0; i < 12; i++)
						    {
								fb.put(controlpoints[i]);
						    }
							fb.rewind();
							
								GL11.glPushMatrix();
								GL11.glDisable(GL11.GL_TEXTURE_2D);			
								GL11.glDisable(GL11.GL_LIGHTING);
								GL11.glMap1f(GL11.GL_MAP1_VERTEX_3, 0.0f, 1.0f, 3, 4, fb);
								GL11.glColor4d(conColor[0], conColor[1], conColor[2], conColor[3]);
								GL11.glEnable(GL11.GL_MAP1_VERTEX_3);
						        GL11.glBegin(GL11.GL_LINE_STRIP);      
						        for (int i=3; i<=30; i++)
						            GL11.glEvalCoord1f((float)(i/30f));
						        GL11.glEnd();
								//GL11.glPointSize(5f);
						        /*GL11.glBegin(GL11.GL_POINTS);
						            GL11.glVertex3f(controlpoints[0],controlpoints[1],controlpoints[2]); //cause function glVertex3fv is apparently not in gl anymore
						            GL11.glVertex3f(controlpoints[3],controlpoints[4],controlpoints[5]);
						            GL11.glVertex3f(controlpoints[6],controlpoints[7],controlpoints[8]);
						            GL11.glVertex3f(controlpoints[9],controlpoints[10],controlpoints[11]);
								GL11.glEnd();*/
								GL11.glDisable(GL11.GL_MAP1_VERTEX_3);
								GL11.glEnable(GL11.GL_TEXTURE_2D);
								GL11.glColor4f(1,1,1,1);
								//GL11.glEnable(GL11.GL_LIGHTING);
					        GL11.glPopMatrix();        
						}
						else if (this.xPosition == dependency.xPosition && this.yPosition != dependency.yPosition){				
							this.drawRect(xPosition, yPosition+2, dependency.xPosition, dependency.yPosition-2, hex); //horizontal
						}
						else {
							this.drawRect(xPosition+2, yPosition, dependency.xPosition-2, dependency.yPosition, hex); //vertical
						}
					}
				}
				if(xPosition > guiareax-defwidth && yPosition > guiareay-defheight && xPosition < guiareax+guiareawidth && yPosition < guiareay+guiareaheight){
					minecraft.getTextureManager().bindTexture(res);
					this.drawTexturedModalRect(xPosition, yPosition, u, v, defwidth, defheight);
					
					
				//	itemRenderer.renderItemIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, renderItem, xPosition+defwidth/2-8, yPosition+defheight/2-8);
					drawItemStack(renderItem, xPosition+defwidth/2-8, yPosition+defheight/2-8, "");
				}
			}
		}
		
		
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
