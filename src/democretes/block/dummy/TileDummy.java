package democretes.block.dummy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import democretes.api.macht.IMachtStorage;
import democretes.api.purity.IPurityHandler;
import democretes.block.TileMTBase;

public class TileDummy extends TileMTBase implements IPurityHandler, IMachtStorage {
	
	public TileEntity tile;	
	public IMachtStorage macht;
	public IPurityHandler purity;
	

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
		this.purity = (IPurityHandler)tile;
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
	public boolean isSupercharged() {
		return this.macht.isSupercharged();
	}

	@Override
	public void setSupercharged(boolean charge) {
		this.macht.setSupercharged(charge);
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
	public int getPurity() {
		return purity.getPurity();
	}

	@Override
	public boolean isDark() {
		return purity.isDark();
	}

	@Override
	public boolean isNeutral() {
		return purity.isNeutral();
	}

	@Override
	public boolean isLight() {
		return purity.isLight();
	}

	@Override
	public void increasePurity(int amount) {
		purity.increasePurity(amount);
	}

	@Override
	public void decreasePurity(int amount) {
		purity.decreasePurity(amount);
	}
	
	@Override
	public boolean isFull() {
		return purity.isFull();
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		//nbt.setInteger("xx", tile.xCoord);
		//nbt.setInteger("yy", tile.yCoord);
		//nbt.setInteger("zz", tile.zCoord);		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		//int x = nbt.getInteger("xx");
		//int y = nbt.getInteger("yy");
		//int z = nbt.getInteger("zz");
		//this.tile = this.worldObj.getTileEntity(x, y, z);
	}

}
