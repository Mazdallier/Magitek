package democretes.block.circuits;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.macht.IMachtHandler;
import democretes.api.macht.IMachtStorage;
import democretes.block.TileMTBase;

public class TileCircuitBase extends TileMTBase  implements IMachtStorage {


	public TileMasterCircuit master;
	
	@Override
	public void updateEntity() {
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = this.worldObj.getTileEntity(this.xCoord+dir.offsetX, this.yCoord+dir.offsetY, this.zCoord+dir.offsetZ);
			if(tile instanceof IMachtHandler && tile instanceof TileCircuitBase == false) {
				this.extractMacht(((IMachtHandler)tile).receiveMacht(Math.min(100, this.getMachtStored())));
			}
		}	
	}
	
	void closeCircuit() {
		this.master = null;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = this.worldObj.getTileEntity(this.xCoord+dir.offsetX, this.yCoord+dir.offsetY, this.zCoord+dir.offsetZ);
			if(tile instanceof TileCircuitBase) {
				if(((TileCircuitBase)tile).master != null) {
					((TileCircuitBase)tile).closeCircuit();					
				}
			}
		}			
	}

	@Override
	public int extractMacht(int amount) {
		return this.master == null ? 0 : master.extractMacht(amount);
	}

	@Override
	public int receiveMacht(int amount) {
		return this.master == null ? 0 : master.receiveMacht(amount);
	}

	@Override
	public int getCapacity() {
		return this.master == null ? 0 : master.getCapacity();
	}

	@Override
	public int getMachtStored() {
		return this.master == null ? 0 : master.getMachtStored();
	}


}
