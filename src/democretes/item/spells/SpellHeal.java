package democretes.item.spells;

import net.minecraft.entity.player.EntityPlayer;
import democretes.api.spells.ISpellActivation;

public class SpellHeal implements ISpellActivation{

	@Override
	public void activateSpell(EntityPlayer player) {
		player.heal(5);
	}

}
