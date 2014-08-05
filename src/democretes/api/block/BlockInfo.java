package democretes.api.block;

import net.minecraft.entity.player.EntityPlayer;

public class BlockInfo {
	
	int x;
	int y;
	int z;
	EntityPlayer player;
	int macht;
	boolean isMachtHandler;
	int purity;
	boolean isPurityHandler;
	
	public BlockInfo() {}
	
	public BlockInfo(EntityPlayer player, int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.macht = 0;
		this.isMachtHandler = false;
		this.purity = 0;
		this.isPurityHandler = false;
	}
	
	public BlockInfo(EntityPlayer player, int x, int y, int z, int energy) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.macht = energy;
		this.isMachtHandler = true;
		this.purity = 0;
		this.isPurityHandler = false;
	}
	
	public BlockInfo(EntityPlayer player, int x, int y, int z, int energy, int purity) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.macht = energy;
		this.isMachtHandler = true;
		this.purity = purity;
		this.isPurityHandler = true;
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	public void setPlayer(EntityPlayer player) {
		this.player = player;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getZ() {
		return this.z;
	}
	
	public void setZ(int z) {
		this.z = z;
	}
	
	public boolean isMachtHandler() {
		return this.isMachtHandler;
	}
	
	public int getMacht() {
		return this.macht;
	}
	
	public void setMacht(int macht) {
		this.macht = macht;
		this.isMachtHandler = true;
	}
	
	public boolean isPurityHandler() {
		return this.isPurityHandler;
	}

	public int getPurity() {
		return this.purity;
	}
	
	public void setPurity(int purity) {
		this.purity = purity;
		this.isPurityHandler = true;
	}
}
