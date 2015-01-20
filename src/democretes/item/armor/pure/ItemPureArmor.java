package democretes.item.armor.pure;

import democretes.Magitek;
import democretes.api.MagitekApi;
import democretes.api.spells.SpellHelper;
import democretes.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class ItemPureArmor extends ItemArmor {

	public ItemPureArmor(int type, String name) {
		super(MagitekApi.pureArmorMaterial, 0, type);
		setUnlocalizedName(name);
		setCreativeTab(Magitek.tabMT);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return slot == 2 ? Reference.ARMOR_PURE_2 :  Reference.ARMOR_PURE_1 ;
	}
	
	IIcon[] icons = new IIcon[4];
	@Override
	public void registerIcons(IIconRegister ir) {
		String pre = Reference.TEXTURE_PREFIX + "pure";
		icons[0] = ir.registerIcon(pre + "Helmet");
		icons[1] = ir.registerIcon(pre + "Chestplate");
		icons[2] = ir.registerIcon(pre + "Legs");
		icons[3] = ir.registerIcon(pre + "Boots");		
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return icons[((ItemArmor)stack.getItem()).armorType];
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if(SpellHelper.getPlayerPurity(player) < 0) {
			return;
		}
		int count = 2000;
		if(count%2000 == 0) {
			int tier = SpellHelper.getTier(player);
			if(stack.stackTagCompound != null) {
				stack.stackTagCompound.setTag("ench", new NBTTagList());				
			}
			switch(tier) {
			case 1:
				stack.addEnchantment(Enchantment.protection, tier);break;
			case 2:
				stack.addEnchantment(Enchantment.protection, tier + 1);
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
				stack.addEnchantment(Enchantment.protection, tier + 2);	
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
