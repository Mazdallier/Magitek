package democretes.block.machines;

import democretes.block.TilePurityBase;

public abstract class TileMachineBase extends TilePurityBase {

	public TileMachineBase(int machtCapacity) {
		super(machtCapacity);
	}
	
	@Override
	public void updateEntity() {
		if(!this.worldObj.isRemote) {
			if(canActivate()) {
				doStuff();
			}
		}
	}
	
	public abstract void doStuff();
	
	public abstract boolean canActivate();

}
