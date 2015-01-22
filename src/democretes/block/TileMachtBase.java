package democretes.block;

import net.minecraft.nbt.NBTTagCompound;
import democretes.api.macht.IMachtStorage;
import democretes.api.macht.MachtStorage;

public class TileMachtBase extends TileMTBase implements IMachtStorage {

	protected MachtStorage macht;
	
	public TileMachtBase(int amount) {
		this.macht = new MachtStorage(amount);
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
		this.macht.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.macht.readFromNBT(nbt);
	}

}
