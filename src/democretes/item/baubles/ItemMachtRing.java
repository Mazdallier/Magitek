package democretes.item.baubles;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import baubles.api.BaubleType;
import baubles.api.IBauble;
import democretes.api.spells.SpellHelper;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;
import democretes.utils.handlers.ConfigHandler;

public class ItemMachtRing extends ItemMTBase implements IBauble {

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.RING;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < rings.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	int count;
	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		count++;
		if(count >= 100/(stack.getItemDamage()+1) && SpellHelper.getMacht((EntityPlayer)player) < (SpellHelper.getMax((EntityPlayer)player)*ConfigHandler.maxRatio)/100) {
			SpellHelper.receiveMacht((EntityPlayer)player, 10*(stack.getItemDamage()+1));
			count = 0;
		}		
	}

	@Override
	public void onEquipped(ItemStack stack, EntityLivingBase player) {}

	@Override
	public void onUnequipped(ItemStack stack, EntityLivingBase player) {}

	@Override
	public boolean canEquip(ItemStack stack, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
		return true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Reference.MOD_PREFIX + ".ring.macht." + stack.getItemDamage();
	}
	
	IIcon[] rings = new IIcon[3];
	
	@Override
	public void registerIcons(IIconRegister ir) {
		for(int i = 0; i < rings.length; i++) {
			rings[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "baubles/ring_macht_" + i);
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return rings[meta];
	}
}
