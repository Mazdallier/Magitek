package democretes.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import democretes.gui.elements.ElementButtonBack;
import democretes.gui.elements.ElementButtonCategory;
import democretes.gui.elements.ElementButtonInvisible;
import democretes.gui.elements.ElementButtonItem;
import democretes.gui.elements.ElementButtonNextPage;
import democretes.gui.elements.ElementButtonPreviousPage;
import democretes.utils.helper.RecipeHelper;
import democretes.utils.helper.StringHelper;
import democretes.utils.research.Blueprint;
import democretes.utils.research.Blueprint.ResearchType;
import democretes.utils.research.BlueprintHelper;

public class GuiResearch extends GuiScreen {
	
	public static ResourceLocation texture = new ResourceLocation("democretes", "textures/gui/itsabook.png");
	public static ResourceLocation craftingGrid = new ResourceLocation("democretes", "textures/gui/itsagrid.png");

	int guiWidth = 146;
	int guiHeight = 182;
	int left;
	int top;
	
	EntityPlayer player;
	
	ArrayList<Blueprint> index = new ArrayList<Blueprint>();
	
	int level;
	GuiButton prevButton;
	
	public int currentPage;	
	public Blueprint blueprint;
		
	public GuiResearch(EntityPlayer player) {
		super();
		this.player = player;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		left = width/2 - guiWidth/2;
		top = height/2 - guiHeight/2;	
		
		buttonList.clear();
		addCategories();
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);		
		if(blueprint != null) {
			fontRendererObj.setUnicodeFlag(true);
			this.drawCenteredString(fontRendererObj, StringHelper.localize(blueprint.getName()), left + guiWidth / 2, top + 8, 0);
			if(blueprint.getPages()[currentPage].text != null) {
				String text = StringHelper.localize(blueprint.getPages()[currentPage].text);
				this.fontRendererObj.drawSplitString(text, left + 20, top +30, 110, 0);
			}else if(blueprint.getPages()[currentPage].recipeA != null) {
				
			}else if(blueprint.getPages()[currentPage].recipeB != null) {
				
			}else if(blueprint.getPages()[currentPage].recipeC != null) {
				GL11.glColor4f(1F, 1F, 1F, 1F);
				mc.renderEngine.bindTexture(craftingGrid);
				drawTexturedModalRect(left+36, top+44, 0, 0, 80, 80);	
				renderCraftingRecipe(left + 24, top + 40, blueprint.getPages()[currentPage].recipeC);
			}
			fontRendererObj.setUnicodeFlag(false);			
		}else if(index != null && !index.isEmpty() && level == 1){
			fontRendererObj.setUnicodeFlag(true);
			this.drawCenteredString(fontRendererObj, StringHelper.localize("magitek.research.category." + index.get(0).getResearchType().toString().toLowerCase()), left + guiWidth / 2, top + 8, 0);	
			fontRendererObj.setUnicodeFlag(false);				
		}else{
			fontRendererObj.setUnicodeFlag(true);
			this.drawCenteredString(fontRendererObj, StringHelper.localize("magitek.book.title"), left + guiWidth / 2, top + 8, 0);
			this.drawCenteredString(fontRendererObj, StringHelper.localize("magitek.book.contents"), left + guiWidth / 2, top + 15, 0);
			fontRendererObj.setUnicodeFlag(false);			
		}
		super.drawScreen(par1, par2, par3);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if(button.id == 100) {
			if(level == 1) {
				addCategories();
			}else if(index != null && level == 2) {
				populateIndex((index.get(prevButton.id)).getResearchType());			
			}
			this.blueprint = null;
			return;
		}else if(button.id == 101) {
			if(currentPage+1 < blueprint.getPages().length) {
				currentPage++;		
				if(currentPage+1 == blueprint.getPages().length) {
					getButtonbyID(101).enabled = false;
					getButtonbyID(101).visible = false;
				}				
				getButtonbyID(102).enabled = true;
				getButtonbyID(102).visible = true;
			}
			return;
		}else if(button.id == 102) {
			if(currentPage > 0) {
				currentPage--;			
				if(currentPage==0) {
					getButtonbyID(102).enabled = false;
					getButtonbyID(102).visible = false;
				}			
				getButtonbyID(101).enabled = true;
				getButtonbyID(101).visible = true;
			}
			return;
		}
		if(button instanceof ElementButtonCategory) {
			populateIndex(((ElementButtonCategory)button).category);
		}else if(index != null) {
			this.buttonList.clear();
			this.blueprint = index.get(button.id);
			this.currentPage = 0;
			buttonList.add(new ElementButtonBack(left + 7, top + 7));
			level = 2;
			if(this.blueprint.getPages().length > 1) {
				buttonList.add(new ElementButtonNextPage(left + guiWidth-20, top + guiHeight-15));
				ElementButtonPreviousPage b = new ElementButtonPreviousPage(left, top + guiHeight-15);
				b.enabled= false;
				b.visible = false;
				buttonList.add(b);
			}
		}
		prevButton = button;
	}
	
	void addCategories() {
		level = 0;
		buttonList.clear();
		for(ResearchType t : ResearchType.values()) {
			buttonList.add(new ElementButtonCategory(t.ordinal(), left + 20, top + 30 + t.ordinal()*10, t));
		}
	}
	
	void populateIndex(ResearchType r) {
		level = 1;
		this.index.clear();
		buttonList.clear();
		buttonList.add(new ElementButtonBack(left + 7, top + 7));
		for(Blueprint b : BlueprintHelper.getBlueprintsByType(r)) {
			this.index.add(b);
		}		
		this.drawCenteredString(fontRendererObj, StringHelper.localize("magitek.research.category." + r.toString().toLowerCase()), left + guiWidth / 2, top + 8, 0);
		String name;
		for(int i = 0; i < index.size(); i++) {
			name = index.get(i).getName();
			buttonList.add(new ElementButtonInvisible(i, left + 20, top + 30, StringHelper.localize(name).length()*4, 10, StringHelper.localize(name)));
		}
	}
	
	GuiButton getButtonbyID(int id) {
		for(int i = 0; i < buttonList.size(); i++) {
			if(((GuiButton)buttonList.get(i)).id == id) {
				return (GuiButton)buttonList.get(i);
			}
		}
		return null;
	}
	
	void renderCraftingRecipe(int x, int y, ItemStack stack) {
		ItemStack[] recipe = RecipeHelper.getOrientatedRecipe(stack);
		if(recipe == null) {
			return;
		}
		buttonList.add(new ElementButtonItem(99, x+25, y-25, stack, this));
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(recipe[j*3+i] != null) {
					buttonList.add(new ElementButtonItem(90+i*3+j,x + j*25, y+i*25, recipe[j+i*3], this));		
				}
			}			
		}
	}

	public void drawCreativeText(String string, int x, int y) {
		drawCreativeTabHoveringText(string, x, y);			
	}

	public List getButtonList() {
		return this.buttonList;
	}

}
