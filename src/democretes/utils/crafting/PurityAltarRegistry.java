package democretes.utils.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.api.recipe.PurityRecipe;
import democretes.item.MTItems;

public class PurityAltarRegistry {
	
	public static PurityRecipe ingotLight;
	public static PurityRecipe ingotDark;
	

	public static void initPurityRecipes() {
		ingotLight = new PurityRecipe(new ItemStack(Items.iron_ingot), new ItemStack(MTItems.ingot, 1, 7), false);
		ingotDark = new PurityRecipe(new ItemStack(Items.iron_ingot), new ItemStack(MTItems.ingot, 1, 8), true);
	}
	
}
