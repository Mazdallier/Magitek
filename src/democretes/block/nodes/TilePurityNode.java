package democretes.block.nodes;

import democretes.api.purity.IPurityHandler;
import democretes.api.purity.Purity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TilePurityNode extends TileNodeBase implements IPurityHandler {

	Purity purity = new Purity(500, 500, 0);
	
	@Override
	public boolean canLink(TileEntity tile) {
		return tile instanceof IPurityHandler;
	}

	@Override
	public void transferTo(TileEntity tile) {
		int amount = 1;
		if(((IPurityHandler)tile).getPurity() < 0) {
			amount = -1;
		}
		do{
			((IPurityHandler)tile).increasePurity(amount);
			this.decreasePurity(amount);
		}while(this.getPurity() > 0 && !((IPurityHandler)tile).isFull());
	}

	@Override
	public boolean canTransfer(TileEntity tile) {
		return !((IPurityHandler)tile).isFull();
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

	@Override
	public boolean isFull() {
		return this.purity.isFull();
	}

}
