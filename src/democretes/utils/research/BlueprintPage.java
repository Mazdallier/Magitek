package democretes.utils.research;

import net.minecraft.item.ItemStack;
import democretes.api.recipe.ChipCrafterRecipe;

public class BlueprintPage {
	
	public String text;
	public ChipCrafterRecipe recipeA;
	public ItemStack recipeB;
	
	
	public BlueprintPage(String text) {
		this.text = text;
	}
	
	public BlueprintPage(ChipCrafterRecipe recipe) {
		this.recipeA = recipe;
	}
	
	//Crafting recipe
	public BlueprintPage(ItemStack result) {
		this.recipeB = result;	
	}
	

}
