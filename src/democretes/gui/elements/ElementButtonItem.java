package democretes.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import democretes.Magitek;
import democretes.gui.GuiResearch;

public class ElementButtonItem extends ElementButtonInvisible {

	ItemStack stack;
	GuiResearch screen;
	
	int startingPage;
	public ElementButtonItem(int id, int locX, int locY, ItemStack stack, GuiResearch screen) {
		super(id, locX, locY, 30, 30, stack.getDisplayName());
		this.stack = stack;
		this.enabled = false;
		this.screen = screen;
		this.startingPage = screen.currentPage;
	}
	
	@Override
	public void drawButton(Minecraft mc, int x,	int y) {
		if(startingPage != screen.currentPage) {
			screen.getButtonList().remove(this);
		}else if(this.visible) {
			field_146123_n = xPosition + width+10 > x && x >= xPosition+10 && yPosition + height+10 > y && y >=yPosition+10;

			if(field_146123_n) {
				screen.drawCreativeText(stack.getDisplayName(), xPosition+10, yPosition+10);
			}
			renderItem(xPosition, yPosition, stack);
		}
	}
	
	void renderItem(int x, int y, ItemStack stack) {
		RenderItem render = new RenderItem();

		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glScalef(1.1F, 1.1F, 1.1F);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		RenderHelper.enableGUIStandardItemLighting();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		render.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), stack, x, y);
		render.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), stack, x, y);
		RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
	}

    

}
