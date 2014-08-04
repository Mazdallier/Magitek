package democretes.api.macht;

/** Proper use of IMachtStorage. Use this in conjunction with other tiles to easily implement the Macht api.
 * 
 * @author Democretes
 *
 */
public class MachtStorage implements IMachtStorage {
	
	int capacity;
	int macht;
	boolean supercharge;
	
	public MachtStorage(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public int extractMacht(int amount) {
		if(this.macht < amount) {
			int energy = this.macht;
			this.macht = 0;
			return energy;
		}
		this.macht -= amount;
		return amount;
	}

	@Override
	public int receiveMacht(int amount) {
		if(this.macht + amount > this.capacity) {
			int energy = this.capacity - this.macht;
			this.macht = this.capacity;
			return energy;
		}
		this.macht += amount;
		return amount;
	}

	@Override
	public boolean isSupercharged() {
		return this.supercharge;
	}

	@Override
	public int getCapacity() {
		return this.capacity;
	}

	@Override
	public int getMachtStored() {
		return this.macht;
	}

	@Override
	public void setSupercharged(boolean charge) {
		this.supercharge = charge;		
	}

}
