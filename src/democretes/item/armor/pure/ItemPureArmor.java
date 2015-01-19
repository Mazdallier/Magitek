package democretes.item.armor.pure;

import democretes.Magitek;
import democretes.api.MagitekApi;
import democretes.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

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
	

}
