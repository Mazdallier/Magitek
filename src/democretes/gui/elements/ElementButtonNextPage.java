package democretes.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import democretes.gui.GuiResearch;

public class ElementButtonNextPage extends ElementButtonInvisible {
	
	ResourceLocation texture;

	public ElementButtonNextPage(int locX, int locY) {
		super(101, locX, locY, 18, 10, "");
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y) {
		if(this.visible) {
			this.texture = GuiResearch.texture;
			GL11.glColor4f(1F, 1F, 1F, 1F);
			mc.renderEngine.bindTexture(texture);

			field_146123_n = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;
			int k = getHoverState(field_146123_n);	
			
			if(selected) {
				k = 2;
			}
			
			drawTexturedModalRect(this.xPosition, this.yPosition, k != 2 ? 3 : 26, 194, 18, 10);			
		}
	}
}
