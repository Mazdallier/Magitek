package democretes.utils.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.block.MTBlocks;
import democretes.item.MTItems;

public final class RecipeRegistry {
	
	public static void initAllTheRecipes() {
		RunicRegistry.initRunicRecipes();
		AltarRegistry.initAltarRecipes();
		RitualRegistry.initRitualRecipes();
		PurityAltarRegistry.initPurityRecipes();
		ReconstructorRegistry.initReconstructorRecipes();
		initShapedRecipes();
		initShapelessRecipes();
		if(Loader.isModLoaded("Baubles")) {
			initBaubleRecipes();		
		}
	}	
	
	public static void initShapedRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.simple, 1, 0), 
				"III", "III", "III",
				'I', new ItemStack(MTItems.material, 1, 3));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.rune,1, 5), 
				"WAF",
				'A', new ItemStack(MTItems.rune, 1, 3),
				'W', new ItemStack(MTItems.rune, 1, 1),
				'F', new ItemStack(MTItems.rune, 1, 0));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.rune, 1, 5), 
				"EWA",
				'A', new ItemStack(MTItems.rune, 1, 3),
				'W', new ItemStack(MTItems.rune, 1, 1),
				'E', new ItemStack(MTItems.rune, 1, 2));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.rune,1, 5), 
				"FAW",
				'A', new ItemStack(MTItems.rune, 1, 3),
				'W', new ItemStack(MTItems.rune, 1, 1),
				'F', new ItemStack(MTItems.rune, 1, 0));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.rune, 1, 5), 
				"AWE",
				'A', new ItemStack(MTItems.rune, 1, 3),
				'W', new ItemStack(MTItems.rune, 1, 1),
				'E', new ItemStack(MTItems.rune, 1, 2));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.rune, 2, 6),
				" A ", "WBF", " E ",
				'A', new ItemStack(MTItems.rune, 1, 3),
				'W', new ItemStack(MTItems.rune, 1, 1),
				'B', new ItemStack(MTItems.material, 1, 0),
				'F', new ItemStack(MTItems.rune, 1, 0),
				'E', new ItemStack(MTItems.rune, 1, 2));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.material, 1, 4), 
				" L ", "RDR", " L ",
				'L', new ItemStack(Items.dye, 1, 4),
				'R', new ItemStack(Items.redstone),
				'D', new ItemStack(Items.gold_ingot));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.material, 1, 4), 
				" R ", "LDL", " R ",
				'L', new ItemStack(Items.dye, 1, 4),
				'R', new ItemStack(Items.redstone),
				'D', new ItemStack(Items.gold_ingot));
		addOreDictRecipe(new ItemStack(MTBlocks.generator, 1, 0), 
				"PPP", "WCW", "SSS",
				'P', "paneGlass",
				'W', "plankWood",
				'C', new ItemStack(MTItems.material, 1, 4),
				'S', "stone");
		addOreDictRecipe(new ItemStack(MTBlocks.generator, 1, 1), 
				"GSG", "GCG", "III",
				'G', "blockGlass",
				'I', "ingotIron",
				'C', new ItemStack(MTItems.material, 1, 4),
				'S', "stone");
		addOreDictRecipe(new ItemStack(MTBlocks.generator, 1, 2), 
				" S ", "GCG", "ISI",
				'G', "blockGlass",
				'I', "ingotIron",
				'C', new ItemStack(MTItems.material, 1, 4),
				'S', "stone");
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.generator, 1, 4), 
				" I ", "ICI", "IMI",
				'I', new ItemStack(MTBlocks.simple, 1, 1),
				'C', new ItemStack(MTItems.material, 1, 4),
				'M', new ItemStack(MTItems.material, 1, 3));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.generator, 1, 5), 
				"O O", "IOI", "OCO",
				'O', new ItemStack(Blocks.obsidian),
				'I', new ItemStack(MTItems.material, 1, 3),
				'C', new ItemStack(MTItems.material, 1, 4));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.generator, 1, 6), 
				"ICI", "CAC", "ICI",
				'I', new ItemStack(MTItems.material, 1, 3),
				'A', new ItemStack(MTItems.material, 1, 5),
				'C', new ItemStack(MTItems.material, 1, 6));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.generator, 1, 7), 
				"ICI", "CAC", "ICI",
				'I', new ItemStack(MTItems.material, 1, 3),
				'A', new ItemStack(MTItems.material, 1, 5),
				'C', new ItemStack(MTItems.material, 1, 6));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.generator, 1, 8), 
				"   ", " C ", "ITI",
				'T', new ItemStack(MTBlocks.transfer),
				'I', new ItemStack(MTItems.material, 1, 3),
				'C', new ItemStack(MTItems.material, 1, 5));
		addOreDictRecipe(new ItemStack(MTBlocks.altar),
				"SSS", " T ", " T ", 
				'S', new ItemStack(Blocks.stone_slab),
				'T', "stone");
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.machine, 1, 0), 
				" C ", "DID", "III",
				'I', new ItemStack(MTBlocks.simple, 1, 1),
				'C', new ItemStack(MTItems.material, 1, 4),
				'D', new ItemStack(Items.diamond));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.machine, 1, 1), 
				"BIB", "ITI", "BCB",
				'B', new ItemStack(MTBlocks.simple, 1, 1),
				'C', new ItemStack(MTItems.material, 1, 6),
				'T', new ItemStack(Blocks.crafting_table),
				'I', new ItemStack(MTItems.material, 1, 3));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.machine, 1, 2), 
				" I ", "ICI", "IMI",
				'I', new ItemStack(MTBlocks.simple, 1, 1),
				'C', new ItemStack(MTItems.material, 1, 4),
				'M', new ItemStack(MTItems.material, 1, 3));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.disposable, 1, 0), 
				"ITI", "TCT", "ITI",
				'I', new ItemStack(MTBlocks.simple, 1, 1),
				'T', new ItemStack(Blocks.tnt),
				'C', new ItemStack(MTItems.material, 1, 5));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.transfer), 
				"   ", " H ", "BCB",
				'C', new ItemStack(MTItems.material, 1, 4),
				'H', new ItemStack(Blocks.hopper),
				'B', new ItemStack(MTBlocks.simple, 1, 1));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkBoots),
				"   ", "I I", "I I",
				'I', new ItemStack(MTItems.material, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkBoots),
				"I I", "I I", "   ", 
				'I', new ItemStack(MTItems.material, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkHelmet),
				"III", "I I", "   ", 
				'I', new ItemStack(MTItems.material, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkHelmet),
				 "   ", "III", "I I",
				'I', new ItemStack(MTItems.material, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkLeggings),
				 "III", "I I", "I I",
				'I', new ItemStack(MTItems.material, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkChestplate),
				 "I I", "III", "III",
				'I', new ItemStack(MTItems.material, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureBoots),
				"   ", "I I", "I I",
				'I', new ItemStack(MTItems.material, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureBoots),
				"I I", "I I", "   ", 
				'I', new ItemStack(MTItems.material, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureHelmet),
				"III", "I I", "   ", 
				'I', new ItemStack(MTItems.material, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureHelmet),
				 "   ", "III", "I I",
				'I', new ItemStack(MTItems.material, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureLeggings),
				 "III", "I I", "I I",
				'I', new ItemStack(MTItems.material, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureChestplate),
				 "I I", "III", "III",
				'I', new ItemStack(MTItems.material, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.totemEnhancer),
				 "III", "SCS", "SSS",
				'S', new ItemStack(MTBlocks.simple, 1, 1),
				'C', new ItemStack(MTItems.material, 1, 5),
				'I', new ItemStack(MTItems.material, 1, 3));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.totemVision),
				 "SSS", "SRS", "SSS",
				'R', new ItemStack(Items.redstone),
				'S', new ItemStack(MTBlocks.simple, 1, 1));
	}
	
	public static void initShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.material, 9, 3), new ItemStack(MTBlocks.simple, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.material, 1, 1), new ItemStack(MTItems.material, 1, 0), new ItemStack(MTItems.material, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 0), new ItemStack(MTItems.material, 1, 0), new ItemStack(Items.coal));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 1), new ItemStack(MTItems.material, 1, 0), new ItemStack(Items.water_bucket), new ItemStack(Items.apple));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 2), new ItemStack(MTItems.material, 1, 0), new ItemStack(Blocks.dirt), new ItemStack(Items.clay_ball));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 3), new ItemStack(MTItems.material, 1, 0), new ItemStack(Items.feather));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 4), new ItemStack(MTItems.material, 1, 0), new ItemStack(MTItems.rune, 1, 0), new ItemStack(MTItems.rune, 1, 0), new ItemStack(MTItems.rune, 1, 0), new ItemStack(MTItems.rune, 1, 0));
	}
	public static void initBaubleRecipes() {
		
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
	
}
