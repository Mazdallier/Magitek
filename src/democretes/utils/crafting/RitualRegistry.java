package democretes.utils.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.api.RitualType;
import democretes.api.recipe.RitualRecipe;
import democretes.block.MTBlocks;
import democretes.item.MTItems;

public class RitualRegistry {
	
	public static RitualRecipe sigilAdvanced;
	public static RitualRecipe sigilComplex;
	public static RitualRecipe crystal;
	public static RitualRecipe sigilPower;
	public static RitualRecipe sigilPurity;
	public static RitualRecipe sigilDeactivate;	
	public static RitualRecipe coreAdvanced;	
	public static RitualRecipe coreComplex;
	public static RitualRecipe sigilDivination;
	public static RitualRecipe sigilAbilities;
	
	public static void initRitualRecipes() {
		sigilAdvanced = new RitualRecipe(new ItemStack(MTItems.ritualSigil, 1, 0), RitualType.BASIC, new ItemStack[] {
			new ItemStack(Items.diamond), new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.glowstone_dust)}, 
			new ItemStack(MTItems.ritualSigil, 1, 1), 10000);
		sigilComplex = new RitualRecipe(new ItemStack(MTItems.ritualSigil, 1, 1), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(Blocks.diamond_block), new ItemStack(MTBlocks.simple, 1, 0), new ItemStack(Items.ender_eye), new ItemStack(Items.blaze_rod), new ItemStack(Items.ghast_tear)}, 
			new ItemStack(MTItems.ritualSigil, 1, 2), 25000);
		crystal = new RitualRecipe(new ItemStack(MTBlocks.generator, 1, 7), RitualType.BASIC, new ItemStack[] {
			new ItemStack(Blocks.quartz_block), new ItemStack(Items.ender_pearl), new ItemStack(Items.dye, 1, 4)}, 
			new ItemStack(MTBlocks.generator, 1, 3), 5000);
		sigilDeactivate = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.rune, 1, 6), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.divinationSigil), 5000);	
		coreAdvanced = new RitualRecipe(new ItemStack(MTItems.material, 1, 4), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.ender_pearl)}, 
			new ItemStack(MTItems.material, 3, 5), 15000);	
		coreAdvanced = new RitualRecipe(new ItemStack(MTItems.material, 1, 5), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.ender_pearl), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 4)}, 
			new ItemStack(MTItems.material, 3, 6), 30000);
	}


}
