package democretes.item.spells;

import net.minecraft.entity.player.EntityPlayer;
import democretes.api.spells.ISpellActivation;

public class SpellBlink implements ISpellActivation {

	@Override
	public void activateSpell(EntityPlayer player) {
		player.motionY += 100;
	}

}
