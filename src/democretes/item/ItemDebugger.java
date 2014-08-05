package democretes.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import democretes.api.block.BlockInfo;
import democretes.api.block.IBlockDebug;
import democretes.lib.Reference;

public class ItemDebugger extends ItemEMBase {

	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		if(block instanceof IBlockDebug && !world.isRemote) {
			BlockInfo info = ((IBlockDebug)block).getInfo(player, x, y, z);
			FMLLog.info(Reference.MOD_NAME, "X Choord: " + info.getX());
			FMLLog.info(Reference.MOD_NAME, "Y Choord: " + info.getY());
			FMLLog.info(Reference.MOD_NAME, "Z Choord: " + info.getZ());
			FMLLog.info(Reference.MOD_NAME, "Player Name: " + info.getPlayer().getDisplayName());
			if(info.isMachtHandler()) {
				FMLLog.info(Reference.MOD_NAME, "Macht Stored: " + info.getMacht());
			}
		}
		return false;
	}
}
