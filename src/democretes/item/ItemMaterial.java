package democretes.item;

import java.util.List;

import democretes.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemMaterial extends ItemMTBase {
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	IIcon icons[] = new IIcon[3];
	
	@Override
	public void registerIcons(IIconRegister ir) {
		icons[0] = ir.registerIcon(Reference.TEXTURE_PREFIX + "rune");
		icons[1] = ir.registerIcon(Reference.TEXTURE_PREFIX + "sigil");
		icons[2] = ir.registerIcon(Reference.TEXTURE_PREFIX + "scroll");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack) {
		return "item." + Reference.MOD_PREFIX + ".material." + stack.getItemDamage();
	}
	
}
