package democretes.block.generators;

public class TileSolarGenerator extends TileGeneratorBase {

	@Override
	protected boolean canGenerate() {
		if(this.worldObj.isRaining() || this.worldObj.isThundering()) {
			return false;
		}
		return this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord) 
				&& this.worldObj.isDaytime();
	}

	@Override
	protected int getFuel() {
		count ++;
		if(count >= 40) {
			count = 0;
			this.decreasePurity(1);
			if(isDark()) {
				return 1;
			}else if(isLight()) {
				return 4;
			}else{
				return 2;
			}
		}
		return 0;
	}

}
