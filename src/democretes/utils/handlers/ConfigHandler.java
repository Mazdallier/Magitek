package democretes.utils.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler {
	
	
	public static void init(File file)    {

		Configuration config = new Configuration(file);
		
		config.load();
		
		
		
		config.save();
        
        
        
	}
	
}
