package democretes.item.armor.pure;

import net.minecraft.util.IIcon;
import democretes.lib.ItemNames;

public class ItemPureHelmet extends ItemPureArmor {

	public ItemPureHelmet() {
		super(0, "pure.helmet");
	}

	
	@Override
	public IIcon getIconFromDamage(int i) {
		return icons[0];
	}
	
}
