package democretes.gui.client.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ElementButtonInvisible extends GuiButton {
	
	public boolean selected;
	public int width;

	/**
	 * Creates text without a texture around it using the following parameters. Uses unicode font.
	 * @param id
	 * @param locX
	 * @param locY
	 * @param width
	 * @param height
	 * @param name
	 */	
	public ElementButtonInvisible(int id, int locX, int locY, int width, int height, String name) {
		super(id, locX, locY, width, height, name);
		this.width = width;
	}
	
	@Override
	public void drawButton(Minecraft mc, int x,	int y) {
		if(this.visible) {
			field_146123_n = x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height;
			int k = getHoverState(field_146123_n);
			
			if(selected) {
				k = 2;
			}
			
			boolean unicode = mc.fontRenderer.getUnicodeFlag();
			mc.fontRenderer.setUnicodeFlag(true);
			mc.fontRenderer.drawString(displayString, xPosition + (k == 2 ? 1 : 0), yPosition + (height - 8) / 2, k == 2 ? 14737632 : 0);
			mc.fontRenderer.setUnicodeFlag(unicode);	
		}
	}

}
