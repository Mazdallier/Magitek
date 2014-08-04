package democretes.item;

import cpw.mods.fml.common.registry.GameRegistry;
import democretes.lib.ItemNames;
import net.minecraft.item.Item;

public class ItemsTR {
	
	public static Item debugger;
	
	public static void init() {
		debugger = new ItemDebugger();
		
		GameRegistry.registerItem(debugger, ItemNames.DEBUGGER_NAME);
		
	}

}
