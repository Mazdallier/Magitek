package democretes.utils;

import democretes.api.spells.Spell;
import democretes.block.BlocksMT;
import democretes.item.ItemsMT;
import democretes.item.spells.SpellsMT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabsMT extends CreativeTabs {

	public CreativeTabsMT(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(BlocksMT.altar);
	}
	
	

}
