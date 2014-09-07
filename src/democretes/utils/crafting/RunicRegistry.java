package democretes.utils.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.api.recipe.RuneRecipe;
import democretes.item.MTItems;

public class RunicRegistry {
	
	public static RuneRecipe fire;
	public static RuneRecipe water;
	public static RuneRecipe earth;
	public static RuneRecipe air;
	public static RuneRecipe energy;
	public static RuneRecipe balance;
	public static RuneRecipe control;

	public static void initRunicRecipes() {
		fire = new RuneRecipe(new ItemStack(Items.blaze_powder), new ItemStack(MTItems.rune, 2, 0), 600, 0);
		water = new RuneRecipe(new ItemStack(Items.dye, 1, 4), new ItemStack(MTItems.rune, 2, 1), 600, 0);
		earth = new RuneRecipe(new ItemStack(Items.clay_ball), new ItemStack(MTItems.rune, 2, 2), 600, 0);
		air = new RuneRecipe(new ItemStack(Items.feather), new ItemStack(MTItems.rune, 2, 3), 600, 0);
		energy = new RuneRecipe(new ItemStack(Items.blaze_rod), new ItemStack(MTItems.rune, 1, 4), 2400, 0);
		balance = new RuneRecipe(new ItemStack(Items.magma_cream), new ItemStack(MTItems.rune, 1, 5), 1800, 0);
		control = new RuneRecipe(new ItemStack(Items.ender_eye), new ItemStack(MTItems.rune, 1, 6), 2400, 0);
	}

}
