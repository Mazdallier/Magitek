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
import democretes.item.sigils.ItemDivinationSigil;
import democretes.item.simple.ItemChipset;
import democretes.item.simple.ItemIngots;
import democretes.item.tools.ItemDebugger;
import democretes.item.tools.ItemResearch;
import democretes.lib.ItemNames;

public class MTItems {

	public static Item ingot;
	public static Item chipset;
	public static Item debugger;
	public static Item divinationSigil;
	public static Item research;
	
	public static Item pureHelmet;
	public static Item pureChestplate;
	public static Item pureLeggings;
	public static Item pureBoots;
	
	public static Item darkHelmet;
	public static Item darkChestplate;
	public static Item darkLeggings;
	public static Item darkBoots;
	
	public static void init() {
		ingot = new ItemIngots();
		debugger = new ItemDebugger();
		chipset = new ItemChipset();
		divinationSigil = new ItemDivinationSigil();
		research = new ItemResearch();
		
		pureHelmet = new ItemPureHelmet();
		pureChestplate = new ItemPureChestplate();
		pureLeggings = new ItemPureLegs();
		pureBoots = new ItemPureBoots();
		
		darkHelmet = new ItemDarkHelmet();
		darkChestplate = new ItemDarkChestplate();
		darkLeggings = new ItemDarkLegs();
		darkBoots = new ItemDarkBoots();

		GameRegistry.registerItem(ingot, ItemNames.MATERIAL_NAME);
		GameRegistry.registerItem(debugger, ItemNames.DEBUGGER_NAME);
		GameRegistry.registerItem(chipset, ItemNames.CHIPSET_NAME);
		GameRegistry.registerItem(divinationSigil, ItemNames.DIVINATION_SIGIL_NAME);
		GameRegistry.registerItem(research, ItemNames.RESEARCH_NAME);
		
		GameRegistry.registerItem(pureHelmet, ItemNames.PURE_HELMET_NAME);
		GameRegistry.registerItem(pureChestplate, ItemNames.PURE_CHEST_NAME);
		GameRegistry.registerItem(pureLeggings, ItemNames.PURE_LEGS_NAME);
		GameRegistry.registerItem(pureBoots, ItemNames.PURE_BOOTS_NAME);
		
		GameRegistry.registerItem(darkHelmet, ItemNames.DARK_HELMET_NAME);
		GameRegistry.registerItem(darkChestplate, ItemNames.DARK_CHEST_NAME);
		GameRegistry.registerItem(darkLeggings, ItemNames.DARK_LEGS_NAME);
		GameRegistry.registerItem(darkBoots, ItemNames.DARK_BOOTS_NAME);

		if(Loader.isModLoaded("Baubles")) {
		}
	}
	

}
