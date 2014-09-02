package democretes.utils.crafting;

import net.minecraft.item.ItemStack;
import democretes.api.RitualType;
import democretes.api.recipe.RitualRecipe;

public class RitualHelper {


	public static RitualRecipe getRecipe(ItemStack catalyst) {
		for(RitualRecipe recipe : RitualRecipe.ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe;
			}
		}
		return null;
	}
	
	public static ItemStack[] getInputForCatalyst(ItemStack catalyst) {
		for(RitualRecipe recipe : RitualRecipe.ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getInput();
			}
		}
		return null;
	}
	
	public static ItemStack getOutputForCatalyst(ItemStack catalyst) {
		if(catalyst == null) {
			return null;
		}
		for(RitualRecipe recipe : RitualRecipe.ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getOutput();
			}
		}
		return null;
	}
	
	public static RitualType getTypeForCatalyst(ItemStack catalyst) {
		for(RitualRecipe recipe : RitualRecipe.ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getRitual();
			}
		}
		return null;
	}
	
	public static int getMachtForCatalyst(ItemStack catalyst) {
		if(catalyst == null) {
			return 0;
		}
		for(RitualRecipe recipe : RitualRecipe.ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getEnergyRequired();
			}
		}
		return 0;
	}
	
	public static boolean recipeExists(ItemStack catalyst) {
		return getInputForCatalyst(catalyst) != null;
	}
	
	public static boolean inputsMatch(ItemStack[] input, ItemStack recipe[]) {
		if(input == null || recipe == null) {
			return false;
		}
		if(input.length != recipe.length) {
			return false;
		}
		ItemStack[] stacks = input;
		int check = 0;
		for(int i = 0; i < stacks.length; i++) {
			for(int j = 0; j < recipe.length; j++) {
				if(recipe[j] == null || stacks[i] == null) {
					return false;
				}
				if(stacks[i].isItemEqual(recipe[j])) {
					check++;
					break;
				}
			}
		}
		return check >= recipe.length;
	}
	
}
