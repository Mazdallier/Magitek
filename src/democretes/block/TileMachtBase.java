package democretes.block;

import democretes.api.macht.IMachtStorage;
import democretes.api.macht.MachtStorage;

public class TileMachtBase extends TileTRBase implements IMachtStorage {

	private MachtStorage macht;
	
	public TileMachtBase(int amount) {
		this.macht = new MachtStorage(amount);
	}
	
	@Override
	public int extractMacht(int amount) {
		return this.macht.extractMacht(amount);
	}

	@Override
	public int receiveMacht(int amount) {
		return this.macht.receiveMacht(amount);
	}

	@Override
	public boolean isSupercharged() {
		return this.macht.isSupercharged();
	}

	@Override
	public void setSupercharged(boolean charge) {
		this.macht.setSupercharged(charge);
	}

	@Override
	public int getCapacity() {
		return this.macht.getCapacity();
	}

	@Override
	public int getMachtStored() {
		return this.macht.getMachtStored();
	}

}
