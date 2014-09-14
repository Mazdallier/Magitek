package democretes.utils.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import democretes.api.helpers.RitualType;
import democretes.api.recipe.AltarRecipe;
import democretes.api.recipe.RitualRecipe;
import democretes.api.recipe.RuneRecipe;
import democretes.api.spells.Spell;
import democretes.api.spells.SpellHelper;
import democretes.block.MTBlocks;
import democretes.item.MTItems;
import democretes.item.spells.SpellsMT;

public final class RecipeRegistry {
	
	public static void initAllTheRecipes() {
		RunicRegistry.initRunicRecipes();
		AltarRegistry.initAltarRecipes();
		RitualRegistry.initRitualRecipes();
		RitualRegistry.initSpellRecipes();
		ReconstructorRegistry.initReconstructorRecipes();
		initShapedRecipes();
		initShapelessRecipes();
		initBaubleRecipes();
		RitualRegistry.initBaubleRecipes();
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
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.material, 3, 4), 
				" L ", "RDR", " L ",
				'L', new ItemStack(Items.dye, 1, 4),
				'R', new ItemStack(Items.redstone),
				'D', new ItemStack(Items.gold_ingot));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.material, 3, 4), 
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
	}
	
	public static void initShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.material, 9, 3), new ItemStack(MTBlocks.simple, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.material, 1, 1), new ItemStack(MTItems.material, 1, 0), new ItemStack(MTItems.material, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 0), new ItemStack(MTItems.material, 1, 0), new ItemStack(Items.coal));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 1), new ItemStack(MTItems.material, 1, 0), new ItemStack(Items.water_bucket), new ItemStack(Items.apple));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 2), new ItemStack(MTItems.material, 1, 0), new ItemStack(Blocks.dirt), new ItemStack(Items.clay_ball));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 3), new ItemStack(MTItems.material, 1, 0), new ItemStack(Items.feather));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.rune, 1, 4), new ItemStack(MTItems.material, 1, 0), new ItemStack(MTItems.rune, 1, 0), new ItemStack(MTItems.rune, 1, 0), new ItemStack(MTItems.rune, 1, 0), new ItemStack(MTItems.rune, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.machtSigil), new ItemStack(MTItems.puritySigil));
		GameRegistry.addShapelessRecipe(new ItemStack(MTItems.puritySigil), new ItemStack(MTItems.machtSigil));
	}
	public static void initBaubleRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.purityRing, 1, 0), 
				" C ", "MRM", " M ",
				'C',new ItemStack(MTItems.material, 1, 5),
				'R', new ItemStack(MTItems.rune, 1, 5),
				'M', new ItemStack(MTItems.material, 1, 3));
		
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.purityRing, 1, 3), 
				" C ", "MRM", " M ",
				'C',new ItemStack(MTItems.material, 1, 5),
				'R', new ItemStack(MTItems.rune, 1, 6),
				'M', new ItemStack(Items.iron_ingot));
		
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.machtRing, 1, 0), 
				" C ", "MRM", " M ",
				'C',new ItemStack(MTItems.material, 1, 5),
				'R', new ItemStack(MTItems.rune, 1, 4),
				'M', new ItemStack(MTItems.material, 1, 3));
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
	
}
