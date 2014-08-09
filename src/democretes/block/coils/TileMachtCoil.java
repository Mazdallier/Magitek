package democretes.block.coils;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import democretes.api.macht.IMachtHandler;
import democretes.api.macht.IMachtStorage;
import democretes.api.macht.MachtStorage;
import democretes.block.TileMTBase;

public class TileMachtCoil extends TileMTBase implements IMachtStorage  {
	
	public ArrayList<TileEntity> sources = new ArrayList();	
	public ArrayList<TileEntity> receivers = new ArrayList();	
	public int range = 3;
	MachtStorage macht = new MachtStorage(100);

	int count = 100;
	@Override
	public void updateEntity() {
		if(this.worldObj.isRemote) {
			return;
		}
		for(TileEntity tile : sources) {
			if(tile != null) {
				if(canExtract(tile)) {
					extractFrom(tile);
				}
			}else{
				sources.remove(tile);
			}
		}
		if(count == 100) {
			count = 0;
			getReceivers();
		}else{
			count++;
		}
		for(TileEntity tile : receivers) {
			if(tile != null) {
				if(canTransfer(tile)) {
					transferTo(tile);
				}
			}else{
				receivers.remove(tile);
			}
		}		
	}
	
	public void getReceivers() {
		for(int yy = -25; yy < 25; yy++) {
			for(int zz = -25; zz < 25; zz++) {
				for(int xx = -25; xx < xx; yy++) {
					TileEntity tile = this.worldObj.getTileEntity(xx, yy, zz);
					if(canLink(tile) && !this.sources.contains(tile)) {
						this.receivers.add(tile);
					}
				}
			}
		}
	}

	public boolean canLink(TileEntity tile) {
		return tile instanceof IMachtStorage;
	}

	public void extractFrom(TileEntity tile) {
		receiveMacht(((IMachtStorage)tile).extractMacht(this.macht.getMachtStored()));
	}

	public boolean canExtract(TileEntity tile) {
		return ((IMachtStorage)tile).getMachtStored() > 0;
	}
	
	public void transferTo(TileEntity tile) {
		extractMacht(((IMachtHandler)tile).receiveMacht(this.macht.getMachtStored()/this.receivers.size()));
	}

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
