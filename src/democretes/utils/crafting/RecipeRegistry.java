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
		initRunicRecipes();
		initAltarRecipes();
		initRitualRecipes();
		initSpellRecipes();
		initShapedRecipes();
		initShapelessRecipes();
		initBaubleRecipes();
	}

	public static RuneRecipe fire;
	public static RuneRecipe water;
	public static RuneRecipe earth;
	public static RuneRecipe air;
	public static RuneRecipe energy;
	public static RuneRecipe balance;
	public static RuneRecipe control;

	public static void initRunicRecipes() {
		fire = new RuneRecipe(new ItemStack(Items.blaze_powder), new ItemStack(MTItems.rune, 2, 0), 600, 0);
		water = new RuneRecipe(new ItemStack(Items.dye, 1, 4), new ItemStack(MTItems.rune, 2, 1), 600, 0);
		earth = new RuneRecipe(new ItemStack(Items.clay_ball), new ItemStack(MTItems.rune, 2, 2), 600, 0);
		air = new RuneRecipe(new ItemStack(Items.feather), new ItemStack(MTItems.rune, 2, 3), 600, 0);
		energy = new RuneRecipe(new ItemStack(Items.blaze_rod), new ItemStack(MTItems.rune, 1, 4), 2400, 0);
		balance = new RuneRecipe(new ItemStack(Items.magma_cream), new ItemStack(MTItems.rune, 1, 5), 1800, 0);
		control = new RuneRecipe(new ItemStack(Items.ender_eye), new ItemStack(MTItems.rune, 1, 6), 2400, 0);
	}
	
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

	public static RitualRecipe sigilAdvanced;
	public static RitualRecipe sigilComplex;
	public static RitualRecipe crystal;
	public static RitualRecipe sigilPower;
	public static RitualRecipe sigilPurity;
	public static RitualRecipe sigilDeactivate;	
	public static RitualRecipe coreAdvanced;
	public static RitualRecipe sigilDivination;
	public static RitualRecipe sigilAbilities;
	
	public static void initRitualRecipes() {
		sigilAdvanced = new RitualRecipe(new ItemStack(MTItems.ritualSigil, 1, 0), RitualType.BASIC, new ItemStack[] {
			new ItemStack(Items.diamond), new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.glowstone_dust)}, 
			new ItemStack(MTItems.ritualSigil, 1, 1), 10000);
		sigilComplex = new RitualRecipe(new ItemStack(MTItems.ritualSigil, 1, 1), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(Blocks.diamond_block), new ItemStack(MTBlocks.simple, 1, 0), new ItemStack(Items.ender_eye), new ItemStack(Items.blaze_rod), new ItemStack(Items.ghast_tear)}, 
			new ItemStack(MTItems.ritualSigil, 1, 2), 25000);
		crystal = new RitualRecipe(new ItemStack(Items.diamond), RitualType.BASIC, new ItemStack[] {
			new ItemStack(Blocks.quartz_block), new ItemStack(Items.ender_pearl), new ItemStack(Items.dye, 1, 4)}, 
			new ItemStack(MTBlocks.generator, 1, 4), 5000);
		sigilPower = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.rune, 1, 4), new ItemStack(MTItems.rune, 1, 4), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.machtSigil), 5000);
		sigilPurity = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 5)},
			new ItemStack(MTItems.machtSigil), 5000);
		sigilDeactivate = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.rune, 1, 6), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.divinationSigil), 5000);	
		coreAdvanced = new RitualRecipe(new ItemStack(MTItems.material, 1, 4), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.ender_pearl)}, 
			new ItemStack(MTItems.material, 3, 5), 15000);
		sigilAbilities = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(Items.blaze_powder), new ItemStack(Items.fermented_spider_eye), new ItemStack(Items.sugar), new ItemStack(Items.magma_cream), new ItemStack(Items.fish), 
			new ItemStack(Items.ghast_tear), new ItemStack(Items.speckled_melon), new ItemStack(Items.golden_apple), new ItemStack(Items.ender_pearl), new ItemStack(Items.golden_carrot)},
			new ItemStack(MTItems.enhanceSigil), 75000);
	}
	
	public static RitualRecipe spellInvisible;
	public static RitualRecipe spellHeal;
	public static RitualRecipe spellBlink;
	public static RitualRecipe spellGod;
	
	public static void initSpellRecipes() {
		spellInvisible = new RitualRecipe(new ItemStack(MTItems.material, 1, 2), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(Items.ender_eye),	new ItemStack(MTItems.rune, 1, 6), new ItemStack(Items.golden_carrot), new ItemStack(Items.fermented_spider_eye), new ItemStack(Items.ghast_tear)},
			new ItemStack(MTItems.binder, 1, SpellHelper.getSpellIndex(SpellsMT.invisible)), 10000);
		spellHeal = new RitualRecipe(new ItemStack(MTItems.material, 1, 2), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(Items.speckled_melon), new ItemStack(Items.golden_apple), new ItemStack(Items.magma_cream), new ItemStack(MTItems.rune, 1, 5), new ItemStack(Items.ghast_tear)},
			new ItemStack(MTItems.binder, 1, SpellHelper.getSpellIndex(SpellsMT.heal)), 5000);
		spellBlink = new RitualRecipe(new ItemStack(MTItems.material, 1, 2), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(Items.ender_eye),	new ItemStack(Items.ender_pearl), new ItemStack(MTItems.rune, 1, 4), new ItemStack(Items.emerald), new ItemStack(Items.glowstone_dust)},
			new ItemStack(MTItems.binder, 1, SpellHelper.getSpellIndex(SpellsMT.blink)), 7500);
		spellGod = new RitualRecipe(new ItemStack(MTItems.material, 1, 2), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(Items.nether_star),	new ItemStack(Blocks.dragon_egg), new ItemStack(Blocks.diamond_block), new ItemStack(Items.ghast_tear), new ItemStack(Blocks.emerald_block), new ItemStack(MTItems.material, 1, 5),
			new ItemStack(MTItems.rune, 1, 4),	new ItemStack(MTBlocks.simple, 1, 0), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6)},
			new ItemStack(MTItems.binder, 1, SpellHelper.getSpellIndex(SpellsMT.immortality)), 100000);
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
				" I ", "ICI", "IMI",
				'I', new ItemStack(MTBlocks.simple, 1, 1),
				'C', new ItemStack(MTItems.material, 1, 4),
				'M', new ItemStack(MTItems.material, 1, 3));
		GameRegistry.addShapedRecipe(new ItemStack(MTBlocks.disposable, 1, 0), 
				"ITI", "TCT", "ITI",
				'I', new ItemStack(MTBlocks.simple, 1, 1),
				'T', new ItemStack(Blocks.tnt),
				'C', new ItemStack(MTItems.material, 1, 5));
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
	
	public static RitualRecipe purityRing1;
	public static RitualRecipe purityRing2;
	public static RitualRecipe purityRing4;
	public static RitualRecipe purityRing5;
	public static RitualRecipe machtRing1;
	public static RitualRecipe machtRing2;
	public static void initBaubleRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.purityRing, 1, 0), 
				" C ", "MRM", " M ",
				'C',new ItemStack(MTItems.material, 1, 5),
				'R', new ItemStack(MTItems.rune, 1, 5),
				'M', new ItemStack(MTItems.material, 1, 3));
		purityRing1 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 0), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.emerald), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6)},
			new ItemStack(MTItems.purityRing, 1, 1), 30000);
		purityRing2 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 1), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.emerald_block), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6),
			new ItemStack(Items.nether_star), new ItemStack(MTItems.puritySigil), new ItemStack(Items.emerald), new ItemStack(Items.ender_eye), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.purityRing, 1, 2), 100000);
		
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.purityRing, 1, 3), 
				" C ", "MRM", " M ",
				'C',new ItemStack(MTItems.material, 1, 5),
				'R', new ItemStack(MTItems.rune, 1, 6),
				'M', new ItemStack(Items.iron_ingot));
		purityRing4 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 3), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(Items.fermented_spider_eye), new ItemStack(Items.diamond), new ItemStack(Items.ghast_tear), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6)},
			new ItemStack(MTItems.purityRing, 1, 4), 30000);
		purityRing5 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 1), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(Items.fermented_spider_eye), new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.emerald_block), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6),
			new ItemStack(Items.nether_star), new ItemStack(MTItems.puritySigil), new ItemStack(Items.bone), new ItemStack(Items.ender_eye), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.purityRing, 1, 5), 100000);
		
		GameRegistry.addShapedRecipe(new ItemStack(MTItems.machtRing, 1, 0), 
				" C ", "MRM", " M ",
				'C',new ItemStack(MTItems.material, 1, 5),
				'R', new ItemStack(MTItems.rune, 1, 4),
				'M', new ItemStack(MTItems.material, 1, 3));
		purityRing1 = new RitualRecipe(new ItemStack(MTItems.machtRing, 1, 0), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.emerald), new ItemStack(MTItems.rune, 1, 4), new ItemStack(MTItems.rune, 1, 6)},
			new ItemStack(MTItems.machtRing, 1, 1), 35000);	
		purityRing5 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 1), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(MTBlocks.simple, 1, 0), new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.emerald_block), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6),
			new ItemStack(Items.nether_star), new ItemStack(MTItems.machtSigil), new ItemStack(Items.diamond_sword), new ItemStack(Items.ender_eye), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.machtRing, 1, 2), 150000);
	}
	
	private static void addOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
	}
	
}
