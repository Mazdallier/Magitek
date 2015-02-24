package democretes.api.helpers;

import net.minecraft.item.ItemStack;
import democretes.api.recipe.ChipCrafterRecipe;

public class ChipCrafterHelper {

	
	
	public static ItemStack getResult(ItemStack catalyst) {
		for(ChipCrafterRecipe recipe : ChipCrafterRecipe.runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getOutput().copy();
			}
		}
		return null;
	}
	
	public static int getMacht(ItemStack catalyst) {
		for(ChipCrafterRecipe recipe : ChipCrafterRecipe.runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getEnergyRequired();
			}
		}
		return 0;
	}
		
	public static boolean recipeExists(ItemStack catalyst) {
		return getResult(catalyst) != null;
	}
	
	public static ItemStack getCatalystFromOutput(ItemStack output) {
		if(output == null) {
			return null;
		}
		for(ChipCrafterRecipe recipe : ChipCrafterRecipe.runeRecipes) {
			if(recipe.getOutput().isItemEqual(output)) {
				return recipe.getCatalyst().copy();
			}
		}
		return null;
	}	
	
	public static int getMachtFromOutput(ItemStack output) {
		for(ChipCrafterRecipe recipe : ChipCrafterRecipe.runeRecipes) {
			if(recipe.getOutput().isItemEqual(output)) {
				return recipe.getEnergyRequired();
			}
		}
		return 0;
	}
	
	public static boolean recipeExistsFromOutput(ItemStack output) {
		return getCatalystFromOutput(output) != null;
	}

}
