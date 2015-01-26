package democretes.api.recipe;

import java.util.LinkedList;

import net.minecraft.item.ItemStack;

public class PurityRecipe {
	
	ItemStack input; 
	ItemStack output; 
	boolean isDark;
	
	public PurityRecipe(ItemStack input, ItemStack output, boolean isDark) {
		this.input = input;
		this.output = output;
		this.isDark = isDark;
		altarPurityRecipes.add(this);
	}
	
	public ItemStack getInput() {
		return input;
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public boolean isDark() {
		return isDark;
	}	
	
	public static LinkedList<PurityRecipe> altarPurityRecipes = new LinkedList<PurityRecipe>();

}
