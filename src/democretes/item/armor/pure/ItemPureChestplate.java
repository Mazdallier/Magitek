package democretes.item.armor.pure;

import net.minecraft.util.IIcon;
import democretes.lib.ItemNames;

public class ItemPureChestplate extends ItemPureArmor {

	public ItemPureChestplate() {
		super(1, "pure.chestplate");
	}

	
	@Override
	public IIcon getIconFromDamage(int i) {
		return icons[1];
	}
}
