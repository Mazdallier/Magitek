package democretes.block.generators.disposable;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import democretes.lib.Reference;

public class ItemBlockDisposableGenerator extends ItemBlockWithMetadata {
	
	public ItemBlockDisposableGenerator(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + Reference.MOD_PREFIX + ".disposable." + stack.getItemDamage();
	}

}
