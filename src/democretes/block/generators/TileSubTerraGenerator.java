package democretes.block.generators;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileSubTerraGenerator extends TileGeneratorBase {


	@Override
	protected boolean canGenerate() {
		return this.yCoord < 6;
	}

	private int count;
	@Override
	protected int getFuel() {
		if(count++ >= 20) {
			this.decreasePurity(2);
			count = 0;
			return 5 + (this.getPurity()/500);
		}
		return 0;
	}

	boolean firstrun = true;
	boolean direction;
	public float inflation;
	@Override
	protected void renderWhenActive() {
		if (this.firstrun) {
			this.inflation = (0.35F + this.worldObj.rand.nextFloat() * 0.55F);
	    }
	    this.firstrun = false;
	    if ((this.inflation > 0.05F) && (!this.direction)) {
	        this.inflation -= 0.05F;
	    }
	    if ((this.inflation <= 0.05F) && (!this.direction)) {
	        this.direction = true;
	    }
	    if ((this.inflation < 1.0F) && (this.direction)) {
	        this.inflation += 0.015F;
	    }
	    if ((this.inflation >= 1.0F) && (this.direction)) {
	        this.direction = false;
	    }
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("Inflation", this.inflation);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.inflation = pkt.func_148857_g().getFloat("Inflation");
	}

}
