package democretes.utils.helper;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class DirectionHelper {
	
	public static ForgeDirection[] horizontal = {ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.SOUTH};
	public static ForgeDirection[] vertical = {ForgeDirection.UP, ForgeDirection.DOWN};

	public static ForgeDirection getDirectionFromTiles(TileEntity farTile, TileEntity mainTile) {
		ForgeDirection dir = null;
		if(farTile.xCoord > mainTile.xCoord) {
			dir = ForgeDirection.EAST;
		}else if(farTile.xCoord < mainTile.xCoord) {
			dir = ForgeDirection.WEST;
		}else if(farTile.yCoord > mainTile.yCoord) {
			dir = ForgeDirection.UP;
		}else if(farTile.yCoord < mainTile.yCoord) {
			dir = ForgeDirection.DOWN;
		}else if(farTile.zCoord > mainTile.zCoord) {
			dir = ForgeDirection.SOUTH;
		}else if(farTile.zCoord < mainTile.zCoord) {
			dir = ForgeDirection.NORTH;
		}
		return dir;
	}
}
