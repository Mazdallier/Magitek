package democretes.block.nodes;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import democretes.block.TileMTBase;

public abstract class TileNodeBase extends TileMTBase  {
	
	public ArrayList<TileEntity> tiles = new ArrayList();	
	public int range = 3;
	
	public abstract boolean canLink(TileEntity tile);
	
	public abstract void transferTo(TileEntity tile);
	
	public abstract boolean canTransfer(TileEntity tile);
	
	@Override
	public void updateEntity() {
		if(this.worldObj.isRemote) {
			return;
		}
		for(TileEntity tile : tiles) {
			if(tile != null) {
				if(canTransfer(tile)) {
					transferTo(tile);
				}
			}
		}
	}

}
