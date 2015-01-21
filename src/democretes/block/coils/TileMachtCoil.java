package democretes.block.coils;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import democretes.api.macht.IMachtHandler;
import democretes.api.macht.IMachtStorage;
import democretes.api.macht.MachtStorage;
import democretes.block.TileMTBase;
import democretes.block.generators.TileGeneratorBase;

public class TileMachtCoil extends TileMTBase implements IMachtStorage  {
	
	public ArrayList<TileEntity> receivers = new ArrayList();	
	MachtStorage macht = new MachtStorage(5000);

	int count = 100;
	@Override
	public void updateEntity() {
		if(this.worldObj.isRemote) {
			return;
		}
		if(count++ >= 100) {
			count = 0;
			getReceivers();
		}
		for(TileEntity tile : receivers) {
			if(tile != null) {
				transferTo(tile);				
			}else{
				receivers.remove(tile);
			}
		}		
	}
	
	public void getReceivers() {
		for(int yy = -25; yy < 25; yy++) {
			for(int zz = -25; zz < 25; zz++) {
				for(int xx = -25; xx < 25; xx++) {
					TileEntity tile = this.worldObj.getTileEntity(this.xCoord + xx, this.yCoord + yy, this.zCoord + zz);
					if(canLink(tile)) {
						this.receivers.add(tile);
					}
				}
			}
		}
	}

	public boolean canLink(TileEntity tile) {
		return tile instanceof IMachtStorage && tile instanceof TileGeneratorBase == false;
	}
	
	public void transferTo(TileEntity tile) {
		extractMacht(((IMachtHandler)tile).receiveMacht(this.macht.getMachtStored()/this.receivers.size()));
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
