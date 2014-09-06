package democretes.block.generators;

import democretes.api.macht.IMachtStorage;
import democretes.utils.handlers.ConfigHandler;
import democretes.utils.helper.DirectionHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileDestructionGenerator extends TileGeneratorBase {
	
	
	private int radius = 1;
	private int maxRadius = -1;
	private int layer = -1;
	
	
	public TileDestructionGenerator() {
		super(25000);
	}	
	
	private int count;
	@Override
	protected boolean canGenerate() {
		if(this.layer < 0) {
			this.layer = this.yCoord - 1;
		}
		return this.getMachtStored() < this.getCapacity() && detectMaxRadius() && layer > 1;
	}

	@Override
	protected int getFuel() {
		int amount = 0;
		if(count++ >= 20) {
			amount = destroyBlocks(radius);
			if(radius++ > maxRadius) {
				radius = 1;
			}
			if(radius == 1) {
				layer--;
			}
			count = 0;
		}
		return amount;
	}

	@Override
	protected void renderWhenActive() {}
	
	int destroyBlocks(int radius) {
		int blocksChanged = 0;
		for(int i = -radius; i <= radius; i++) {
			for(int k = -radius; k <= radius; k++) {
				if(i * i + k * k >= (radius + 0.50f) * (radius + 0.50f)) {
					continue;
				}
				if(!this.worldObj.isAirBlock(this.xCoord + i, layer, this.zCoord + k)) {
					Block block = this.worldObj.getBlock(this.xCoord + i, layer, this.zCoord + k);
					if(block == Blocks.bedrock || (block == base && layer == this.yCoord - 1)) {
						continue;
					}
					this.worldObj.setBlockToAir(this.xCoord + i, layer, this.zCoord + k);
					blocksChanged++;
				}
	        }				
	    }
		return blocksChanged;
	}

	Block base;
	boolean detectMaxRadius() {
		if(maxRadius > 0 && this.base != null) {
			return true;
		}
		int xx = xCoord;
		int yy = yCoord - 1;
		int zz = zCoord;
		base = this.worldObj.getBlock(xx, yy, zz);
		boolean horizon = false;
		for(int i = 0; i < hBlocks.length; i++) {
			if(base == hBlocks[i]) {
				horizon = true;
				break;
			}
		}
		if(horizon) {
			for(ForgeDirection dir : DirectionHelper.horizontal) {
				for(int j = 1; j < ConfigHandler.range; j++) {
					if(base == this.worldObj.getBlock(xx + dir.offsetX*j, yy + dir.offsetY*j, zz + dir.offsetZ*j)) {
						this.maxRadius++;
					}else{
						break;
					}
				}			
			}
		}
		return maxRadius > 0;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setInteger("Layer", layer);
		nbt.setInteger("Radius", radius);
		nbt.setInteger("MaxRadius", maxRadius);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.layer = nbt.getInteger("Layer");
		this.radius = nbt.getInteger("Radius");
		this.maxRadius = nbt.getInteger("MaxRadius");
	}
}
