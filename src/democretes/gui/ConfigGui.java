package democretes.gui;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import democretes.lib.Reference;
import democretes.utils.handlers.ConfigHandler;

public class ConfigGui extends GuiConfig {

	public ConfigGui(GuiScreen screen) {
		super(screen, new ConfigElement(
				ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));

	}

}
