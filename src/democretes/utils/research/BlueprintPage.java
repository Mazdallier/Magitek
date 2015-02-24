package democretes.utils.research;

import net.minecraft.item.ItemStack;
import democretes.api.recipe.ChipCrafterRecipe;
import democretes.api.recipe.RitualRecipe;

public class BlueprintPage {
	
	public String text;
	public RitualRecipe recipeA;
	public ChipCrafterRecipe recipeB;
	public ItemStack recipeC;
	
	
	public BlueprintPage(String text) {
		this.text = text;
	}

	public BlueprintPage(RitualRecipe recipe) {
		this.recipeA = recipe;
	}
	
	public BlueprintPage(ChipCrafterRecipe recipe) {
		this.recipeB = recipe;
	}
	
	//Crafting recipe
	public BlueprintPage(ItemStack result) {
		this.recipeC = result;	
	}
	

}
