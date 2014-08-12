package democretes.utils.crafting;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.item.ItemStack;
import democretes.api.altar.RitualType;

public class RitualRecipes {

	static List<RitualRecipe> ritualRecipes = new LinkedList();

	
	public static class RitualRecipe {
		static ItemStack catalyst;
		static RitualType type;
		static ItemStack[] input;
		static ItemStack output;
		static int macht;
		
		RitualRecipe(ItemStack catalyst, RitualType type, ItemStack[] input, ItemStack output, int macht) {
			this.catalyst = catalyst;
			this.type = type;
			this.input = input;
			this.output = output;
			this.macht = macht;
		}
		
		public static ItemStack getCatalyst() {
			return catalyst;
		}
		
		public static RitualType getRitual() {
			return type;
		}
		
		public static ItemStack[] getInput() {
			return input;
		}
		
		public static ItemStack getOutput() {
			return output;
		}
		
		public static int getEnergyRequired() {
			return macht;
		}	
	}
	
	public static void initAltarRecipes() {

	}	
	
	
	
	public static void addRecipe(ItemStack catalyst, RitualType type, ItemStack[] input, ItemStack output, int macht) {
		if(type.size != input.length) {
			return;
		}
		ritualRecipes.add(new RitualRecipe(catalyst, type, input, output, macht));
	}

	public static RitualRecipe getRecipe(ItemStack catalyst) {
		for(RitualRecipe recipe : ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe;
			}
		}
		return null;
	}
	
	public static ItemStack[] getInputForCatalyst(ItemStack catalyst) {
		for(RitualRecipe recipe : ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getInput();
			}
		}
		return null;
	}
	
	public static ItemStack getOutputForCatalyst(ItemStack catalyst) {
		for(RitualRecipe recipe : ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getOutput();
			}
		}
		return null;
	}
	
	public static RitualType getTypeForCatalyst(ItemStack catalyst) {
		for(RitualRecipe recipe : ritualRecipes) {
			if(recipe.getCatalyst().isItemEqual(catalyst)) {
				return recipe.getRitual();
			}
		}
		return null;
	}
	
	public static int getMachtForCatalyst(ItemStack catalyst) {
		for(RitualRecipe recipe : ritualRecipes) {
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
				if(stacks[i].isItemEqual(recipe[j])) {
					check++;
				}
			}
		}
		return check == recipe.length;
	}
	
}
