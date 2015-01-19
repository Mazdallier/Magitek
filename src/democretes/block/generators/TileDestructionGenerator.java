package democretes.block.generators;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.api.MagitekApi;
import democretes.utils.handlers.ConfigHandler;
import democretes.utils.helper.DirectionHelper;

public class TileDestructionGenerator extends TileGeneratorBase {
	
	
	private int radius = 1;
	private int maxRadius = -1;
	public int layer = -1;
	
	
	public TileDestructionGenerator() {
		super(50000);
		this.purity.maxPurity = 50000;
		this.purity.minPurity = 50000;
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
		if(count++ >= 50) {
			amount = destroyBlocks(radius);
			if(radius++ > maxRadius) {
				radius = 1;
			}
			if(radius == 1) {
				layer--;
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}
			count = 0;
			this.decreasePurity(amount/10);
		}
		return amount*5/2;
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
		for(int i = 0; i < MagitekApi.horizontal.size(); i++) {
			if(base == MagitekApi.horizontal.get(i)) {
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
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Layer", this.layer);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.layer = pkt.func_148857_g().getInteger("Layer");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return INFINITE_EXTENT_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536;
	}
}
