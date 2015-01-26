package democretes.api.helpers;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import democretes.api.recipe.AltarRecipe;
import democretes.api.recipe.PurityRecipe;

public class PurityAltarHelper {
	
	public static ItemStack getDarkResult(ItemStack input) {
		for(PurityRecipe recipe : PurityRecipe.altarPurityRecipes) {
			if(!recipe.isDark()) {
				continue;
			}
			if(recipe.getInput().isItemEqual(input)) {
				return recipe.getOutput().copy();
			}
		}
		return null;
	}
	
	public static ItemStack getLightResult(ItemStack input) {
		for(PurityRecipe recipe : PurityRecipe.altarPurityRecipes) {
			if(recipe.isDark()) {
				continue;
			}
			if(recipe.getInput().isItemEqual(input)) {
				return recipe.getOutput().copy();
			}
		}
		return null;
	}	
	
	public static boolean recipeExists(ItemStack stack) {
		for(PurityRecipe recipe : PurityRecipe.altarPurityRecipes) {
			if(recipe.getInput().isItemEqual(stack)) {
				return true;
			}
		}
		return false;
	}	

	private static ArrayList<ItemStack> getOreDictStack(String name) {
		return OreDictionary.getOres(name);
	}

}
