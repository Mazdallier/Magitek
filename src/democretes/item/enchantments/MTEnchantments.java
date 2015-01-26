package democretes.item.enchantments;

import net.minecraft.enchantment.Enchantment;


public class MTEnchantments {
	
	public static void init() {
		EnchantmentDevour devour = new EnchantmentDevour(getAvailableID(), 2);
	}
	
	public static int getAvailableID() {
		int i = 0;
		for(; i < Enchantment.enchantmentsList.length; i++) {
			if(Enchantment.enchantmentsList[i] == null) {
				return i;
			}
		}
		return i;
	}

}
