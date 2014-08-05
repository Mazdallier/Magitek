package democretes.block.generators;

public class TileSolarGenerator extends TileGeneratorBase {

	public TileSolarGenerator() {
		super(1000);
	}

	@Override
	public boolean canGenerate() {
		return this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord, this.zCoord) 
				&& this.worldObj.isDaytime() 
				&& !this.worldObj.isRaining();
	}

	private int count;
	@Override
	public int getFuel() {
		count ++;
		if(count >= 80) {
			this.decreasePurity(1);
			if(isDark()) {
				return 2;
			}else if(isLight()) {
				return 8;
			}else{
				return 4;
			}
		}
		return 0;
	}

}
