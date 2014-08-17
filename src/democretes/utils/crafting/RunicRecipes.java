package democretes.utils.crafting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.item.ItemsMT;
import democretes.utils.crafting.AltarRecipes.AltarRecipe;

public class RunicRecipes {
		
	public static class RuneRecipe {
		
		static ItemStack catalyst;
		static ItemStack output;
		static int macht;
		static int purity;
		
		public RuneRecipe(ItemStack catalyst, ItemStack output, int macht, int purity) {
			this.catalyst = catalyst;
			this.output = output;
			this.macht = macht;
			this.purity = purity;
			runeRecipes.add(this);
		}
		
		public static ItemStack getCatalyst() {
			return catalyst;
		}
		
		public static ItemStack getOutput() {
			return output;
		}
		
		public static int getEnergyRequired() {
			return macht;
		}
		
		public static int getPurity() {
			return purity;
		}
	}	

	public static List<RuneRecipe> runeRecipes = new ArrayList<RuneRecipe>();
		
	public static RuneRecipe addRecipe(ItemStack input, ItemStack output, int energy, int purity) {
		RuneRecipe recipe = new RuneRecipe(input, output, energy, purity);
		runeRecipes.add(recipe);
		return recipe;
	}
	
	public static void addRecipe(RuneRecipe recipe) {
		runeRecipes.add(recipe);
	}
	
	public static ItemStack getResult(ItemStack catalyst) {
		for(RuneRecipe recipe : runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getOutput().copy();
			}
		}
		return null;
	}
	
	public static int getMacht(ItemStack catalyst) {
		for(RuneRecipe recipe : runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getEnergyRequired();
			}
		}
		return 0;
	}
	
	public static int getPurity(ItemStack catalyst) {
		for(RuneRecipe recipe : runeRecipes) {
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
		for(RuneRecipe recipe : runeRecipes) {
			if(recipe.getOutput().isItemEqual(output)) {
				return recipe.getCatalyst().copy();
			}
		}
		return null;
	}	
	
	public static int getMachtFromOutput(ItemStack output) {
		for(RuneRecipe recipe : runeRecipes) {
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
