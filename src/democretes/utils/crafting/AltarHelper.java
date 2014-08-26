package democretes.utils.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import democretes.api.recipe.AltarRecipe;

public class AltarHelper {
	
	
	public static ItemStack getResult(ItemStack input) {
		for(AltarRecipe recipe : AltarRecipe.altarRecipes) {
			if(recipe.getInput().isItemEqual(input)) {
				return recipe.getOutput().copy();
			}
		}
		return null;
	}
	
	public static int getMacht(ItemStack input) {
		for(AltarRecipe recipe : AltarRecipe.altarRecipes) {
			if(recipe.getInput().isItemEqual(input)) {
				return recipe.getEnergyRequired();
			}
		}
		return 0;
	}
	
	public static boolean recipeExists(ItemStack stack) {
		return getResult(stack) != null;
	}	

	private static ArrayList<ItemStack> getOreDictStack(String name) {
		return OreDictionary.getOres(name);
	}

}
