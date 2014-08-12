package democretes.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.item.baubles.ItemBauble;
import democretes.item.spells.ItemSpellBinder;
import democretes.item.tools.ItemDebugger;
import democretes.item.tools.ItemMachtSigil;
import democretes.item.tools.ItemCoilLinker;
import democretes.item.tools.ItemPuritySigil;
import democretes.lib.ItemNames;

public class ItemsMT {
	
	public static Item debugger;
	public static Item purityRune;
	public static Item link;
	public static Item binder;
	public static Item machtRune;
	public static Item material;
	
	public static void init() {
		debugger = new ItemDebugger();
		purityRune = new ItemPuritySigil();
		link = new ItemCoilLinker();
		binder = new ItemSpellBinder();
		machtRune = new ItemMachtSigil();
		material = new ItemMaterial();
		
		GameRegistry.registerItem(debugger, ItemNames.DEBUGGER_NAME);
		GameRegistry.registerItem(purityRune, ItemNames.PURITY_RUNE_NAME);
		GameRegistry.registerItem(machtRune, ItemNames.MACHT_RUNE_NAME);
		GameRegistry.registerItem(link, ItemNames.LINKER_NAME);
		GameRegistry.registerItem(binder, ItemNames.SPELL_BINDER_NAME);
		GameRegistry.registerItem(material, ItemNames.MATERIAL_NAME);

		if(Loader.isModLoaded("Baubles")) {
			initBaubles();
		}
	}
	
	public static Item bauble;
	
	public static void initBaubles() {
		bauble = new ItemBauble();
		
		GameRegistry.registerItem(bauble, ItemNames.RING_NAME);
	}

}
