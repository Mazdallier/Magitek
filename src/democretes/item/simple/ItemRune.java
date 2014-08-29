package democretes.item.simple;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;

public class ItemRune extends ItemMTBase {
	
	public ItemRune() {
		setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	IIcon icons[] = new IIcon[7];
	
	@Override
	public void registerIcons(IIconRegister ir) {
		icons[0] = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune/rune_fire");
		icons[1] = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune/rune_water");
		icons[2] = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune/rune_earth");
		icons[3] = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune/rune_air");
		icons[4] = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune/rune_energy");
		icons[5] = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune/rune_balance");
		icons[6] = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune/rune_control");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack) {
		return "item." + Reference.MOD_PREFIX + ".rune." + stack.getItemDamage();
	}

}
