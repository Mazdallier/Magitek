package democretes.utils.helper;

import java.util.List;

import democretes.Magitek;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

public class RecipeHelper {
	//This class exists because Mojang code is pure garbage.
	
	
	//All of this to get a recipe -_-
	public static ItemStack[] getOrientatedRecipe(ItemStack stack) {
		List recipes = CraftingManager.getInstance().getRecipeList();
		ShapelessRecipes shapeless = null;
		ShapedRecipes shaped = null;
		for(Object o : recipes) {
			if(o instanceof ShapedRecipes) {
				if(((ShapedRecipes)o).getRecipeOutput().getItem() == stack.getItem() && ((ShapedRecipes)o).getRecipeOutput().getItemDamage() == stack.getItemDamage()) {
					shaped = (ShapedRecipes)o;
					break;
				}
			}else if(o instanceof ShapelessRecipes) {
				if(((ShapelessRecipes)o).getRecipeOutput().getItem() == stack.getItem() && ((ShapelessRecipes)o).getRecipeOutput().getItemDamage() == stack.getItemDamage()) {
					shapeless = (ShapelessRecipes)o;
					break;
				}
			}
		}
		if(shaped != null) {
			return shaped.recipeItems;
		}else if(shapeless != null) {
			return (ItemStack[])shapeless.recipeItems.toArray();
		}
		return null;
	}


}
