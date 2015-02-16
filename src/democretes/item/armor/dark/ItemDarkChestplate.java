package democretes.item.armor.dark;

import net.minecraft.util.IIcon;
import democretes.lib.ItemNames;

public class ItemDarkChestplate extends ItemDarkArmor {

	public ItemDarkChestplate() {
		super(1, "dark.chestplate");
	}
	
	@Override
	public IIcon getIconFromDamage(int i) {
		return icons[1];
	}
}
