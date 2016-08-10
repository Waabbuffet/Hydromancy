package com.waabbuffet.hydromancy.client.gui.TileEntity;

import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.inventory.containers.ContainerPurifier;
import com.waabbuffet.hydromancy.tileentity.generation.TileEntityPurifier;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class GuiPurifier extends GuiContainer {

	private IInventory playerInv;
	private TileEntityPurifier te;

	public GuiPurifier(IInventory playerInv, TileEntityPurifier te) {
		super(new ContainerPurifier(playerInv, te));

		this.playerInv = playerInv;
		this.te = te;

		this.xSize = 175;
		this.ySize = 165;
	}




	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/PurifierGUI.png"));
		this.drawTexturedModalRect(guiX, guiY, 0, 0, this.xSize, this.ySize);


		if(this.te.getBurnTime() > 0)
		{
			double Progress1 = ((double)this.te.getBurnTime())/1600.0;
			
			if(this.te.waterTank.getFluid().isFluidEqual(new FluidStack(FluidRegistry.WATER, 0)))
				this.drawTexturedModalRect(guiX + 53, guiY + 59, 176, 55, 13, (int) (13*Progress1)); // 189 68
		}
		if(this.te.getCompletionTime() > 0)
		{
			double Progress = ((double)this.te.getCompletionTime())/2400.0;

			this.drawTexturedModalRect(guiX + 77, guiY + 40, 176, 86, 15, (int)(21 * Progress)); //190, 106 
		}
		if(this.te.waterTank != null)
		{
			drawFluid(this.te.waterTank.getFluid(), guiX + 104, guiY + 19, 16, 58, 4000); //120, 18
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {


		String s = "Purifier Inventory";
		this.fontRendererObj.drawString(s, 50, 7, 4210752);            //#404040

	}


	public void drawFluid(FluidStack fluid, int x, int y, int width, int height, int maxCapacity) {
		if (fluid == null || fluid.getFluid() == null) {
			return;
		}
		IIcon icon = fluid.getFluid().getIcon(fluid);

		if (icon == null) {
			icon = ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
		}

		mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		setGLColorFromInt(fluid.getFluid().getColor(fluid));
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - fullX * 16;
		int lastY = height - fullY * 16;
		int level = fluid.amount * height / maxCapacity;
		int fullLvl = (height - level) / 16;
		int lastLvl = (height - level) - fullLvl * 16;
		for (int i = 0; i < fullX; i++) {
			for (int j = 0; j < fullY; j++) {
				if (j >= fullLvl) {
					drawCutIcon(icon, x + i * 16, y + j * 16, 16, 16, j == fullLvl ? lastLvl : 0);
				}
			}
		}
		for (int i = 0; i < fullX; i++) {
			drawCutIcon(icon, x + i * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0);
		}
		for (int i = 0; i < fullY; i++) {
			if (i >= fullLvl) {
				drawCutIcon(icon, x + fullX * 16, y + i * 16, lastX, 16, i == fullLvl ? lastLvl : 0);
			}
		}
		drawCutIcon(icon, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0);
	}

	//The magic is here
	private void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut) {
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, zLevel, icon.getMinU(), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + cut, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
		tess.draw();
	}
	
	public static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

}