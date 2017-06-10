package com.waabbuffet.hydromancy.client.gui.lexicon.util.button;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

public class GuiButtonResearch extends GuiButton {

	public boolean hovering;
	public GuiButtonResearch(int x, int y, int widtht, int heightt, String text) {
		super(0, x, y, widtht, heightt, text);
		// TODO Auto-generated constructor stub
	}
	public void drawButton(Minecraft minecraft, int mx, int my)
    {
		hovering = (mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height);
        FontRenderer fontrenderer = minecraft.fontRendererObj;
        minecraft.getTextureManager().bindTexture(BUTTON_TEXTURES);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.hovered = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height;
        int k = this.getHoverState(this.hovered);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
        this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
        this.mouseDragged(minecraft, mx, my);
        int l = 14737632;

        if (packedFGColour != 0)
        {
            l = packedFGColour;
        }
        else if (!this.enabled)
        {
            l = 10526880;
        }
        else if (this.hovered)
        {
            l = 16777120;
        }

        GL11.glPushMatrix();
        GL11.glScalef(0.75f, 0.75f, 0.75f);
        this.drawCenteredString(fontrenderer, this.displayString, (int)((this.xPosition + 0.75 + this.width / 2)/0.75), (int)((this.yPosition + (this.height - 4/0.75) / 2)/0.75), l);
        GL11.glPopMatrix();
    }
}
