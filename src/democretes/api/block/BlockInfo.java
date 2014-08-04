package democretes.api.block;

import net.minecraft.entity.player.EntityPlayer;

public class BlockInfo {
	
	int x;
	int y;
	int z;
	EntityPlayer player;
	int macht;
	boolean isMachtHandler;
	
	public BlockInfo(EntityPlayer player, int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.macht = 0;
		this.isMachtHandler = false;
	}
	
	public BlockInfo(EntityPlayer player, int x, int y, int z, int energy) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.macht = energy;
		this.isMachtHandler = true;
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getZ() {
		return this.z;
	}
	
	public boolean isMachtHandler() {
		return this.isMachtHandler;
	}
	
	public int getMacht() {
		return this.macht;
	}

}
