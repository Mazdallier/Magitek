package democretes.api.helpers;

import net.minecraft.item.ItemStack;
import democretes.api.recipe.ReconstructorRecipe;

public class ReconstructorHelper {
	
	public static int getEnergyForItemStack(ItemStack stack) {
		if(stack == null) {
			return 0;
		}
		for(ReconstructorRecipe recipe : ReconstructorRecipe.reconstructorRecipes) {
			if(recipe.getOutput().isItemEqual(stack)) {
				return recipe.getEnergy();
			}
		}
		return 0;
	}
	
	public static boolean recipeExists(ItemStack stack) {
		return getEnergyForItemStack(stack) > 0;
	}

}
