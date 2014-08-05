package democretes.item;

import cpw.mods.fml.common.registry.GameRegistry;
import democretes.lib.ItemNames;
import net.minecraft.item.Item;

public class ItemsMT {
	
	public static Item debugger;
	public static Item syphon;
	
	public static void init() {
		debugger = new ItemDebugger();
		syphon = new ItemPuritySyphon();
		
		GameRegistry.registerItem(debugger, ItemNames.DEBUGGER_NAME);
		GameRegistry.registerItem(syphon, ItemNames.SYPHON_NAME);
		
	}

}
