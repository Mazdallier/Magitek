package democretes.block.nodes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import democretes.api.macht.IMachtHandler;
import democretes.api.macht.IMachtStorage;
import democretes.api.macht.MachtStorage;

public class TileEnergyNode extends TileNodeBase implements IMachtStorage {

	MachtStorage macht = new MachtStorage(100);
	
	public TileEnergyNode() {
		this.range = 3;
	}
	
	@Override
	public boolean canLink(TileEntity tile) {
		return tile instanceof IMachtHandler;
	}

	@Override
	public void transferTo(TileEntity tile) {
		extractMacht(((IMachtHandler)tile).receiveMacht(this.macht.getMachtStored()));
	}

	@Override
	public boolean canTransfer(TileEntity tile) {
		return this.macht.getMachtStored() > 0;
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
