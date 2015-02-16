package democretes.item.armor.pure;

import net.minecraft.util.IIcon;
import democretes.lib.ItemNames;

public class ItemPureBoots extends ItemPureArmor {

	public ItemPureBoots() {
		super(3, "pure.boots");
	}

	
	@Override
	public IIcon getIconFromDamage(int i) {
		return icons[3];
	}
}
