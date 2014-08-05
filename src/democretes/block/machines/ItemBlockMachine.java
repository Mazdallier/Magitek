package democretes.block.machines;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import democretes.lib.Reference;

public class ItemBlockMachine extends ItemBlockWithMetadata{
	
	public ItemBlockMachine(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + Reference.MOD_PREFIX + ".machine." + stack.getItemDamage();
	}

}
