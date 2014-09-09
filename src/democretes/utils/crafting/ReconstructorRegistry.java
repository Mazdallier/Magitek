package democretes.utils.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.api.recipe.ReconstructorRecipe;

public class ReconstructorRegistry {
	
	public static ReconstructorRecipe iron;
	public static ReconstructorRecipe gold;
	public static ReconstructorRecipe diamond;
	public static ReconstructorRecipe cobble;
	public static ReconstructorRecipe stone;
	public static ReconstructorRecipe redstone;
	public static ReconstructorRecipe lapis;
	public static ReconstructorRecipe quartz;
	public static ReconstructorRecipe emerald;
	public static ReconstructorRecipe pearl;
	public static ReconstructorRecipe blaze;
	public static ReconstructorRecipe clay;
	public static ReconstructorRecipe dirt;
	public static ReconstructorRecipe netherrack;
	public static ReconstructorRecipe gravel;
	public static ReconstructorRecipe sand;
	public static ReconstructorRecipe dragon;
	public static ReconstructorRecipe wood;
	public static ReconstructorRecipe mycelium;
	public static ReconstructorRecipe glowstone;
	public static ReconstructorRecipe coal;
	
	public static void initReconstructorRecipes() {
		iron = new ReconstructorRecipe(new ItemStack(Items.iron_ingot), 5000);
		gold = new ReconstructorRecipe(new ItemStack(Items.gold_ingot), 10000);
		diamond = new ReconstructorRecipe(new ItemStack(Items.gold_ingot), 20000);
		cobble = new ReconstructorRecipe(new ItemStack(Blocks.cobblestone), 500);
		stone = new ReconstructorRecipe(new ItemStack(Blocks.stone), 500);
		redstone = new ReconstructorRecipe(new ItemStack(Items.redstone), 7000);
		lapis = new ReconstructorRecipe(new ItemStack(Items.dye, 1, 4), 10000);
		quartz = new ReconstructorRecipe(new ItemStack(Items.quartz), 6500);
		emerald = new ReconstructorRecipe(new ItemStack(Items.emerald), 25000);
		pearl = new ReconstructorRecipe(new ItemStack(Items.ender_pearl), 6000);
		netherrack = new ReconstructorRecipe(new ItemStack(Blocks.netherrack), 500);
		gravel = new ReconstructorRecipe(new ItemStack(Blocks.gravel), 500);
		sand = new ReconstructorRecipe(new ItemStack(Blocks.sand), 500);
		dragon = new ReconstructorRecipe(new ItemStack(Blocks.dragon_egg), 500000);
		blaze = new ReconstructorRecipe(new ItemStack(Items.blaze_rod), 5000);
		clay = new ReconstructorRecipe(new ItemStack(Items.clay_ball), 750);
		wood = new ReconstructorRecipe(new ItemStack(Blocks.log), 1000);
		mycelium = new ReconstructorRecipe(new ItemStack(Blocks.mycelium), 1000);
		glowstone = new ReconstructorRecipe(new ItemStack(Items.glowstone_dust), 6500);
		coal = new ReconstructorRecipe(new ItemStack(Items.coal), 4000);
		
	}

}
