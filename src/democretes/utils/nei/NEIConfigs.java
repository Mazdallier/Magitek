package democretes.utils.nei;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.FMLLog;
import democretes.block.MTBlocks;

public class NEIConfigs implements IConfigureNEI {	
	
	@Override
	public void loadConfig() {
		API.hideItem(new ItemStack(MTBlocks.altarDummy, 1, OreDictionary.WILDCARD_VALUE));
		API.hideItem(new ItemStack(MTBlocks.terraDummy, 1, OreDictionary.WILDCARD_VALUE));
		
		API.registerRecipeHandler(new NEIAltarRecipeHandler());
		API.registerUsageHandler(new NEIAltarRecipeHandler());
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
