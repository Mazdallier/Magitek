package democretes.block.nodes;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import democretes.lib.Reference;

public class ItemBlockNode extends ItemBlockWithMetadata{
	
	public ItemBlockNode(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + Reference.MOD_PREFIX + ".node." + stack.getItemDamage();
	}
}
