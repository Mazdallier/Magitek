package democretes.block.dummy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import democretes.api.macht.IMachtStorage;
import democretes.block.TileMTBase;

public class TileSubTerraDummy extends TileMTBase implements IMachtStorage {
	
	public TileEntity tile;	
	public IMachtStorage macht;
	

	@Override
	public void updateEntity() {
		if(!this.worldObj.isRemote) {
			setTile(this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord));
		}
	}
	
	@Override
	public boolean canUpdate() {
		return this.tile == null;
	}
	
	public void setTile(TileEntity tile) {
		this.tile = tile;
		this.macht = (IMachtStorage)tile;
	}

	@Override
	public int extractMacht(int amount) {
		return this.macht.extractMacht(amount);
	}

	@Override
	public int receiveMacht(int amount) {
		return this.macht.receiveMacht(amount);
	}

	@Override
	public int getCapacity() {
		return this.macht.getCapacity();
	}

	@Override
	public int getMachtStored() {
		return this.macht.getMachtStored();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}

}
