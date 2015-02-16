package democretes.item.armor.pure;

import net.minecraft.util.IIcon;
import democretes.lib.ItemNames;

public class ItemPureLegs extends ItemPureArmor {

	public ItemPureLegs() {
		super(2, "pure.legs");
	}

	
	@Override
	public IIcon getIconFromDamage(int i) {
		return icons[2];
	}
}
