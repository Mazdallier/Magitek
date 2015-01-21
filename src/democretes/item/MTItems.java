package democretes.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.item.armor.dark.ItemDarkBoots;
import democretes.item.armor.dark.ItemDarkChestplate;
import democretes.item.armor.dark.ItemDarkHelmet;
import democretes.item.armor.dark.ItemDarkLegs;
import democretes.item.armor.pure.ItemPureBoots;
import democretes.item.armor.pure.ItemPureChestplate;
import democretes.item.armor.pure.ItemPureHelmet;
import democretes.item.armor.pure.ItemPureLegs;
import democretes.item.baubles.ItemMachtRing;
import democretes.item.baubles.ItemPurityRing;
import democretes.item.sigils.ItemDeactivatorSigil;
import democretes.item.sigils.ItemDivinationSigil;
import democretes.item.sigils.ItemEnhancingSigil;
import democretes.item.sigils.ItemMachtSigil;
import democretes.item.sigils.ItemPuritySigil;
import democretes.item.sigils.ItemRitualSigil;
import democretes.item.simple.ItemMaterial;
import democretes.item.simple.ItemRune;
import democretes.item.spells.ItemSpellBinder;
import democretes.item.tools.ItemDebugger;
import democretes.lib.ItemNames;

public class MTItems {

	public static Item material;
	public static Item rune;
	public static Item debugger;
	public static Item binder;
	public static Item machtSigil;
	public static Item puritySigil;
	public static Item ritualSigil;
	public static Item enhanceSigil;
	public static Item deactivator;
	public static Item divinationSigil;
	
	public static Item pureHelmet;
	public static Item pureChestplate;
	public static Item pureLeggings;
	public static Item pureBoots;
	
	public static Item darkHelmet;
	public static Item darkChestplate;
	public static Item darkLeggings;
	public static Item darkBoots;
	
	public static void init() {
		material = new ItemMaterial();
		debugger = new ItemDebugger();
		binder = new ItemSpellBinder();
		rune = new ItemRune();
		machtSigil = new ItemMachtSigil();
		puritySigil = new ItemPuritySigil();
		ritualSigil = new ItemRitualSigil();
		enhanceSigil = new ItemEnhancingSigil();
		deactivator = new ItemDeactivatorSigil();
		divinationSigil = new ItemDivinationSigil();
		
		pureHelmet = new ItemPureHelmet();
		pureChestplate = new ItemPureChestplate();
		pureLeggings = new ItemPureLegs();
		pureBoots = new ItemPureBoots();
		
		darkHelmet = new ItemDarkHelmet();
		darkChestplate = new ItemDarkChestplate();
		darkLeggings = new ItemDarkLegs();
		darkBoots = new ItemDarkBoots();

		GameRegistry.registerItem(material, ItemNames.MATERIAL_NAME);
		GameRegistry.registerItem(debugger, ItemNames.DEBUGGER_NAME);
		GameRegistry.registerItem(binder, ItemNames.SPELL_BINDER_NAME);
		GameRegistry.registerItem(rune, ItemNames.RUNE_NAME);
		GameRegistry.registerItem(puritySigil, ItemNames.PURITY_RUNE_NAME);
		GameRegistry.registerItem(machtSigil, ItemNames.MACHT_RUNE_NAME);
		GameRegistry.registerItem(ritualSigil, ItemNames.RITUAL_NAME);
		GameRegistry.registerItem(enhanceSigil, ItemNames.ENHANCE_SIGIL_NAME);
		GameRegistry.registerItem(deactivator, ItemNames.DEACTIVATOR_SIGIL_NAME);
		GameRegistry.registerItem(divinationSigil, ItemNames.DIVINATION_SIGIL_NAME);
		
		GameRegistry.registerItem(pureHelmet, ItemNames.PURE_HELMET_NAME);
		GameRegistry.registerItem(pureChestplate, ItemNames.PURE_CHEST_NAME);
		GameRegistry.registerItem(pureLeggings, ItemNames.PURE_LEGS_NAME);
		GameRegistry.registerItem(pureBoots, ItemNames.PURE_BOOTS_NAME);
		
		GameRegistry.registerItem(darkHelmet, ItemNames.DARK_HELMET_NAME);
		GameRegistry.registerItem(darkChestplate, ItemNames.DARK_CHEST_NAME);
		GameRegistry.registerItem(darkLeggings, ItemNames.DARK_LEGS_NAME);
		GameRegistry.registerItem(darkBoots, ItemNames.DARK_BOOTS_NAME);

		if(Loader.isModLoaded("Baubles")) {
			initBaubles();
		}
	}
	
	public static Item purityRing;
	public static Item machtRing;
	
	public static void initBaubles() {
		purityRing = new ItemPurityRing();
		machtRing = new ItemMachtRing();
		
		GameRegistry.registerItem(purityRing, ItemNames.RING_NAME + "Purity");
		GameRegistry.registerItem(machtRing, ItemNames.RING_NAME + "Macht");
	}

}
