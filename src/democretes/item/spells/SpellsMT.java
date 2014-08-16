package democretes.item.spells;

import democretes.api.spells.Spell;

public class SpellsMT {
	
	public static Spell blink;
	public static Spell invisible;
	public static Spell heal;
	public static Spell immortality;
	public static Spell wind;
	
	public static void init() {
		blink = new Spell("blink", 1000, 0, new SpellBlink());
		invisible = new Spell("invisibility", 2000, 0, new SpellInvisible());
		heal = new Spell("heal", 500, 10, new SpellHeal());
		immortality = new Spell("immortality", 50000, 2000, new SpellGodMode());
		wind = new Spell("wind blast", 5000, -100, new SpellWindBlast());
	}

}
