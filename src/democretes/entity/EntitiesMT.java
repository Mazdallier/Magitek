package democretes.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import democretes.Magitek;

public class EntitiesMT {

	public static void init() {
		int id = 0;
		EntityRegistry.registerModEntity(EntityWindBlast.class, "WindBlast", id++, Magitek.instance, 128, 10, true);
	}
}
