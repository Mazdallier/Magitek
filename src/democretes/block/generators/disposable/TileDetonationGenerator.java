package democretes.block.generators.disposable;

import java.util.Random;

import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.FMLLog;
import democretes.Magitek;


public class TileDetonationGenerator extends TileSingleGeneratorBase {

	public TileDetonationGenerator() {
		super(25000);
	}

	@Override
	protected void renderWhenActive() {}
	
	int count;
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!this.worldObj.isRemote) {
			Random r = new Random();
			if(count++ >= r.nextInt(100)) {
				count = 0;
				if(r.nextFloat() > (float)this.energyRemaining/(float)this.maxEnergy) {
					ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[r.nextInt(5)];
					this.worldObj.createExplosion(null, this.xCoord + dir.offsetX*2, this.yCoord + dir.offsetY*2, this.zCoord + dir.offsetZ*2, explosionStrength, true);
				}
			}
		}
	}
	
	@Override
	public void detonate() {
		if(!this.worldObj.isRemote) {
			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				this.worldObj.createExplosion(null, this.xCoord + dir.offsetX*2, this.yCoord + dir.offsetY*2, this.zCoord + dir.offsetZ*2, explosionStrength*2, true);			
			}
			this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, explosionStrength*2, true);
			this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);	
		}
	}

}
