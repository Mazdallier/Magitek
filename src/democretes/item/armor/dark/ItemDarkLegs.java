package democretes.item.armor.dark;

import net.minecraft.util.IIcon;
import democretes.lib.ItemNames;

public class ItemDarkLegs extends ItemDarkArmor {

	public ItemDarkLegs() {
		super(2, "dark.legs");
	}
	
	@Override
	public IIcon getIconFromDamage(int i) {
		return icons[2];
	}
	
}
