package democretes.api.purity;

import net.minecraft.nbt.NBTTagCompound;

public class Purity implements IPurityHandler {

	public int purity = 0;
	public int maxPurity = 2000;
	public int minPurity = -2000;
	
	public Purity() {}
	
	public Purity(int purity) {
		this.purity = purity;
	}
	
	public Purity(int max, int min) {
		this.maxPurity = max;
		this.maxPurity = min;
	}
	
	public Purity(int max, int min, int purity) {
		this.maxPurity = max;
		this.maxPurity = min;
		this.purity = purity;
	}
	
	@Override
	public int getPurity() {
		return this.purity;
	}

	@Override
	public boolean isDark() {
		return this.purity <= this.minPurity/2;
	}

	@Override
	public boolean isNeutral() {
		return !isDark() && !isLight();
	}

	@Override
	public boolean isLight() {
		return this.purity >= this.maxPurity/2;
	}

	@Override
	public void increasePurity(int amount) {
		this.purity += amount;
		if(this.purity > this.maxPurity) {
			this.purity = this.maxPurity;
		}
	}

	@Override
	public void decreasePurity(int amount) {
		this.purity -= amount;
		if(this.purity < this.minPurity) {
			this.purity = this.minPurity;
		}
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("Purity", this.purity);
		return nbt;
	}

	public Purity readFromNBT(NBTTagCompound nbt) {
		this.purity = nbt.getInteger("Purity");
		return this;
	}
	
}
