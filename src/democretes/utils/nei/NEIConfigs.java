package democretes.utils.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.FMLLog;

public class NEIConfigs implements IConfigureNEI {	
	
	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new NEIAltarRecipeHandler());
		API.registerUsageHandler(new NEIAltarRecipeHandler());
		FMLLog.info("NEI Registered");
	}

	@Override
	public String getName() {
		return "Magitek NEI";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
	
}
