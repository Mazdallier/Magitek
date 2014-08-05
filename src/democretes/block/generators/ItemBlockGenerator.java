package democretes.block.generators;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import democretes.lib.Reference;

public class ItemBlockGenerator extends ItemBlockWithMetadata{

	public ItemBlockGenerator(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + Reference.MOD_PREFIX + ".generator." + stack.getItemDamage();
	}
}
