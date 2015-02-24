package democretes.utils.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.block.MTBlocks;
import democretes.item.MTItems;

public final class RecipeRegistry {
	
	public static void initAllTheRecipes() {
		RunicRegistry.initRunicRecipes();
		PurityAltarRegistry.initPurityRecipes();
		ReconstructorRegistry.initReconstructorRecipes();
		initShapedRecipes();
		initShapelessRecipes();
		
		OreDictionary.registerOre("ingotCopper", new ItemStack(MTItems.ingot, 1, 0));
		OreDictionary.registerOre("ingotTin", new ItemStack(MTItems.ingot, 1, 1));
		OreDictionary.registerOre("ingotLead", new ItemStack(MTItems.ingot, 1, 2));
		OreDictionary.registerOre("ingotSilver", new ItemStack(MTItems.ingot, 1, 3));
	}	
	
	public static void initShapedRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.machine, 1, 2), 
				"SIS", "I I", "SCS",
				'I', new ItemStack(Items.iron_ingot),
				'S', new ItemStack(Blocks.stone),
				'C', new ItemStack(MTItems.chipset, 1, 0));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkBoots),
				"   ", "I I", "I I",
				'I', new ItemStack(MTItems.ingot, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkBoots),
				"I I", "I I", "   ", 
				'I', new ItemStack(MTItems.ingot, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkHelmet),
				"III", "I I", "   ", 
				'I', new ItemStack(MTItems.ingot, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkHelmet),
				 "   ", "III", "I I",
				'I', new ItemStack(MTItems.ingot, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkLeggings),
				 "III", "I I", "I I",
				'I', new ItemStack(MTItems.ingot, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.darkChestplate),
				 "I I", "III", "III",
				'I', new ItemStack(MTItems.ingot, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureBoots),
				"   ", "I I", "I I",
				'I', new ItemStack(MTItems.ingot, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureBoots),
				"I I", "I I", "   ", 
				'I', new ItemStack(MTItems.ingot, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureHelmet),
				"III", "I I", "   ", 
				'I', new ItemStack(MTItems.ingot, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureHelmet),
				 "   ", "III", "I I",
				'I', new ItemStack(MTItems.ingot, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureLeggings),
				 "III", "I I", "I I",
				'I', new ItemStack(MTItems.ingot, 1, 8));
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.pureChestplate),
				 "I I", "III", "III",
				'I', new ItemStack(MTItems.ingot, 1, 8));
	}
	
	public static void initShapelessRecipes() {
		
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
	
}
