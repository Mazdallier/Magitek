package democretes.block.generators;

public class TileSubTerraGenerator extends TileGeneratorBase {

	public TileSubTerraGenerator() {
		super(2000);
	}

	@Override
	public boolean canGenerate() {
		return this.xCoord < 6;
	}

	int count;
	@Override
	public int getFuel() {
		count++;
		if(count == 40) {
			this.decreasePurity(1);
			return 10 + (this.getPurity()/500);
		}
		return 0;
	}

}
