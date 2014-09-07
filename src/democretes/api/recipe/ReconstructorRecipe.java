package democretes.api.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ReconstructorRecipe {
	
	
	int energy;
	ItemStack stack;
	public ReconstructorRecipe(ItemStack stack, int energy) {
		this.energy = energy;
		this.stack = stack;
		reconstructorRecipes.add(this);
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public ItemStack getStack() {
		return stack;
	}

	public static List<ReconstructorRecipe> reconstructorRecipes = new ArrayList<ReconstructorRecipe>();

}
