package democretes.item.armor.dark;

import net.minecraft.util.IIcon;
import democretes.lib.ItemNames;

public class ItemDarkBoots extends ItemDarkArmor {

	public ItemDarkBoots() {
		super(3, "dark.boots");
	}
	
	@Override
	public IIcon getIconFromDamage(int i) {
		return icons[3];
	}

}
