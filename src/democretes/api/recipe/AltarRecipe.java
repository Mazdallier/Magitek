package democretes.api.recipe;

import java.util.LinkedList;

import net.minecraft.item.ItemStack;

public class AltarRecipe {
	ItemStack input;
	ItemStack output;
	int macht;
	
	public AltarRecipe(ItemStack input, ItemStack output, int macht) {
		this.input = input;
		this.output = output;
		this.macht = macht;
		altarRecipes.add(this);
	}
	
	public ItemStack getInput() {
		return input;
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public int getEnergyRequired() {
		return macht;
	}	
	
	public static LinkedList<AltarRecipe> altarRecipes = new LinkedList<AltarRecipe>();

}
