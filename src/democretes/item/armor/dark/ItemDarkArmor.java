package democretes.item.armor.dark;

import democretes.Magitek;
import democretes.api.MagitekApi;
import democretes.api.spells.SpellHelper;
import democretes.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemDarkArmor extends ItemArmor {

	public ItemDarkArmor(int type, String name) {
		super(MagitekApi.darkArmorMaterial, 0, type);
		setUnlocalizedName(name);
		setCreativeTab(Magitek.tabMT);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return slot == 2 ? Reference.ARMOR_DARK_2 :  Reference.ARMOR_DARK_1 ;
	}
	
	IIcon[] icons = new IIcon[4];
	@Override
	public void registerIcons(IIconRegister ir) {
		String pre = Reference.TEXTURE_PREFIX + "armor/dark_";
		icons[0] = ir.registerIcon(pre + "helmet");
		icons[1] = ir.registerIcon(pre + "chestplate");
		icons[2] = ir.registerIcon(pre + "leggings");
		icons[3] = ir.registerIcon(pre + "boots");		
	}
	
	int count = 2000;	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if(SpellHelper.getPlayerPurity(player) > 0) {
			return;
		}
		if(count++%2000 == 0) {
			count = 0;
			int tier = SpellHelper.getTier(player);
			if(stack.stackTagCompound != null) {
				stack.stackTagCompound.setTag("ench", new NBTTagList());				
			}
			switch(tier) {
			case 1:
				stack.addEnchantment(Enchantment.thorns, tier);
				stack.addEnchantment(Enchantment.unbreaking, tier);break;
			case 2:
				stack.addEnchantment(Enchantment.thorns, tier + 1);
				stack.addEnchantment(Enchantment.unbreaking, tier);
				switch(stack.getItemDamage()) {
				case 0:
					stack.addEnchantment(Enchantment.aquaAffinity, tier);break;
				case 1:
					stack.addEnchantment(Enchantment.blastProtection, tier);break;
				case 2:
					stack.addEnchantment(Enchantment.fireProtection, tier);break;
				case 3:	
					stack.addEnchantment(Enchantment.featherFalling, tier);
				}
			case 3:
				stack.addEnchantment(Enchantment.thorns, tier + 2);	
				stack.addEnchantment(Enchantment.unbreaking, tier);
				switch(stack.getItemDamage()) {
				case 0:
					stack.addEnchantment(Enchantment.aquaAffinity, tier);
					stack.addEnchantment(Enchantment.respiration, tier);break;
				case 1:
					stack.addEnchantment(Enchantment.blastProtection, tier);break;
				case 2:
					stack.addEnchantment(Enchantment.fireProtection, tier);break;
				case 3:	
					stack.addEnchantment(Enchantment.featherFalling, tier);
				}
			}		
		}
	}

}
