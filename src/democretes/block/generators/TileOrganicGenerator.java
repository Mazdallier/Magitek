package democretes.block.generators;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.IPlantable;

public class TileOrganicGenerator extends TileGeneratorBase {

	public TileOrganicGenerator() {
		super(5000);
	}
	
	int fuel;
	ArrayList<Block> fuelSources = new ArrayList<Block>() {{ 
		add(Blocks.leaves);
		add(Blocks.leaves2);
		add(Blocks.grass);}};
	
	int count = 20;
	@Override
	protected boolean canGenerate() {
		if(count++ >= 20) {
			getOrganicStuff();			
		}
		return fuel > 0;
	}

	@Override
	protected int getFuel() {
		int amount = fuel;
		fuel = 0;
		return amount;
	}

	@Override
	protected void renderWhenActive() {}
	
	private void getOrganicStuff() {
		if(this.isEnhanced()) {
			fuelSources.add(Blocks.log);
			fuelSources.add(Blocks.log2);
		}
		int amount = 0;
		for(int y = 15; y > -2; y--) {
			for(int z = -15; z < 15; z++) {
				for(int x = -15; x < 15; x++) {
					if(fuel > 0) {
						return;
					}
					Block block = this.worldObj.getBlock(this.xCoord + x, this.yCoord + y, this.zCoord + z);
					if(block == null) {
						continue;
					}
					for(Block b : fuelSources) {
						if(block == b) {
							amount = 20;
							break;
						}
					}
					if(block instanceof IPlantable || block instanceof IGrowable) {
						amount = 10;
					}
					if(amount == 0) {
						continue;
					}
					if(block == Blocks.grass) {
						amount = 5;
						this.fuel += amount;
						this.worldObj.setBlock(this.xCoord + x, this.yCoord + y, this.zCoord + z, Blocks.dirt);
						continue;
					}
					if(block == Blocks.log || block == Blocks.log2) {
						amount *= 2;
					}
					this.worldObj.setBlockToAir(this.xCoord + x, this.yCoord + y, this.zCoord + z);
					this.fuel += amount;
				}
			}
		}
	}

}
