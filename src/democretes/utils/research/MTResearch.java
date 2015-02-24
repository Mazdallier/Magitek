package democretes.utils.research;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import democretes.block.MTBlocks;
import democretes.utils.research.Blueprint.ResearchType;

public class MTResearch {
	
	public static Blueprint furnace;
	
	public static void initResearch() {
		furnace = new Blueprint("magitek.blueprint.furnace", ResearchType.SMELTING, new BlueprintPage[]{new BlueprintPage("magitek.blueprint.furnace.0"), new BlueprintPage(new ItemStack(MTBlocks.totemVision))});

	}

}
