package democretes.utils.research;

import java.util.LinkedList;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import democretes.utils.research.Blueprint.ResearchType;

public class BlueprintHelper {
	
	static LinkedList<Blueprint> blueprints = new LinkedList();
	
	public static LinkedList<Blueprint> getBlueprints() {
		return blueprints;
	}
	
	public static LinkedList<Blueprint> getBlueprintsByType(ResearchType type) {
		LinkedList<Blueprint> list = new LinkedList<Blueprint>();
		for(Blueprint b : blueprints) {
			if(b.type == type) {
				list.add(b);
			}
		}
		return list;
	}
	
	public static Blueprint getBlueprint(String name) {
		for(Blueprint b : blueprints) {
			if(b.name.equals(name)) {
				return b;
			}
		}
		return null;
	}
	
	public static void addBlueprintToPlayer(Blueprint b, EntityPlayer player) {
		getResearch(player).addBlueprint(b);
	}
	
	public static PlayerResearch getResearch(EntityPlayer player) {
		String name = player.getUniqueID().toString();
		World world = MinecraftServer.getServer().worldServers[0];
		PlayerResearch research = (PlayerResearch)world.loadItemData(PlayerResearch.class, name);
		if(research == null) {
			research = new PlayerResearch(name);
			player.worldObj.setItemData(name, research);			
		}
		return research;
	}
	

}
