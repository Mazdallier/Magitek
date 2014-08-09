package democretes.utils.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {
	
	public static int syphonAmount;
	public static int range;
	
	public static void init(File file)    {

		Configuration config = new Configuration(file);
		
		config.load();
		
		Property syphon = config.get("Item Configs", "Amount the syphon drains per use.", 100);
		syphonAmount = syphon.getInt();
		
		Property r = config.get("Macht Configs", "Range that generators and runes search.", 10);
		range = r.getInt();
		
		config.save();
        
        
        
	}
	
}
