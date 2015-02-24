package democretes.block.circuits;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import democretes.lib.Reference;
import democretes.utils.helper.StringHelper;

public class ItemBlockCircuit extends ItemBlockWithMetadata {

	public ItemBlockCircuit(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + Reference.MOD_PREFIX + ".circuit." + stack.getItemDamage();
	}
	
}
