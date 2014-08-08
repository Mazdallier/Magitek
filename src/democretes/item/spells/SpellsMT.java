package democretes.item.spells;

import democretes.api.spells.ISpellActivation;
import democretes.api.spells.Spell;

public class SpellsMT {
	
	public static Spell blink;
	public static Spell invisible;
	
	public static void init() {
		blink = new Spell("blink", 100, 0, new SpellBlink());
		invisible = new Spell("invisibility", 500, 0, new SpellInvisible());
	}

}
