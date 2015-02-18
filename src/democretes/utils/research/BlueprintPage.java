package democretes.utils.research;

import net.minecraft.item.ItemStack;
import democretes.api.recipe.AltarRecipe;
import democretes.api.recipe.PurityRecipe;
import democretes.api.recipe.RitualRecipe;
import democretes.api.recipe.RuneRecipe;

public class BlueprintPage {
	
	public String text;
	public AltarRecipe recipeA;
	public RitualRecipe recipeB;
	public RuneRecipe recipeC;
	public ItemStack[] recipeD;
	
	
	public BlueprintPage(String text) {
		this.text = text;
	}
	
	public BlueprintPage(AltarRecipe recipe) {
		this.recipeA = recipe;
	}

	public BlueprintPage(RitualRecipe recipe) {
		this.recipeB = recipe;
	}
	
	public BlueprintPage(RuneRecipe recipe) {
		this.recipeC = recipe;
	}
	
	//Crafting recipe
	public BlueprintPage(ItemStack[] recipe) {
		this.recipeD = recipe;		
	}
	

}
