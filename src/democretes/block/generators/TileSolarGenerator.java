package democretes.block.generators;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileSolarGenerator extends TileGeneratorBase {

	@Override
	protected boolean canGenerate() {
		if(this.worldObj.isRaining() || this.worldObj.isThundering() || !this.worldObj.isDaytime()) {
			this.increasePurity(1);
			return false;
		}
		return this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
	}

	private int count;
	@Override
	protected int getFuel() {
		if(count++ >= 40) {
			count = 0;
			if(isDark()) {
				return 1;
			}else if(isLight()) {
				return 4;
			}else{
				return 2;
			}
		}
		return 0;
	}
	
	public float angle;
	@Override
	protected void renderWhenActive() {
		float f = this.worldObj.getCelestialAngleRadians(1.0F);
		long time = this.worldObj.getWorldTime()%24000L;
		if(time <= 1000 && time >= 0) {
			f = 5.25F;
		}else if(time >= 23979) {
			if(angle == 0) {
				angle = 5.9999F;
			}
			if(angle > 5.25F) {
				angle -= 0.05F;
			}else{
				angle = 5.25F;
			}
			return;
		}
		if(time >= 10500 && time < 12000) {
			f = 0.9621334F;
		}else if(time >= 12000) {
			if(angle > 0) {
				angle -= 0.05F;
			}else if(angle < 0.1F) {
				angle = 0;
			}
			return;
		}
		angle = f;
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("Angle", this.angle);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.angle = pkt.func_148857_g().getFloat("Angle");
	}

}
