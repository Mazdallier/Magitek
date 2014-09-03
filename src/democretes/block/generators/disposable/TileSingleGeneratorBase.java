package democretes.block.generators.disposable;

import democretes.block.generators.TileGeneratorBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public abstract class TileSingleGeneratorBase extends TileGeneratorBase {
	
	public int energyRemaining;
	public int maxEnergy;
	int drain = 50;
	float explosionStrength = 1.5F;
	
	public TileSingleGeneratorBase(int energy) {
		super(10000);
		this.energyRemaining = this.maxEnergy = energy;
	}
	
	public TileSingleGeneratorBase(int capacity, int energy) {
		super(capacity);
		this.energyRemaining = this.maxEnergy = energy;
	}

	@Override
	protected boolean canGenerate() {
		return this.getMachtStored() < this.getCapacity() - this.maxEnergy/1000;
	}
	
	int count;
	@Override
	protected int getFuel() {
		if(count++ >= 20) {
			count = 0;
			if(this.energyRemaining <= 0) {
				detonate();
			}
			this.energyRemaining -= drain;
			return drain;
		}
		return 0;
	}
	
	public void detonate() {
		if(!this.worldObj.isRemote) {
			this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, explosionStrength/4, false);
			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Max", this.maxEnergy);
		nbt.setInteger("Remaining", this.energyRemaining);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.energyRemaining = pkt.func_148857_g().getInteger("Remaining");
		this.maxEnergy = pkt.func_148857_g().getInteger("Max");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Remaining", this.energyRemaining);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.energyRemaining = nbt.getInteger("Remaining");
	}

}
