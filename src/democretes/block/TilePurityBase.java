package democretes.block;

import net.minecraft.nbt.NBTTagCompound;
import democretes.api.purity.IPurityHandler;
import democretes.api.purity.Purity;

public class TilePurityBase extends TileMachtBase implements IPurityHandler{

	Purity purity = new Purity();
	
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
		this.purity.writeToNBT(nbt);
		super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.purity.readFromNBT(nbt);
		super.readFromNBT(nbt);
	}

}
