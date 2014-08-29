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

public class ItemPurityRing extends ItemMTBase implements IBauble {

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
		if(stack.getItemDamage() < 3) {
			if(count >= 100/(stack.getItemDamage()+1)) {
				SpellHelper.increasePlayerPurity((EntityPlayer)player, 1*(stack.getItemDamage()+1));
				count = 0;
				return;
			}
		}
		if(stack.getItemDamage() >= 3) {
			if(count >= 100/(stack.getItemDamage())) {
				SpellHelper.decreasePlayerPurity((EntityPlayer)player, 1*(stack.getItemDamage()-1));
				count = 0;
				return;
			}
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
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		if(stack.getItemDamage() == 5) {
			list.add("One ring to rule them all.");			
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = "ring";
		if(stack.getItemDamage() < 3) {			
			name += ".pure.";
		}else{
			name += ".impure.";
		}
		return "item." + Reference.MOD_PREFIX + "." + name + stack.getItemDamage();
	}
	
	IIcon[] rings = new IIcon[6];
	@Override
	public void registerIcons(IIconRegister ir) {
		for(int i = 0; i < 3; i++) {
			rings[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "baubles/ring_pure_" + i);
		}
		for(int i = 3; i < 6; i++) {
			rings[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "baubles/ring_impure_" + (i-3));
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return rings[meta];
	}

}
