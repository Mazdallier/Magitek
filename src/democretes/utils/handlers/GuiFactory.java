package democretes.utils.handlers;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import democretes.lib.Reference;

public class GuiFactory implements IModGuiFactory {
	
	public class ConfigGui extends GuiConfig {

		public ConfigGui(GuiScreen screen) {
			super(screen, 
					new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), 
					Reference.MOD_ID, 
					false, 
					false, 
					GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));

		}

	}
	
	@Override
	public void initialize(Minecraft minecraftInstance) {}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}

}
