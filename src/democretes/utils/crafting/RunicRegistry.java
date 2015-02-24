package democretes.utils.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.api.recipe.ChipCrafterRecipe;
import democretes.item.MTItems;

public class RunicRegistry {
	
	public static ChipCrafterRecipe fire;
	public static ChipCrafterRecipe water;
	public static ChipCrafterRecipe earth;
	public static ChipCrafterRecipe air;
	public static ChipCrafterRecipe energy;
	public static ChipCrafterRecipe balance;
	public static ChipCrafterRecipe control;

	public static void initRunicRecipes() {
		fire = new ChipCrafterRecipe(new ItemStack(Items.blaze_powder), new ItemStack(MTItems.chipset, 2, 0), 600, 0);
		water = new ChipCrafterRecipe(new ItemStack(Items.dye, 1, 4), new ItemStack(MTItems.chipset, 2, 1), 600, 0);
		earth = new ChipCrafterRecipe(new ItemStack(Items.clay_ball), new ItemStack(MTItems.chipset, 2, 2), 600, 0);
		air = new ChipCrafterRecipe(new ItemStack(Items.feather), new ItemStack(MTItems.chipset, 2, 3), 600, 0);
		energy = new ChipCrafterRecipe(new ItemStack(Items.blaze_rod), new ItemStack(MTItems.chipset, 1, 4), 2400, 0);
		balance = new ChipCrafterRecipe(new ItemStack(Items.magma_cream), new ItemStack(MTItems.chipset, 1, 5), 1800, 0);
		control = new ChipCrafterRecipe(new ItemStack(Items.ender_eye), new ItemStack(MTItems.chipset, 1, 6), 2400, 0);
	}

}
