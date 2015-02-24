package democretes.block.generators;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.lib.Reference;
import democretes.utils.helper.StringHelper;

public class ItemBlockGenerator extends ItemBlockWithMetadata{

	public ItemBlockGenerator(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + Reference.MOD_PREFIX + ".generator." + stack.getItemDamage();
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		if (!world.setBlock(x, y, z, field_150939_a, metadata, 3)) {
	           return false;
	    }
	    if (world.getBlock(x, y, z) == field_150939_a) {
	       field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
	       field_150939_a.onPostBlockPlaced(world, x, y, z, metadata);
	    }
	    return true;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		if(stack.getTagCompound() != null) {
			list.add(StringHelper.localize("magitek.macht.stored") + ": " + stack.getTagCompound().getInteger("Macht"));
		}
	}
}
