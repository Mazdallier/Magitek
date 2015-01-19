package democretes.block.generators.disposable;

import java.util.Random;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraftforge.common.util.ForgeDirection;

public class TileLightningGenerator extends TileSingleGeneratorBase {

	public TileLightningGenerator() {
		super(25000);
	}

	@Override
	protected void renderWhenActive() {}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		Random r = new Random();
		if(count++ >= r.nextInt(100)) {
			count = 0;
			if(r.nextFloat() > (float)this.energyRemaining/(float)this.maxEnergy) {
				int x = r.nextInt(50) - r.nextInt(50);
				int z = r.nextInt(50) - r.nextInt(50);
				int y = this.worldObj.getActualHeight();
				do{
					y--;
				}while(this.worldObj.isAirBlock(this.xCoord + x, y, this.zCoord + z));
				this.worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, this.xCoord + x, y + 1, this.zCoord + z));
			}
		}
	}
	
}
