package democretes.utils.crafting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.item.ItemsMT;

public class RunicRecipes {
	
	static List<RunicRecipe> runeRecipes = new ArrayList<RunicRecipe>();
	
	public static class RunicRecipe {
		
		static ItemStack catalyst;
		static ItemStack output;
		static int macht;
		static int purity;
		
		public RunicRecipe(ItemStack catalyst, ItemStack output, int macht, int purity) {
			this.catalyst = catalyst;
			this.output = output;
			this.macht = macht;
			this.purity = purity;
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
	
	public static void initRunicRecipes() {
		addRecipe(new ItemStack(Items.blaze_powder), new ItemStack(ItemsMT.rune, 2, 0), 600, 0);
		addRecipe(new ItemStack(Items.dye, 1, 4), new ItemStack(ItemsMT.rune, 2, 1), 600, 0);
		addRecipe(new ItemStack(Items.clay_ball), new ItemStack(ItemsMT.rune, 2, 2), 600, 0);
		addRecipe(new ItemStack(Items.feather), new ItemStack(ItemsMT.rune, 2, 3), 600, 0);
		addRecipe(new ItemStack(Items.blaze_rod), new ItemStack(ItemsMT.rune, 1, 4), 2400, 0);
		addRecipe(new ItemStack(Items.magma_cream), new ItemStack(ItemsMT.rune, 1, 5), 1800, 0);
		addRecipe(new ItemStack(Items.ender_eye), new ItemStack(ItemsMT.rune, 1, 6), 2400, 0);
		for(int i = 0; i < runeRecipes.size(); i++) {
			RunicRecipe recipe = runeRecipes.get(i);
			System.out.println(recipe.getEnergyRequired());
		}
	}	
	
	public static void addRecipe(ItemStack catalyst, ItemStack output, int energy, int purity) {
		RunicRecipe recipe = new RunicRecipe(catalyst, output, energy, purity);
		runeRecipes.add(recipe);
	}
	
	public static ItemStack getResult(ItemStack catalyst) {
		for(RunicRecipe recipe : runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getOutput().copy();
			}
		}
		return null;
	}
	
	public static int getMacht(ItemStack catalyst) {
		for(RunicRecipe recipe : runeRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getEnergyRequired();
			}
		}
		return 0;
	}
	
	public static int getPurity(ItemStack catalyst) {
		for(RunicRecipe recipe : runeRecipes) {
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
		for(RunicRecipe recipe : runeRecipes) {
			if(recipe.getOutput().isItemEqual(output)) {
				return recipe.getCatalyst().copy();
			}
		}
		return null;
	}	
	
	public static int getMachtFromOutput(ItemStack output) {
		for(RunicRecipe recipe : runeRecipes) {
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
