package democretes.utils.nei;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.FMLLog;
import democretes.block.MTBlocks;
import democretes.lib.Reference;

public class NEIMagitekConfig implements IConfigureNEI {	
	
	@Override
	public void loadConfig() {
		API.hideItem(new ItemStack(MTBlocks.terraDummy, 1, OreDictionary.WILDCARD_VALUE));
		API.registerRecipeHandler(new NEIReconstructorRecipeHandler());
	}

	@Override
	public String getName() {
		return Reference.MOD_NAME + " NEI";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
	
}
