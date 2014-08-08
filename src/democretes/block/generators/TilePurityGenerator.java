package democretes.block.generators;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TilePurityGenerator extends TileGeneratorBase{

	@Override
	protected boolean canGenerate() {
		if(!this.worldObj.isRemote) {
			this.amount = this.getPurity();
		}
		return this.getPurity() != 0;
	}

	int count;
	@Override
	protected int getFuel() {
		count++;
		if(count == 20) {
			count = 0;
			if(this.getPurity() < 0) {
				this.increasePurity(1);
				return 5;
			}else{
				this.decreasePurity(1);
				return 10;
			}
		}
		return 0;
	}
	
	@Override
	protected void renderWhenActive() {}
	
	public int amount;	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Amount", this.amount);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.amount = pkt.func_148857_g().getInteger("Amount");
	}
	
}
