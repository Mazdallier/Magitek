package democretes.block.generators;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.utils.network.PacketHandler;

public class TilePurityGenerator extends TileGeneratorBase{
	
	public TilePurityGenerator() {
		this.purity.maxPurity = 10000;
		this.purity.minPurity = -10000;
	}

	@Override
	protected boolean canGenerate() {
		return this.getPurity() != 0;
	}

	int count;
	@Override
	protected int getFuel() {
		if(count++ >= 20) {
			count = 0;
			return 1 + Math.abs(this.getPurity()/100/(this.isEnhanced() ? 2 : 1));
		}
		return 0;
	}
	
	int amount;
	@Override
	public void increasePurity(int amount) {
		super.increasePurity(amount);
		sync(amount);
	}
	
	@Override
	public void decreasePurity(int amount) {
		super.decreasePurity(amount);
		sync(amount);
	}
	
	@Override
	public void setPurity(int amount) {
		super.setPurity(amount);
		sync(amount);
	}
	
	public void sync(int amount) {
		this.amount += amount;
		if(this.amount > 100){
			this.amount = 0;
			PacketHandler.syncPurity(this, this.purity.getPurity());
		}
	}
	
	@Override
	protected void renderWhenActive() {}

	
}
