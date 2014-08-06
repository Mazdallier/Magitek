package democretes.item;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import democretes.Magitek;
import democretes.api.block.BlockInfo;
import democretes.api.block.IBlockDebug;
import democretes.lib.Reference;

public class ItemDebugger extends ItemMTBase {
	
	public ItemDebugger() {
		setUnlocalizedName(Reference.MOD_PREFIX + ".debugger");
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		if(block instanceof IBlockDebug && !world.isRemote) {
			BlockInfo info = ((IBlockDebug)block).getInfo(player, x, y, z);
			FMLLog.info("Block Name: " + block.getLocalizedName());
			FMLLog.info("X Choord: " + info.getX());
			FMLLog.info("Y Choord: " + info.getY());
			FMLLog.info("Z Choord: " + info.getZ());
			FMLLog.info("Player Name: " + info.getPlayer().getDisplayName());
			if(info.isMachtHandler()) {
				FMLLog.info("Macht Stored: " + info.getMacht());
			}
			if(info.isPurityHandler()) {
				FMLLog.info("Purity: " + info.getPurity());
			}
		}
		return false;
	}
	
}
