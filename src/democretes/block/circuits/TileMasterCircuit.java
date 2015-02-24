package democretes.block.circuits;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.macht.IMachtHandler;
import democretes.api.macht.IMachtStorage;
import democretes.api.macht.MachtStorage;
import democretes.block.TileMTBase;

public class TileMasterCircuit extends TileCircuitBase {
	
	MachtStorage macht = new MachtStorage(10000);
	boolean shorted;
	
	private int count;
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(count++%20==0) {
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				TileEntity tile = this.worldObj.getTileEntity(this.xCoord+dir.offsetX, this.yCoord+dir.offsetY, this.zCoord+dir.offsetZ);
				if(tile instanceof TileCircuitWire) {
					((TileCircuitWire)tile).overwriteMaster(this);
				}
			}
		}
		if(count%40==0 && this.shorted) {
			this.shorted = false;
		}
	}
	
	@Override
	public int extractMacht(int amount) {
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		return shorted ? 0 : macht.extractMacht(amount);
	}

	@Override
	public int receiveMacht(int amount) {
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		return  shorted ? 0 : macht.receiveMacht(amount);
	}

	@Override
	public int getCapacity() {
		return  macht.getCapacity();
	}

	@Override
	public int getMachtStored() {
		return  macht.getMachtStored();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("Shorted", this.shorted);
		this.macht.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.shorted = nbt.getBoolean("Shorted");
		this.macht.readFromNBT(nbt);
	}

}
