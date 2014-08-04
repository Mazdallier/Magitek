package democretes.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabsEM extends CreativeTabs {

	public CreativeTabsEM(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	public Item getTabIconItem() {
		return Items.gold_ingot;
	}
	
	

}
