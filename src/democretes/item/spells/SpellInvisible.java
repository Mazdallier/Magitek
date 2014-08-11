package democretes.item.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import democretes.api.spells.ISpellActivation;
import democretes.api.spells.SpellHelper;

public class SpellInvisible implements ISpellActivation {

	@Override
	public void activateSpell(EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 1200));
	}

}
