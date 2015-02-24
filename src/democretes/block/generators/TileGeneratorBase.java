package democretes.block.generators;

import democretes.block.MTBlocks;
import democretes.block.TileMachtBase;

public abstract class TileGeneratorBase extends TileMachtBase {
	
	
	public TileGeneratorBase() {
		super(1000);
	}
	
	public TileGeneratorBase(int capacity) {
		super(capacity);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)) {
			return;
		}
		if(this.canGenerate()) {
			if(!this.worldObj.isRemote) {
				this.macht.receiveMacht(this.getFuel());
			}else{
				renderWhenActive();
			}
		}
	}
	
	protected abstract boolean canGenerate();
	
	protected abstract int getFuel();
	
	protected abstract void renderWhenActive();
	
	boolean isEnhanced() {
		return this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == MTBlocks.totemEnhancer;
	}
	
	
}
