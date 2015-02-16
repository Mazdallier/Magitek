package democretes.item.armor.dark;

import net.minecraft.util.IIcon;
import democretes.lib.ItemNames;

public class ItemDarkHelmet extends ItemDarkArmor {

	public ItemDarkHelmet() {
		super(0, "dark.helmet");
	}
	
	@Override
	public IIcon getIconFromDamage(int i) {
		return icons[0];
	}
	
}
