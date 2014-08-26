package democretes.utils.handlers;

import java.io.File;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import democretes.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler /**extends GuiConfig implements IModGuiFactory**/ {
	/**
	public ConfigHandler(GuiScreen parentScreen) {
		super(parentScreen, 
				new ConfigElement(config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), 
				Reference.MOD_ID, 
				false, 
				false, 
				GuiConfig.getAbridgedConfigPath(config.toString()));
	}**/

	public static int syphonAmount;
	public static int range;

	static Configuration config;
	
	public static void init(File file)    {
		config = new Configuration(file);
		
		config.load();   
		
		sync();
	}
	
	public static void sync() {	
		Property syphon = config.get(Configuration.CATEGORY_GENERAL, "Amount the syphon drains per use.", 100);
		syphonAmount = syphon.getInt();
		
		Property r = config.get(Configuration.CATEGORY_GENERAL, "Range that generators and runes search.", 10);
		range = r.getInt();
		
		config.save();  
	}
	
	/**
	@Override
	public void initialize(Minecraft minecraftInstance) {}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return this.getClass();
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
	**/
}
