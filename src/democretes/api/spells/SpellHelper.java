package democretes.api.spells;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class SpellHelper {
	
	public static SpellNetwork getNetwork(EntityPlayer player) {
		String name = player.getDisplayName() + "spell";
		World world = MinecraftServer.getServer().worldServers[0];
		SpellNetwork network = (SpellNetwork)world.loadItemData(SpellNetwork.class, name);
        if(network == null) {
        	network = new SpellNetwork(name);
        	player.worldObj.setItemData(name, network);
        }
		return network;
	}
	
	public static void bindSpell(EntityPlayer player, Spell spell) {
		getNetwork(player).spell = spell;
	}
	
	public static Spell getSpell(String name) {
		return Spell.spells.get(name);
	}

}
