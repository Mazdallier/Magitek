package democretes.utils.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.api.recipe.ReconstructorRecipe;

public class ReconstructorRegistry {
	
	public static ReconstructorRecipe iron;
	
	public static void initReconstructorRecipes() {
		iron = new ReconstructorRecipe(new ItemStack(Items.iron_ingot), 5000);
	}

}
