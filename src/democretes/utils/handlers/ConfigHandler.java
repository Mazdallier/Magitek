package democretes.utils.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {
	
	public static int syphonAmount;
	public static void init(File file)    {

		Configuration config = new Configuration(file);
		
		config.load();
		
		Property sypon = config.get("Item Configs", "Amount the syphon drains per use.", 100);
		syphonAmount = sypon.getInt();
		
		config.save();
        
        
        
	}
	
}
