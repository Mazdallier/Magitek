package democretes.block.simple;


import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;

public class BlockSimple extends BlockMTBase {
	
	public BlockSimple() {
		setResistance(20.0F);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	public static IIcon[] icons = new IIcon[2];
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		icons[0] = ir.registerIcon(Reference.TEXTURE_PREFIX + "stone_infused");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

}
