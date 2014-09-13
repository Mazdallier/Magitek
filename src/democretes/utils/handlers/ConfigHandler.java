package democretes.utils.handlers;

import java.io.File;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import democretes.lib.Reference;

public class ConfigHandler {

	public static int syphonAmount;
	public static int range;
	public static int maxRatio;

	public static Configuration config;
	
	public static void init(File file)    {
		config = new Configuration(file);
		
		config.load();   
		
		sync();

		FMLCommonHandler.instance().bus().register(new ConfigChange());
	}
	
	public static void sync() {	
		Property syphon = config.get(Configuration.CATEGORY_GENERAL, "Amount the syphon drains per use.", 100);
		syphonAmount = syphon.getInt();
		
		Property r = config.get(Configuration.CATEGORY_GENERAL, "Range that generators and runes search.", 15);
		range = r.getInt();
		
		Property ratio = config.get(Configuration.CATEGORY_GENERAL, "The ratio as a percent at which the Macht Ring will stop providing energy. (MaxPlayerMacht*Ratio)/100.", 75);
		maxRatio = ratio.getInt();
		
		config.save();  
	}
	
	public static class ConfigChange {

		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if(eventArgs.modID.equals(Reference.MOD_ID)) {
				sync();
			}
		}

	}
}
