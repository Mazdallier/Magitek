package democretes.block.circuits;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.macht.IMachtHandler;
import democretes.api.macht.IMachtStorage;
import democretes.block.TileMTBase;

public class TileCircuitWire extends  TileCircuitBase {
	
	
	private int count;
	@Override
	public void updateEntity() {
		if(count++%10==0) {
			if(this.master != null) {
				for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
					TileEntity tile = this.worldObj.getTileEntity(this.xCoord+dir.offsetX, this.yCoord+dir.offsetY, this.zCoord+dir.offsetZ);
					if(tile instanceof TileCircuitWire) {
						if(((TileCircuitWire)tile).master == null) {
							((TileCircuitWire)tile).overwriteMaster(master);
						}else if(((TileCircuitWire)tile).master != this.master){
							this.master.shorted = true;
							((TileCircuitWire)tile).master.shorted = true;
						}
					}
				}		
				if(this.worldObj.getTileEntity(master.xCoord, master.yCoord, master.zCoord) == null) {
					this.master = null;
				}				
			}
		}
		super.updateEntity();
	}	
	
	void overwriteMaster(TileMasterCircuit master) {
		this.master = master;
	}


}
