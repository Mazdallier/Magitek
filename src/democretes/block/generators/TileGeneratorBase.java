package democretes.block.generators;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import democretes.block.TilePurityBase;

public abstract class TileGeneratorBase extends TilePurityBase {
	
	int count;
	
	public TileGeneratorBase() {
		super(500);
	}

	@Override
	public void updateEntity() {
		if(this.canGenerate()) {
			if(!this.worldObj.isRemote) {
				this.receiveMacht(this.getFuel());
			}else{
				renderWhenActive();
			}
		}
	}
	
	protected abstract boolean canGenerate();
	
	protected abstract int getFuel();
	
	protected abstract void renderWhenActive();
	
	
}
