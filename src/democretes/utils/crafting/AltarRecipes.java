package democretes.utils.crafting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import democretes.item.ItemsMT;

public class AltarRecipes {
	
static List<AltarRecipe> altarRecipes = new LinkedList();
	
	public static class AltarRecipe {
		static ItemStack input;
		static ItemStack output;
		static int macht;
		
		AltarRecipe(ItemStack input, ItemStack output, int macht) {
			this.input = input;
			this.output = output;
			this.macht = macht;
		}
		
		public static ItemStack getInput() {
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
		addRecipe(new ItemStack(Blocks.stone), new ItemStack(ItemsMT.material, 1, 0), 500);
	}	
	
	public static void addRecipe(ItemStack input, ItemStack output, int energy) {
		altarRecipes.add(new AltarRecipe(input, output, energy));
	}
	
	public static ItemStack getResult(ItemStack input) {
		for(AltarRecipe recipe : altarRecipes) {
			if(recipe.getInput().isItemEqual(input)) {
				return recipe.getOutput().copy();
			}
		}
		return null;
	}
	
	public static int getMacht(ItemStack input) {
		for(AltarRecipe recipe : altarRecipes) {
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
