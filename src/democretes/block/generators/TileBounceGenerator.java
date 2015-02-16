package democretes.block.generators;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.macht.IMachtStorage;
import democretes.block.TileMTBase;
import democretes.entity.EntityMacht;

public class TileBounceGenerator extends TileMTBase {
	
	public int facing;
	boolean canCreate = true;
	MachtOrb orb;
	
	int distance;
	int waitingTime;
	
	int count;
	@Override
	public void updateEntity() {
		if(count++%40==0 && this.canCreate) {
			ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[facing];
			TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
			if(tile instanceof TileSpreader || tile instanceof TileMiniSpreader) {
				this.canCreate = false;
				this.orb = new MachtOrb(this);
				this.orb.macht = ((IMachtStorage)tile).extractMacht(100);;
				if(this.orb.macht == 0) {
					this.orb = null;
					return;
				}
			}
		}
		if(this.orb == null) {
			return;
		}
		double v = this.orb.timesBounced*.1 + 1;
		if(distance == 0) {
			ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[this.facing]];
			for(int i = 1; i < 50; i++) {
				TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX*i, this.yCoord + dir.offsetY*i, this.zCoord + dir.offsetZ*i);
				if(tile instanceof TileBounceGenerator || tile instanceof TileSpreader || tile instanceof TileMiniSpreader) {
					distance = i;	
					break;
				}
			}
			this.worldObj.spawnEntityInWorld(new EntityMacht(this.worldObj, this.xCoord + 0.5D, this.yCoord+0.5, this.zCoord + 0.5D, v*dir.offsetX/20, v*dir.offsetY/20, v*dir.offsetZ/20));
		}
		if(waitingTime++ >= (distance*20/v)) {
			this.orb.macht += distance*(this.orb.timesBounced + 1);
			this.orb.timesBounced++;
			waitingTime = 0;
			distance = 0;
			projectOrb();
		}
	}
	
	void projectOrb() {
		for(int i = 1; i < 50; i++) {
			ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[this.facing]];
			TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX*i, this.yCoord + dir.offsetY*i, this.zCoord + dir.offsetZ*i);
			if(tile == null) {
				continue;
			}
			if(tile instanceof TileBounceGenerator ) {
				((TileBounceGenerator)tile).orb = this.orb;
				this.orb = null;
				break;
			}else if(tile instanceof TileSpreader || tile instanceof TileMiniSpreader) {
				((IMachtStorage)tile).receiveMacht(orb.macht);
				if(this.orb.getSource(this.worldObj) != null) {
					this.orb.getSource(this.worldObj).canCreate = true;					
				}
				this.orb = null;
				break;
			}
		}
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("Create", this.canCreate);
		nbt.setInteger("Facing", this.facing);
		
		if(this.orb != null) {
			nbt.setBoolean("Creation", true);
			this.orb.writeToNBT(nbt);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.canCreate = nbt.getBoolean("Create");
		this.facing = nbt.getInteger("Facing");
		
		if(nbt.getBoolean("Creation")) {
			this.orb = new MachtOrb();
			this.orb.readFromNBT(nbt);
		}
	}

	class MachtOrb {
		
		int timesBounced;
		int macht;
		int x, y, z;
		
		public MachtOrb() {}
		
		public MachtOrb(TileBounceGenerator source) {
			this.x = source.xCoord;
			this.y = source.yCoord;
			this.z = source.zCoord;
		}
		
		public MachtOrb(TileBounceGenerator source, int macht, int timesBounced) {
			this.x = source.xCoord;
			this.y = source.yCoord;
			this.z = source.zCoord;
			this.macht = macht;
			this.timesBounced = timesBounced;
		}

		public void writeToNBT(NBTTagCompound nbt) {
			nbt.setInteger("Macht", this.macht);
			nbt.setInteger("Bounce", this.timesBounced);
			
			nbt.setInteger("xChoord", this.x);
			nbt.setInteger("yChoord", this.y);
			nbt.setInteger("zChoord", this.z);
		}
		
		public MachtOrb readFromNBT(NBTTagCompound nbt) {	
			this.x = nbt.getInteger("xChoord");
			this.y = nbt.getInteger("yChoord");
			this.z = nbt.getInteger("zChoord");
			TileBounceGenerator tile = new TileBounceGenerator();
			this.macht = nbt.getInteger("Macht");
			this.timesBounced = nbt.getInteger("Bounce");
			return this;
		}
		
		public TileBounceGenerator getSource(World world) {
			return (TileBounceGenerator)world.getTileEntity(x, y, z);
		}
		
		
	}

}
