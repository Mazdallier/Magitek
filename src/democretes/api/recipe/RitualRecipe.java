package democretes.api.recipe;

import java.util.LinkedList;

import net.minecraft.item.ItemStack;
import democretes.api.RitualType;

public class RitualRecipe {
	ItemStack catalyst;
	RitualType type;
	ItemStack[] input;
	ItemStack output;
	int macht;
	
	public RitualRecipe(ItemStack catalyst, RitualType type, ItemStack[] input, ItemStack output, int macht) {
		this.catalyst = catalyst;
		this.type = type;
		this.input = input;
		this.output = output;
		this.macht = macht;
		ritualRecipes.add(this);
	}
	
	public ItemStack getCatalyst() {
		return catalyst;
	}
	
	public RitualType getRitual() {
		return type;
	}
	
	public ItemStack[] getInput() {
		return input;
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public int getEnergyRequired() {
		return macht;
	}	
	
	public static LinkedList<RitualRecipe> ritualRecipes = new LinkedList<RitualRecipe>();

}


