package democretes.item.baubles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import democretes.api.spells.SpellHelper;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;

public class ItemBauble extends ItemMTBase implements IBauble {

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.RING;
	}

	int count;
	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		count++;
		if(stack.getItemDamage() == 0) {
			if(count == 200) {
				SpellHelper.increasePlayerPurity((EntityPlayer)player, 1);
				count = 0;
				return;
			}
		}
		if(stack.getItemDamage() == 1) {
			if(count == 200) {
				SpellHelper.decreasePlayerPurity((EntityPlayer)player, 1);
				count = 0;
				return;
			}
		}
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canEquip(ItemStack stack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = "ring";
		switch(stack.getItemDamage()) {
		case 0:
			name = "pure";break;
		case 1:
			name = "impure";break;
		}
		return Reference.MOD_PREFIX + "." + name;
	}

}
