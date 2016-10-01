package com.waabbuffet.hydromancy.client.gui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslationTable;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.TranslationTableResearch;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class HydromancyResearchHandler extends GuiButton{
	private int[] additionalIDs = {0};
	private GuiTranslationTable gui;
	private boolean sound;
	boolean submenu = false;
	private int arrid;
	private ResourceLocation res = new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table_Research.png");;
	
	private TranslationTableResearch basicHydromancy;
	private TranslationTableResearch lexicon;
	private TranslationTableResearch[] ttrArr;

	public HydromancyResearchHandler(int ID, int x, int y, int width, int height, GuiTranslationTable gui){
		super(ID, x, y, 0, 0, "");
		this.width = width;
		this.height = height;	
		this.gui = gui;
		initResearches();
		registerResearches();
	}	
	/**
	 * You can pass ids of research buttons.
	 */
	public void passAdditionalResIDs(int[] ids){
		additionalIDs = ids;
	}

	private void initResearches() {
		basicHydromancy = new TranslationTableResearch(additionalIDs[0], this.xPosition + 30, this.yPosition + 15, this.xPosition, this.yPosition, this.width, this.height);
		lexicon = new TranslationTableResearch(additionalIDs[0], this.xPosition + 15, this.yPosition + 60, this.xPosition, this.yPosition, this.width, this.height);
	}
	
	private void registerResearches() {
		basicHydromancy.addResearch(null, "basic_hydromancy", "basic_hydromancy_desc", 1, new ItemStack(HydromancyBlocksHandler.Block_Purifier), "root", null);
		lexicon.addResearch(basicHydromancy, "lexicon", "lexicon_desc", 5, new ItemStack(HydromancyItemsHandler.lexicon), "square", null);
		ttrArr = new TranslationTableResearch[] {basicHydromancy,lexicon};
	}
	
	//sadly you have (at least for now) to copy n' paste modified first line for each instance :/
	private void updateUponDrag(int mdmx, int mdmy){
		for(int i=0;i<ttrArr.length;i++){
			ttrArr[i].updateXY(mdmx, mdmy);
		}
		//basicHydromancy.updateXY(mdmx, mdmy);
		//lexicon.updateXY(mdmx, mdmy);
	}
	
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my) {
		if(Mouse.isButtonDown(0) == true && mousePressed(minecraft,mx,my)){
			//these functions are quite accurate but every enhancment is a way to go!
			int dmx = (int) (Mouse.getDX()/2-Math.sqrt((double)Mouse.getDX()));
			int dmy = (int) (Mouse.getDY()/2-Math.sqrt((double)Mouse.getDY()));
			updateUponDrag(dmx,dmy);
		}
		for(int i=0;i<ttrArr.length;i++){
			
			
			ttrArr[i].drawActualButton(minecraft, mx, my);
			if(Mouse.isButtonDown(0) == true && ttrArr[i].hovered == true && mousePressed(minecraft,mx,my)){
				sound = true;
				submenu = true;
				arrid = i;
			}
			else
				sound = false;
			
		}
		if(submenu == true){
			minecraft.getTextureManager().bindTexture(res);
			this.drawTexturedModalRect(this.xPosition+81, this.yPosition, 177, 0, 63, 114);
			minecraft.fontRenderer.FONT_HEIGHT = 10;
			this.drawCenteredString(minecraft.fontRenderer,ttrArr[arrid].description,this.xPosition+110,this.yPosition + 8, 0xFFFFFF);
			if (Mouse.isButtonDown(0) == true && ttrArr[arrid].hovered == false && mousePressed(minecraft,mx,my)){
				submenu = false;
			}
		}
	}
	
	/*public void drawResearch(Minecraft minecraft, int mx, int my) {
		basicHydromancy.drawActualButton(minecraft, mx, my);
		lexicon.drawActualButton(minecraft, mx, my);
	}*/
	
	@Override
	public void func_146113_a(SoundHandler p_146113_1_){
		if(sound == true)
			p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
	}
}
