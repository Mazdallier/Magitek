package democretes.block.generators;

public class TileSubTerraGenerator extends TileGeneratorBase {

	public TileSubTerraGenerator() {
		super(2000);
	}

	@Override
	public boolean canGenerate() {
		return this.yCoord < 6;
	}

	int count;
	@Override
	public int getFuel() {
		count++;
		if(count >= 20) {
			this.decreasePurity(5);
			count = 0;
			return 5 + (this.getPurity()/500);
		}
		return 0;
	}

}
