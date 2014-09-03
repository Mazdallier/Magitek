package democretes.api;

import net.minecraft.item.ItemStack;
import democretes.api.recipe.RuneRecipe;

public class RunicHelper {

	
	
	public static ItemStack getResult(ItemStack catalyst) {
		for(RuneRecipe recipe : RuneRecipe.runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getOutput().copy();
			}
		}
		return null;
	}
	
	public static int getMacht(ItemStack catalyst) {
		for(RuneRecipe recipe : RuneRecipe.runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getEnergyRequired();
			}
		}
		return 0;
	}
	
	public static int getPurity(ItemStack catalyst) {
		for(RuneRecipe recipe : RuneRecipe.runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getPurity();
			}
		}
		return 0;
	}
	
	public static boolean recipeExists(ItemStack catalyst) {
		return getResult(catalyst) != null;
	}
	
	public static ItemStack getCatalystFromOutput(ItemStack output) {
		for(RuneRecipe recipe : RuneRecipe.runeRecipes) {
			if(recipe.getOutput().isItemEqual(output)) {
				return recipe.getCatalyst().copy();
			}
		}
		return null;
	}	
	
	public static int getMachtFromOutput(ItemStack output) {
		for(RuneRecipe recipe : RuneRecipe.runeRecipes) {
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
