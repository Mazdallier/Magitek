package democretes.utils.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.api.RitualType;
import democretes.api.recipe.RitualRecipe;
import democretes.api.spells.SpellHelper;
import democretes.block.MTBlocks;
import democretes.item.MTItems;
import democretes.item.spells.SpellsMT;

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
		sigilPower = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.rune, 1, 4), new ItemStack(MTItems.rune, 1, 4), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.machtSigil), 5000);
		sigilPurity = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 5)},
			new ItemStack(MTItems.machtSigil), 5000);
		sigilDeactivate = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.rune, 1, 6), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.divinationSigil), 5000);
		sigilAbilities = new RitualRecipe(new ItemStack(MTItems.material, 1, 1), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(Items.blaze_powder), new ItemStack(Items.fermented_spider_eye), new ItemStack(Items.sugar), new ItemStack(Items.magma_cream), new ItemStack(Items.fish), 
			new ItemStack(Items.ghast_tear), new ItemStack(Items.speckled_melon), new ItemStack(Items.golden_apple), new ItemStack(Items.ender_pearl), new ItemStack(Items.golden_carrot)},
			new ItemStack(MTItems.enhanceSigil), 75000);	
		coreAdvanced = new RitualRecipe(new ItemStack(MTItems.material, 1, 4), RitualType.BASIC, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.ender_pearl)}, 
			new ItemStack(MTItems.material, 3, 5), 15000);	
		coreAdvanced = new RitualRecipe(new ItemStack(MTItems.material, 1, 5), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.ender_pearl), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 4)}, 
			new ItemStack(MTItems.material, 3, 6), 30000);
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
	

	
	public static RitualRecipe purityRing1;
	public static RitualRecipe purityRing2;
	public static RitualRecipe purityRing4;
	public static RitualRecipe purityRing5;
	public static RitualRecipe machtRing1;
	public static RitualRecipe machtRing2;
	
	public static void initBaubleRecipes() {
		purityRing1 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 0), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.emerald), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6)},
			new ItemStack(MTItems.purityRing, 1, 1), 30000);
		purityRing2 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 1), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.emerald_block), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6),
			new ItemStack(Items.nether_star), new ItemStack(MTItems.puritySigil), new ItemStack(Items.emerald), new ItemStack(Items.ender_eye), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.purityRing, 1, 2), 100000);
		
		purityRing4 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 3), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(Items.fermented_spider_eye), new ItemStack(Items.diamond), new ItemStack(Items.ghast_tear), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6)},
			new ItemStack(MTItems.purityRing, 1, 4), 30000);
		purityRing5 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 1), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(Items.fermented_spider_eye), new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.emerald_block), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6),
			new ItemStack(Items.nether_star), new ItemStack(MTItems.puritySigil), new ItemStack(Items.bone), new ItemStack(Items.ender_eye), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.purityRing, 1, 5), 100000);

		purityRing1 = new RitualRecipe(new ItemStack(MTItems.machtRing, 1, 0), RitualType.ADVANCED, new ItemStack[] {
			new ItemStack(MTItems.material, 1, 3), new ItemStack(Items.diamond), new ItemStack(Items.emerald), new ItemStack(MTItems.rune, 1, 4), new ItemStack(MTItems.rune, 1, 6)},
			new ItemStack(MTItems.machtRing, 1, 1), 35000);	
		purityRing5 = new RitualRecipe(new ItemStack(MTItems.purityRing, 1, 1), RitualType.COMPLEX, new ItemStack[] {
			new ItemStack(MTBlocks.simple, 1, 0), new ItemStack(Blocks.diamond_block), new ItemStack(Blocks.emerald_block), new ItemStack(MTItems.rune, 1, 5), new ItemStack(MTItems.rune, 1, 6),
			new ItemStack(Items.nether_star), new ItemStack(MTItems.machtSigil), new ItemStack(Items.diamond_sword), new ItemStack(Items.ender_eye), new ItemStack(MTItems.rune, 1, 4)},
			new ItemStack(MTItems.machtRing, 1, 2), 150000);
	}

}
