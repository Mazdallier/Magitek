package democretes.item.tools;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import democretes.Magitek;
import democretes.api.block.BlockInfo;
import democretes.api.block.IBlockDebug;
import democretes.api.macht.IMachtHandler;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;
import democretes.utils.world.WorldGenAltar;

public class ItemDebugger extends ItemMTBase {
	
	public ItemDebugger() {
		setUnlocalizedName(Reference.MOD_PREFIX + ".debugger");
		setMaxStackSize(1);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		if(Minecraft.getMinecraft().thePlayer.getDisplayName().equals("Democretes")) {
			list.add(new ItemStack(item, 1, 0));
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);

		Magitek.log.info("Block Name: " + block.getLocalizedName());
		Magitek.log.info("Metadata: " + world.getBlockMetadata(x, y, z));
		Magitek.log.info("X Choord: " + x);
		Magitek.log.info("Y Choord: " + y);
		Magitek.log.info("Z Choord: " + z);
		Magitek.log.info("Player Name: " + player.getDisplayName());
		if(block instanceof IBlockDebug) {
			BlockInfo info = ((IBlockDebug)block).getInfo(player, x, y, z);
			if(player.isSneaking()) {
				if(info.isMachtHandler()) {
					((IMachtHandler)world.getTileEntity(x, y, z)).receiveMacht(1000000000);
				}
			}
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
