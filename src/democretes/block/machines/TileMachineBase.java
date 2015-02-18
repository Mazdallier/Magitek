package democretes.block.machines;

import democretes.block.TileMachtBase;


public abstract class TileMachineBase extends TileMachtBase {

	public TileMachineBase(int machtCapacity) {
		super(machtCapacity);
	}
	
	@Override
	public void updateEntity() {
		if(canActivate()) {
			doStuff();
		}		
	}
	
	public abstract void doStuff();
	
	public abstract boolean canActivate();

}
