package democretes.item;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.api.purity.IPurityHandler;
import democretes.api.purity.PurityHelper;
import democretes.lib.Reference;

public class ItemPuritySyphon extends ItemMTBase {
	
	public ItemPuritySyphon() {
		setMaxStackSize(1);
		setUnlocalizedName(Reference.MOD_PREFIX + ".syphon");
	}
		
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return false;
		}
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof IPurityHandler) {
			IPurityHandler ptile = (IPurityHandler)tile;
			int amount = 10;
			if(player.isSneaking()) {
				int purity = PurityHelper.getPlayerPurity(player);
				amount = Math.min(Math.abs(purity), 10);
				if(purity < 0) {
					ptile.decreasePurity(amount);
					PurityHelper.increasePlayerPurity(player, amount);						
				}else if(purity > 0) {
					ptile.increasePurity(amount);
					PurityHelper.decreasePlayerPurity(player, amount);
				}
				return false;
			}else{ 
				amount = Math.min(Math.abs(ptile.getPurity()), 10);
				if(ptile.getPurity() < 0) {
					ptile.increasePurity(amount);
					PurityHelper.decreasePlayerPurity(player, amount);
				}else if(ptile.getPurity() > 0) {
					ptile.decreasePurity(amount);
					PurityHelper.increasePlayerPurity(player, amount);				
				}
			}
		}
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		int purity = PurityHelper.getPlayerPurity(player);
		list.add("Your Purity: " + purity);
		if(purity <= -1000) {
			list.add("You soul has become dark.");
		}else if(purity >= 1000) {
			list.add("You soul has been lightened.");
		}
	}

}
