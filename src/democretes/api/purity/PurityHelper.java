package democretes.api.purity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PurityHelper {
	
	public static PurityNetwork getNetwork(EntityPlayer player) {
		String name = player.getDisplayName();
		World world = MinecraftServer.getServer().worldServers[0];
		PurityNetwork network = (PurityNetwork)world.loadItemData(PurityNetwork.class, name);
        if(network == null) {
        	network = new PurityNetwork(name);
        	player.worldObj.setItemData(name, network);
        }
		return network;
	}
	
	public static int getPlayerPurity(EntityPlayer player) {
		PurityNetwork network = getNetwork(player);
		return network.purity;
	}
	
	public static void setPlayerPurity(EntityPlayer player, int purity) {
		PurityNetwork network = getNetwork(player);
        network.purity = purity;
        network.markDirty();
	}
	
	public static void increasePlayerPurity(EntityPlayer player, int purity) {
		PurityNetwork network = getNetwork(player);
        network.purity += purity;
        network.markDirty();
	}
	
	public static void decreasePlayerPurity(EntityPlayer player, int purity) {
		PurityNetwork network = getNetwork(player);
        network.purity -= purity;
        network.markDirty();
	}

}
