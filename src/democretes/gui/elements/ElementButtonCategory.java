package democretes.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.EnumChatFormatting;
import democretes.utils.helper.StringHelper;
import democretes.utils.research.Blueprint.ResearchType;

public class ElementButtonCategory extends ElementButtonInvisible {

	public ResearchType category;

	public ElementButtonCategory(int id, int locX, int locY, ResearchType type) {
		super(id, locX, locY, ("magitek.research.category." + type.toString()).length()*4, 10, StringHelper.localize("magitek.research.category." + type.toString().toLowerCase()));
		this.category = type;
	}
	

}
