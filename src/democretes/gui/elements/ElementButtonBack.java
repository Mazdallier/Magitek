package democretes.gui.elements;

import org.lwjgl.opengl.GL11;

import democretes.gui.GuiResearch;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ElementButtonBack extends ElementButtonInvisible {
	

	public ElementButtonBack(int locX, int locY) {
		super(100, locX, locY, 18, 10, "");
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		if(this.visible) {
			GL11.glColor4f(1F, 1F, 1F, 1F);
			mc.renderEngine.bindTexture(GuiResearch.texture);

			field_146123_n = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;
			int k = getHoverState(field_146123_n);	
			
			if(selected) {
				k = 2;
			}
			
			drawTexturedModalRect(this.xPosition, this.yPosition, k != 2 ? 3 : 26, 223, 15, 9);			
		}
	}

}
