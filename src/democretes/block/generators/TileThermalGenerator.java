package democretes.block.generators;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileThermalGenerator extends TileGeneratorBase implements IFluidHandler {
	
	FluidTank tank = new FluidTank(1000);
	
	public TileThermalGenerator() {
		super(7500);
	}

	private int count = 80;
	@Override
	protected boolean canGenerate() {
		if(this.getMachtStored() >= this.getCapacity()) {
			return false;
		}
		if(this.count++ >= 80) {
			count = 0;
			getLava();			
		}
		return this.tank.getFluidAmount() > 10;
	}

	@Override
	protected int getFuel() {
		if(count%40 == 0) {	
			this.decreasePurity(4);
			return this.tank.drain(100, true).amount;
		}
		return 0;
	}

	@Override
	protected void renderWhenActive() {}
	
	boolean getLava() {
		for(int y = -5; y < 0; y++) {
			for(int z = -5; z < 5; z++) {
				for(int x = -5; x < 5; x++) {
					int xx = this.xCoord + x;
					int yy = this.yCoord + y;
					int zz = this.zCoord + z;
					if(this.worldObj.getBlock(xx, yy, zz) == Blocks.lava && this.worldObj.getBlockMetadata(xx, yy, zz) == 0 && this.tank.getCapacity() >= this.tank.getFluidAmount() + 1000) {
						this.worldObj.setBlockToAir(xx, yy, zz);
						this.tank.fill(new FluidStack(FluidRegistry.LAVA, 1000), true);
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(resource.getFluid() == this.tank.getFluid().getFluid()) {
			return this.tank.drain(resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return this.tank.getFluid().getFluid() == fluid;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return this.tank.getFluid().getFluid() == fluid;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {this.tank.getInfo()};
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		this.tank.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.tank.readFromNBT(nbt);
	}

}
