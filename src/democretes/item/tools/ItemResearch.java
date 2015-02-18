package democretes.item.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import democretes.Magitek;
import democretes.item.ItemMTBase;

public class ItemResearch extends ItemMTBase {
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,	EntityPlayer player) {
		if(player!= null) {
			player.openGui(Magitek.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		return super.onItemRightClick(stack, world, player);
	}


}
