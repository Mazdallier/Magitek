package democretes.block.generators;

import democretes.block.TilePurityBase;

public abstract class TileGeneratorBase extends TilePurityBase {
	

	public TileGeneratorBase(int machtCapacity) {
		super(machtCapacity);
	}

	private int count;
	@Override
	public void updateEntity() {
		if(canGenerate() && !this.worldObj.isRemote) {
			receiveMacht(getFuel());
		}
	}
	
	public abstract boolean canGenerate();
	
	public abstract int getFuel();
	
}
