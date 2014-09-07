package democretes.utils.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.api.recipe.AltarRecipe;
import democretes.block.MTBlocks;
import democretes.item.MTItems;

public class AltarRegistry {

	
	public static AltarRecipe rune;
	public static AltarRecipe brickInfused;
	public static AltarRecipe sigilCrude;
	public static AltarRecipe machtIngot;
	public static AltarRecipe scroll;
	
	public static void initAltarRecipes() {
		rune = new AltarRecipe(new ItemStack(Blocks.stone), new ItemStack(MTItems.material, 1, 0), 250);
		brickInfused = new AltarRecipe(new ItemStack(Blocks.stonebrick), new ItemStack(MTBlocks.simple, 1, 1), 200);
		sigilCrude = new AltarRecipe(new ItemStack(MTItems.material, 1, 1), new ItemStack(MTItems.ritualSigil, 1, 0), 5000);
		machtIngot = new AltarRecipe(new ItemStack(Items.iron_ingot), new ItemStack(MTItems.material, 1, 3), 1000);
		scroll = new AltarRecipe(new ItemStack(Items.paper), new ItemStack(MTItems.material, 1, 2), 1000);
	}
}
