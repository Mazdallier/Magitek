package democretes.api.spells;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class SpellHelper {
	
	public static SpellNetwork getNetwork(EntityPlayer player) {
		String name = player.getDisplayName() + "MagitekNetwork";
		World world = MinecraftServer.getServer().worldServers[0];
		SpellNetwork network = (SpellNetwork)world.loadItemData(SpellNetwork.class, name);
        if(network == null) {
        	network = new SpellNetwork(name);
        	player.worldObj.setItemData(name, network);
        }
		return network;
	}
	
	public static void bindSpell(EntityPlayer player, Spell spell) {
		SpellNetwork network = getNetwork(player);
		network.spell = spell;
		network.markDirty();
	}
	
	public static Spell getSpell(String name) {
		return Spell.spells.get(name);
	}
	
	public static int getPlayerPurity(EntityPlayer player) {
		SpellNetwork network = getNetwork(player);
		return network.purity;
	}
	
	public static void setPlayerPurity(EntityPlayer player, int purity) {
		SpellNetwork network = getNetwork(player);
        network.purity = purity;
        setPurityTier(network);
        network.markDirty();
	}
	
	public static void increasePlayerPurity(EntityPlayer player, int purity) {
		SpellNetwork network = getNetwork(player);
        network.purity += purity;
        setPurityTier(network);
        network.markDirty();
	}
	
	public static void decreasePlayerPurity(EntityPlayer player, int purity) {
		SpellNetwork network = getNetwork(player);
        network.purity -= purity;
        network.markDirty();
	}	

	public static void setPurityTier(SpellNetwork network) {
		if(Math.abs(network.purity) >= 50000) {
			network.tier = 3;
		}else if(Math.abs(network.purity) >= 25000 && network.tier < 2) {
			network.tier = 2;
		}else if(Math.abs(network.purity) >= 1000 && network.tier < 1) {
			network.tier = 1;
		}
	}
	
	public static int getTier(EntityPlayer player) {
		return getNetwork(player).tier;
	}
	
	public static int getMacht(EntityPlayer player) {
		SpellNetwork network = getNetwork(player);
		return network.macht;
	}
	
	public static int extractMacht(EntityPlayer player, int amount) {
		SpellNetwork network = getNetwork(player);
		if(network.macht < amount) {
			int energy = network.macht;
			network.macht = 0;
			return energy;
		}
		network.macht -= amount;
		network.markDirty();
		return amount;
	}
	
	public static int receiveMacht(EntityPlayer player, int amount) {
		SpellNetwork network = getNetwork(player);
		network.macht += amount;
		network.markDirty();
		return amount;
	}
	
	public static boolean isSupercharged(EntityPlayer player) {
		return getMacht(player) > getMax(player);
	}
	
	public static int getMax(EntityPlayer player) {
		return getNetwork(player).max;
	}
	
	public static void setMax(EntityPlayer player, int max) {
		SpellNetwork network = getNetwork(player);
		network.max = max;
		network.markDirty();
	}
	
	public static int getSpellIndex(Spell spell) {
		Set<String> strings = Spell.spells.keySet();
		int i = 0;
		for(String string : strings) {	
			if(string.equals(spell.name)) {
				return i;
			}
			i++;
		}
		return 0;
	}
	

}
