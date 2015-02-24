package democretes.item.simple;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;

public class ItemChipset extends ItemMTBase {
	
	public ItemChipset() {
		setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	IIcon icons[] = new IIcon[6];	
	@Override
	public void registerIcons(IIconRegister ir) {
		String pre = Reference.TEXTURE_PREFIX + "chipset/chipset_";
		icons[0] = ir.registerIcon(pre + "iron");
		icons[1] = ir.registerIcon(pre + "gold");
		icons[2] = ir.registerIcon(pre + "rustic");
		icons[3] = ir.registerIcon(pre + "flimsy");
		icons[4] = ir.registerIcon(pre + "pure");
		icons[5] = ir.registerIcon(pre + "dark");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack) {
		return "item." + Reference.MOD_PREFIX + ".chip." + stack.getItemDamage();
	}

}
