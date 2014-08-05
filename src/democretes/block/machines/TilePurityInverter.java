package democretes.block.machines;

import net.minecraft.nbt.NBTTagCompound;

public class TilePurityInverter extends TileMachineBase {

	public TilePurityInverter() {
		super(5000);
		this.purity.maxPurity = 50;
		this.purity.minPurity = -50;
	}

	int count;
	int energy;
	@Override
	public void doStuff() {
		count++;
		if(count == 10) {
			this.energy = this.extractMacht(100);
			if(energy >= Math.abs(getPurity()*100)) {
				setPurity(getPurity()*-1);
				this.energy -= Math.abs(getPurity()*100);
			}
		}
	}

	@Override
	public boolean canActivate() {
		return getPurity() != 0 && this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Energy", this.energy);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.energy = nbt.getInteger("Energy");
	}
}
