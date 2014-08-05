package democretes.block.generators;

public class TilePurityGenerator extends TileGeneratorBase{

	public TilePurityGenerator(int machtCapacity) {
		super(machtCapacity);
	}

	@Override
	public boolean canGenerate() {
		return this.getPurity() != 0;
	}

	@Override
	public int getFuel() {
		if(this.getPurity() < 0) {
			this.increasePurity(1);
			return 5;
		}else{
			this.decreasePurity(1);
			return 10;
		}
	}

}
