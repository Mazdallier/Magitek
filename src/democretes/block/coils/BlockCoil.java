package democretes.block.coils;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import democretes.block.BlockMTBase;
import democretes.lib.RenderIds;

public class BlockCoil extends BlockMTBase {
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 2; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.idNode;
	}
}
