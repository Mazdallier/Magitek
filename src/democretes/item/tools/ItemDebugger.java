package democretes.item.tools;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.api.block.BlockInfo;
import democretes.api.block.IBlockDebug;
import democretes.api.macht.IMachtStorage;
import democretes.api.purity.IPurityHandler;
import democretes.item.ItemMTBase;
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
	
	IIcon debug;
	@Override
	public void registerIcons(IIconRegister ir) {
		debug = ir.registerIcon(Reference.TEXTURE_PREFIX + "debugger");
	}
	
	@Override
	public int getRenderPasses(int metadata) {
		return 1;
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return debug;
	}
}
