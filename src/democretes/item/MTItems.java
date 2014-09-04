package democretes.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.item.baubles.ItemMachtRing;
import democretes.item.baubles.ItemPurityRing;
import democretes.item.simple.ItemMaterial;
import democretes.item.simple.ItemRune;
import democretes.item.spells.ItemSpellBinder;
import democretes.item.tools.ItemCoilLinker;
import democretes.item.tools.ItemDeactivatorSigil;
import democretes.item.tools.ItemDebugger;
import democretes.item.tools.ItemDivinationSigil;
import democretes.item.tools.ItemEnhancingSigil;
import democretes.item.tools.ItemMachtSigil;
import democretes.item.tools.ItemPuritySigil;
import democretes.item.tools.ItemRitualSigil;
import democretes.lib.ItemNames;

public class MTItems {

	public static Item material;
	public static Item rune;
	public static Item debugger;
	public static Item link;
	public static Item binder;
	public static Item machtSigil;
	public static Item puritySigil;
	public static Item ritualSigil;
	public static Item enhanceSigil;
	public static Item deactivator;
	public static Item divinationSigil;
	
	public static void init() {
		material = new ItemMaterial();
		debugger = new ItemDebugger();
		link = new ItemCoilLinker();
		binder = new ItemSpellBinder();
		rune = new ItemRune();
		machtSigil = new ItemMachtSigil();
		puritySigil = new ItemPuritySigil();
		ritualSigil = new ItemRitualSigil();
		enhanceSigil = new ItemEnhancingSigil();
		deactivator = new ItemDeactivatorSigil();
		divinationSigil = new ItemDivinationSigil();

		GameRegistry.registerItem(material, ItemNames.MATERIAL_NAME);
		GameRegistry.registerItem(debugger, ItemNames.DEBUGGER_NAME);
		//GameRegistry.registerItem(link, ItemNames.LINKER_NAME);
		GameRegistry.registerItem(binder, ItemNames.SPELL_BINDER_NAME);
		GameRegistry.registerItem(rune, ItemNames.RUNE_NAME);
		GameRegistry.registerItem(puritySigil, ItemNames.PURITY_RUNE_NAME);
		GameRegistry.registerItem(machtSigil, ItemNames.MACHT_RUNE_NAME);
		GameRegistry.registerItem(ritualSigil, ItemNames.RITUAL_NAME);
		GameRegistry.registerItem(enhanceSigil, ItemNames.ENHANCE_SIGIL_NAME);
		GameRegistry.registerItem(deactivator, ItemNames.DEACTIVATOR_SIGIL_NAME);
		GameRegistry.registerItem(divinationSigil, ItemNames.DIVINATION_SIGIL_NAME);

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
