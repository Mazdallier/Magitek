package democretes.item.sigils;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import democretes.api.spells.SpellHelper;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;

public class ItemEnhancingSigil extends ItemMTBase {
	
	public ItemEnhancingSigil() {
		setHasSubtypes(true);
		setMaxStackSize(1);
		setUnlocalizedName(Reference.MOD_PREFIX + ".sigil.enhance");
	}

	IIcon sigil[] = new IIcon[SigilEffects.values().length];
	@Override
	public void registerIcons(IIconRegister ir) {
		for(int i = 0; i < SigilEffects.values().length; i++) {
			sigil[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "sigil/sigil_enhance_" + i);
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return sigil[meta];
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		if(stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
		}
		int macht = SpellHelper.getMacht(player);
		list.add("Your Macht: " + macht);
		stack.stackTagCompound.setInteger("Purity", macht);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(stack == null || player == null) {
			return null;
		}
		if(player.isSneaking()) {
			int damage = stack.getItemDamage();
			if(damage < SigilEffects.values().length-1) {
				stack.setItemDamage(damage + 1);
			}else{
				stack.setItemDamage(0);
			}
		}else{
			if(SpellHelper.getMacht(player) > 2500) {
				SpellHelper.extractMacht(player, 2500);
				player.addPotionEffect(new PotionEffect(SigilEffects.values()[stack.getItemDamage()].potionId, 2400, 2));
			}
		}
		return stack;
	}
	
	public enum SigilEffects {

		STRENGTH(Potion.damageBoost.id),
		JUMP(Potion.jump.id),  
		SPEED(Potion.moveSpeed.id),
		DEFENSE(Potion.resistance.id), 
		MINING(Potion.digSpeed.id),
		BREATH(Potion.waterBreathing.id);
		
		public int potionId;
		SigilEffects(int id) {
			this.potionId = id;
		}

	}
	
}
