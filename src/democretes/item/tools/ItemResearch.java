package democretes.item.tools;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import democretes.Magitek;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;

public class ItemResearch extends ItemMTBase {
	
	public ItemResearch() {
		setUnlocalizedName(Reference.MOD_PREFIX + ".tome");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,	EntityPlayer player) {
		if(player!= null) {
			player.openGui(Magitek.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		return super.onItemRightClick(stack, world, player);
	}

	IIcon icon;
	@Override
	public void registerIcons(IIconRegister ir) {
		icon = ir.registerIcon(Reference.TEXTURE_PREFIX + "tome");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icon;
	}

}
