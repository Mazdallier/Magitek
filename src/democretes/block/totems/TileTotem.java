package democretes.block.totems;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.block.TileMTBase;

public class TileTotem extends TileMTBase {
	
	public int facing;
	
	@Override
	public void updateEntity() {
		if(this.worldObj.isRemote) {
			return;
		}
		int count = 10;
		if(count++ >= 10) {	
			int minX = facing == 5 ? 0 : 3+Math.abs(ForgeDirection.getOrientation(facing).offsetX*7);
			int maxX = facing == 4 ? 0 : 3+Math.abs(ForgeDirection.getOrientation(facing).offsetX*7);
			int minZ = facing == 3 ? 0 : 3+Math.abs(ForgeDirection.getOrientation(facing).offsetZ*7);
			int maxZ = facing == 2 ? 0 : 3+Math.abs(ForgeDirection.getOrientation(facing).offsetZ*7);
			List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.xCoord - minX, this.yCoord-3, this.zCoord - minZ, this.xCoord + maxX, this.yCoord+3, this.zCoord + maxZ));	
			if(players.isEmpty()) {
				this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 1 | 2);
				return;
			}
			for(EntityPlayer p : players) {
				if(!p.isInvisible()) {
					this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 15, 1 | 2);
					return;					
				}
			}
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 1 | 2);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Facing", this.facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.facing = nbt.getInteger("Facing");
	}

}
