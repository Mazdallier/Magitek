package democretes.block.generators;

import net.minecraft.nbt.NBTTagCompound;
import democretes.block.TilePurityBase;

public abstract class TileGeneratorBase extends TilePurityBase {
	

	public TileGeneratorBase() {
		super(500);
	}

	@Override
	public void updateEntity() {
		if(this.canGenerate() && !this.worldObj.isRemote) {
			this.receiveMacht(this.getFuel());
		}
	}
	
	public abstract boolean canGenerate();
	
	public abstract int getFuel();
	
}
