package democretes.block.machines;

import net.minecraft.nbt.NBTTagCompound;

public class TilePurityInverter extends TileMachineBase {

	public TilePurityInverter() {
		super(5000);
		this.purity.maxPurity = 100;
		this.purity.minPurity = -100;
	}

	int count;
	int energy;
	boolean pulse;	
	
	@Override
	public void doStuff() {
		count++;
		if(count == 10) {
			this.energy = this.extractMacht(100);
			if(energy >= Math.abs(getPurity()*100)) {
				setPurity(getPurity()*-1);
				this.energy -= Math.abs(getPurity()*100);
				pulse = false;
			}
		}
	}

	@Override
	public boolean canActivate() {
		if(this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)) {
			pulse = true;
		}
		return getPurity() != 0 && pulse;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Energy", this.energy);
		nbt.setBoolean("Pulse", this.pulse);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.energy = nbt.getInteger("Energy");
		this.pulse = nbt.getBoolean("Pulse");
	}
}
