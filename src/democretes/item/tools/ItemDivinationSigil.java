package democretes.item.tools;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import democretes.api.block.BlockInfo;
import democretes.api.block.IBlockDebug;
import democretes.api.macht.IMachtHandler;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;
import democretes.utils.helper.StringHelper;

public class ItemDivinationSigil extends ItemMTBase {
	
	public ItemDivinationSigil() {
		setUnlocalizedName(Reference.MOD_PREFIX + ".sigil.divination");
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		if(block instanceof IBlockDebug && !world.isRemote) {
			BlockInfo info = ((IBlockDebug)block).getInfo(player, x, y, z);
			ChatComponentText text = new ChatComponentText("");
			if(info.isMachtHandler()) {
				text.appendText(StringHelper.localize("magitek.macht.stored") + ": " + info.getMacht());
			}
			if(info.isPurityHandler()) {
				text.appendText(", " + StringHelper.localize("magitek.purity.stored") + ": " + info.getPurity());
			}
			player.addChatComponentMessage(text);
		}
		return false;
	}
	
	IIcon sigil;
	@Override
	public void registerIcons(IIconRegister ir) {
		sigil = ir.registerIcon(Reference.TEXTURE_PREFIX + "sigil/sigil_divination");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return sigil;
	}

}
