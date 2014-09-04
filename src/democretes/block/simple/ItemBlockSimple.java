package democretes.block.simple;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import democretes.lib.Reference;

public class ItemBlockSimple extends ItemBlockWithMetadata{
	
	public ItemBlockSimple(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + Reference.MOD_PREFIX + ".simple." + stack.getItemDamage();
	}
}
