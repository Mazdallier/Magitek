package democretes.item.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import democretes.api.spells.ISpellActivation;
import democretes.utils.helper.StringHelper;

public class SpellGodMode implements ISpellActivation {

	@Override
	public void activateSpell(EntityPlayer player) {
		for(int i = 0; i < player.worldObj.playerEntities.size(); i++) {
			EntityPlayer p = (EntityPlayer)player.worldObj.playerEntities.get(i);
			if(!player.worldObj.isRemote) {
				p.addChatMessage(new ChatComponentText(player.getDisplayName() + " " + StringHelper.localize("magitek.spell.godMode")));
			}
		}
		player.setAbsorptionAmount(20);
		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2400, 10));
		player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 2400, 10));
		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 2400, 10));
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 10));
		player.addPotionEffect(new PotionEffect(Potion.jump.id, 2400, 10));
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2400, 10));
		player.addPotionEffect(new PotionEffect(Potion.heal.id, 2400, 10));
		player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 2400, 10));
		player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 2400, 10));
	}

}
