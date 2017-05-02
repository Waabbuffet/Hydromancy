package com.waabbuffet.hydromancy.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.waabbuffet.hydromancy.blocks.HydromancyBlocksHandler;
import com.waabbuffet.hydromancy.client.gui.lexicon.GuiTranslationTable;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.TranslationTableResearch;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonResearch;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonTabResearch;
import com.waabbuffet.hydromancy.client.gui.lexicon.util.button.GuiButtonTabTranslation;
import com.waabbuffet.hydromancy.items.HydromancyItemsHandler;
import com.waabbuffet.hydromancy.packet.HydromancyPacketHandler;
import com.waabbuffet.hydromancy.packet.packets.SUpdateTranslationTable;
import com.waabbuffet.hydromancy.properties.HydromancyPlayerProperties;
import com.waabbuffet.hydromancy.tileentity.lexicon.TileEntityTranslationTable;
import com.waabbuffet.hydromancy.util.IllegalException;
import com.waabbuffet.hydromancy.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class HydromancyResearchHandler extends GuiButton{
	private int[] additionalIDs = {0};
	private GuiTranslationTable gui;
	private boolean[] sound;
	boolean submenu = false;
	private int arrid;
	private ResourceLocation res = new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table_Research_2.png");
	
	private GuiButtonTabResearch tabResearch;
	private GuiButtonTabTranslation tabTranslation;
	private EntityPlayer player;
	GuiButtonResearch research = new GuiButtonResearch(xPosition + 96, yPosition + 95, 45, 20, "Research");
	
	private TranslationTableResearch basicHydromancy;
	private TranslationTableResearch lexicon;
	private TranslationTableResearch[] ttrArr;
	private TileEntityTranslationTable te;
	private List<String> researchesUpdated = new ArrayList<String>();
	
	private enum researchStates {
		LOCKED,
		IN_PROGRESS,
		AVAILABLE,
		RESEARCHED
	}

	public HydromancyResearchHandler(int ID, int x, int y, int width, int height, TileEntityTranslationTable te, EntityPlayer player){
		super(ID, x, y, 0, 0, "");
		this.width = width;
		this.height = height;
		this.te = te;
		this.player = player;
		initResearches();
		registerResearches();	
	}	

	private void initResearches() {
		basicHydromancy = new TranslationTableResearch(additionalIDs[0], this.xPosition + 30, this.yPosition + 60, this.xPosition, this.yPosition, this.width, this.height);
		basicHydromancy.researchState = TranslationTableResearch.researchStates.AVAILABLE;
		lexicon = new TranslationTableResearch(additionalIDs[0], this.xPosition + 15, this.yPosition + 15, this.xPosition, this.yPosition, this.width, this.height);
	}
	
	private void registerResearches() {
		basicHydromancy.addResearch(null, "basic_hydromancy", "basic_hydromancy_desc", 1, new ItemStack(HydromancyBlocksHandler.Block_Purifier), "root", null);
		lexicon.addResearch(basicHydromancy, "lexicon", "lexicon_desc", 5, new ItemStack(HydromancyItemsHandler.lexicon), "square", null);
		ttrArr = new TranslationTableResearch[] {basicHydromancy,lexicon};
		sound = new boolean[ttrArr.length];
		updateResearchStates();
	}
	
	private void updateUponDrag(int mdmx, int mdmy){
		for(int i=0;i<ttrArr.length;i++){
			ttrArr[i].updateXY(mdmx, mdmy);
		}
	}
	
	@Override
	public void drawButton(Minecraft minecraft, int mx, int my) {
		//System.out.println(gui.te.getStackSize(0));
		if(Mouse.isButtonDown(0) == true && mousePressed(minecraft,mx,my)){			
			//these functions are quite accurate but every enhancment is a way to go!
			if(submenu == true && mx < this.xPosition+82) {
				int dmx = (int) (Mouse.getDX()/2-Math.sqrt((double)Mouse.getDX()));
				int dmy = (int) (Mouse.getDY()/2-Math.sqrt((double)Mouse.getDY()));
				updateUponDrag(dmx,dmy);
			}
			else if (submenu == false){
				int dmx = (int) (Mouse.getDX()/2-Math.sqrt((double)Mouse.getDX()));
				int dmy = (int) (Mouse.getDY()/2-Math.sqrt((double)Mouse.getDY()));
				updateUponDrag(dmx,dmy);
			}
		}
		for(int i=0;i<ttrArr.length;i++){				
			try {
				ttrArr[i].drawActualButton(minecraft, mx, my);
			} catch (IllegalException e) {
				e.printStackTrace();
			}
			if(Mouse.isButtonDown(0) == true && ttrArr[i].researchState.name() != "LOCKED" && ttrArr[i].hovered == true && mousePressed(minecraft,mx,my)){
				if(submenu == true && mx < this.xPosition+82){
					sound[i] = true;
					submenu = true;
					arrid = i;
				}
				else if(submenu == false){
					sound[i] = true;
					submenu = true;
					arrid = i;
				}
			}			
		}
		if(submenu == true){
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			minecraft.getTextureManager().bindTexture(res);
			this.drawTexturedModalRect(this.xPosition+82, this.yPosition, 0, 0, 71, 134);
			RenderItem itemRenderer = new RenderItem();
			GL11.glPushMatrix();
			GL11.glScalef(0.75f, 0.75f, 0.75f);
			itemRenderer.renderItemIntoGUI(minecraft.fontRenderer, minecraft.getTextureManager(), new ItemStack(HydromancyItemsHandler.lostFragment), (int)((this.xPosition+92)/0.75), (int)((this.yPosition+119)/0.75));
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			this.drawCenteredString(minecraft.fontRenderer,ttrArr[arrid].description,(int)((this.xPosition+118)/0.5),(int)((this.yPosition + 6)/0.5), 0xFFFFFF);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(0.75f, 0.75f, 0.75f);
			this.drawString(minecraft.fontRenderer,Integer.toString(ttrArr[arrid].cost),(int)((this.xPosition+105)/0.75), (int)((this.yPosition + 123)/0.75), ttrArr[arrid].cost <= te.getStackSize(0) ? 0xFFFFFF : 0xFF0000);
			GL11.glPopMatrix();
			if (Mouse.isButtonDown(0) == true && mx < this.xPosition+82 && ttrArr[arrid].hovered == false && mousePressed(minecraft,mx,my)){
				submenu = false;
			}
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			research.drawButton(minecraft, mx, my);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			if(te.getStackSize(0) >= ttrArr[arrid].cost && te.hasPaper() && !te.researchInProgress)
			{
				research.enabled = true;
				if(Mouse.isButtonDown(0) == true && ttrArr[arrid].researchState.name() == "AVAILABLE" && research.hovering && mousePressed(minecraft,mx,my)){
					//ttrArr[arrid].researchState = TranslationTableResearch.researchStates.IN_PROGRESS;
					HydromancyPlayerProperties.get(player).addResearchState(ttrArr[arrid].unlocalizedName, TranslationTableResearch.researchStates.IN_PROGRESS.name(), arrid);
					te.decrStackSize(0, ttrArr[arrid].cost);
					HydromancyPacketHandler.INSTANCE.sendToServer(new SUpdateTranslationTable(te));
					updateResearchStates();
				}
			}
			else if(te.getStackSize(0) < ttrArr[arrid].cost || te.researchInProgress || ttrArr[arrid].researchState.name() != "IN_PROGRESS" || ttrArr[arrid].researchState.name() != "RESEARCHED"/*|| !gui.te.hasPaper()*/)
			{
				research.enabled = false;
			}
			GL11.glPopMatrix();
		}
		GL11.glEnable(GL11.GL_BLEND);
		
		tabTranslation.drawSwitchTab(minecraft, mx, my);
		minecraft.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID + ":textures/gui/Translation_Table_Research.png"));
		this.drawTexturedModalRect(this.xPosition - 19, this.yPosition - 19, 0, 0, 191, 172);
		tabResearch.drawSwitchTab(minecraft, mx, my);	
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	private void updateResearchStates() {
		researchesUpdated = HydromancyPlayerProperties.get(player).getResearchStates();
		System.out.println(researchesUpdated.toString());
		if(researchesUpdated != null && researchesUpdated.size() > 1){
			for(int i = 0; i < ttrArr.length; i++)
			{
				for(int j = 0; j < researchesUpdated.size(); j++)
				{
					if(ttrArr[i].unlocalizedName.equals(researchesUpdated.get(j))){
						ttrArr[i].researchState = TranslationTableResearch.researchStates.valueOf(researchesUpdated.get(j+1));
						System.out.println(ttrArr[i].researchState);
					}
				}
				if(ttrArr[i].researchState == TranslationTableResearch.researchStates.IN_PROGRESS && te.researchInProgress == false)
				{
					te.researchInProgress = true;
					te.activeResearch = ttrArr[i];
				}
			}
		}
	}
	
	@Override
	public void func_146113_a(SoundHandler p_146113_1_){
		if(sound[arrid] == true){
			p_146113_1_.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			sound[arrid] = false;
		}
	}
	public void sendInstances(GuiButtonTabResearch tabResearch, GuiButtonTabTranslation tabTranslation) {
		this.tabResearch = tabResearch;
		this.tabTranslation = tabTranslation;
	}
}
