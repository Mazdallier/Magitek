package democretes.item.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class EnchantmentDevour extends Enchantment {
	
	private static final int baseEnchantability = 1;	
    private static final int levelEnchantability = 8;

	protected EnchantmentDevour(int id, int weight) {
		super(id, weight, EnumEnchantmentType.weapon);
	}
	
	@Override
	public int getMinEnchantability(int level) {
        return baseEnchantability + (level - 1) * levelEnchantability;
    }

	@Override
    public int getMaxEnchantability(int level) {
        return this.getMinEnchantability(level) + 20;
    }

	@Override
    public int getMaxLevel() {
        return 5;
    }
    
	@Override
    public String getName() {
        return "enchantment.devour";
    }
	
	@Override
	public void func_151368_a(EntityLivingBase player, Entity entity, int level) {
        if (entity instanceof EntityPlayer) {
        	((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.hunger.id, 200*level, 5));
        }
    }

}
