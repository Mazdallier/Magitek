package democretes.block;

import net.minecraft.nbt.NBTTagCompound;
import democretes.api.purity.IPurityHandler;
import democretes.api.purity.Purity;

public class TilePurityBase extends TileMachtBase implements IPurityHandler{

	protected Purity purity = new Purity();
	
	public TilePurityBase(int machtCapacity) {
		super(machtCapacity);
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
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.purity.writeToNBT(nbt);
	}
	
	public void setPurity(int amount) {
		this.purity.purity = amount;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.purity.readFromNBT(nbt);
	}

}
