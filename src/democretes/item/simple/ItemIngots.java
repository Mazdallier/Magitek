package democretes.item.simple;

import java.util.List;

import democretes.item.ItemMTBase;
import democretes.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemIngots extends ItemMTBase {
	
	public ItemIngots() {
		setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	IIcon icons[] = new IIcon[4];
	
	@Override
	public void registerIcons(IIconRegister ir) {
		String pre = Reference.TEXTURE_PREFIX + "ingots/";
		icons[0] = ir.registerIcon(pre + "rustic_ingot");
		icons[1] = ir.registerIcon(pre + "flimsy_ingot");
		icons[2] = ir.registerIcon(pre + "dark_ingot");
		icons[3] = ir.registerIcon(pre + "pure_ingot");
		
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack) {
		return "item." + Reference.MOD_PREFIX + ".ingot." + stack.getItemDamage();
	}
	
}
