package democretes.block.coils;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;
import democretes.lib.RenderIds;

public class BlockCoil extends BlockMTBase {	

	@Override
	public String getUnlocalizedName() {
		return "tile." + Reference.MOD_PREFIX + ".coil";
	}
}
