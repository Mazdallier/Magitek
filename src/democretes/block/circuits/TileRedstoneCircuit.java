package democretes.block.circuits;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.utils.helper.DirectionHelper;

public class TileRedstoneCircuit extends TileCircuitWire {	
	
	boolean closed;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!closed && isPowered()) {
			closed = true;
			closeCircuit();
		}else if(!isPowered()) {
			closed = false;
		}
	}
	
	@Override
	void overwriteMaster(TileMasterCircuit master) {
		if(!isPowered()) {
			this.master = master;
		}else{
			this.master = null;
		}
	}
	
	boolean isPowered() {
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if(this.worldObj.getIndirectPowerOutput(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ, dir.ordinal())) {
				return true;
			}
			if(this.worldObj.getBlock(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) instanceof BlockRedstoneWire && this.worldObj.getBlockMetadata(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) > 0) {
				return true;
			}
		}
		return false;
	}

}
