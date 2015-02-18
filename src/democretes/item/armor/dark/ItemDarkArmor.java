package democretes.item.armor.dark;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import democretes.Magitek;
import democretes.api.MagitekApi;
import democretes.lib.Reference;

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

}
