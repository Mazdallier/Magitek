package democretes.item.armor.dark;

import democretes.Magitek;
import democretes.api.MagitekApi;
import democretes.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

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
		String pre = Reference.TEXTURE_PREFIX + "dark";
		icons[0] = ir.registerIcon(pre + "Helmet");
		icons[1] = ir.registerIcon(pre + "Chestplate");
		icons[2] = ir.registerIcon(pre + "Legs");
		icons[3] = ir.registerIcon(pre + "Boots");		
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return icons[((ItemArmor)stack.getItem()).armorType];
	}

}
