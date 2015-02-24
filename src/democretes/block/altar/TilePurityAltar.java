package democretes.block.altar;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.AxisAlignedBB;
import democretes.api.helpers.PurityAltarHelper;
import democretes.api.macht.MachtStorage;

public class TilePurityAltar extends TileEntityEndPortal {


	MachtStorage macht = new MachtStorage(10000);
	
	public boolean isDark;
	public boolean creative;
	int count = 60;
	@Override
	public void updateEntity() {
		if(!this.worldObj.isRemote) {
			return;
		}
		if(count%10==0) {
			List<EntityItem> items = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 2, this.zCoord+1));
			for(EntityItem item : items) {
				if(PurityAltarHelper.recipeExists(item.getEntityItem())) {
					ItemStack stack;
					if(((TilePurityAltar)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord)).isDark) {
						stack = PurityAltarHelper.getDarkResult(item.getEntityItem());
						Random r = new Random();
						for(int i = 0; i < 3; i++) {
							this.worldObj.spawnParticle("smoke", item.posX, item.posY, item.posZ, r.nextDouble() - r.nextDouble(), r.nextDouble() - r.nextDouble(), r.nextDouble() - r.nextDouble());
						}
					}else{
						stack = PurityAltarHelper.getLightResult(item.getEntityItem());					
					}
					if(stack ==  null) {
						return;
					}
					stack.stackSize = item.getEntityItem().stackSize;
					item.setEntityItemStack(stack.copy());
				}
				item.setVelocity(0.25D, 0.25D, 0);				
			}
			
		}
	}
	
	public boolean checkForStructure() {
		if(this.worldObj.getBlock(this.xCoord, this.yCoord+3, this.zCoord) == Blocks.nether_brick) {
			this.isDark = true;
		}
		Block stairs;
		Block base;
		Block pillar;
		if(isDark) {
			stairs = Blocks.nether_brick_stairs;
			base = Blocks.nether_brick;
			pillar = Blocks.nether_brick_fence;
		}else{
			stairs = Blocks.quartz_stairs;
			base = Blocks.quartz_block;
			pillar = base;
		}
		int[][] stairsChoords = {{1,0,0}, {-1,0,0}, {0,0,1}, {0,0,-1}, {-1, 3, -1}, {-1, 3, 0}, {-1, 3, 1}, {1, 3, 1}, {1, 3, 0}, {1, 3, -1}, {0, 3, 1}, {0, 3, -1}};
		int[][] baseChoords = {{1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}, {0, 3, 0}};
		int[][] pillarChoords = {{1, 1, 1}, {1, 1, -1}, {-1, 1, 1}, {-1, 1, -1}, {1, 2, 1}, {1, 2, -1}, {-1, 2, 1}, {-1, 2, -1}};

		for(int i = 0; i < stairsChoords.length; i++) {
			if(this.worldObj.getBlock(this.xCoord+stairsChoords[i][0], this.yCoord+stairsChoords[i][1], this.zCoord+stairsChoords[i][2]) != stairs) {
				return false;
			}
		}
		for(int i = 0; i < baseChoords.length; i++) {
			if(this.worldObj.getBlock(this.xCoord+baseChoords[i][0], this.yCoord+baseChoords[i][1], this.zCoord+baseChoords[i][2]) != base) {
				return false;
			}
		}
		for(int i = 0; i < pillarChoords.length; i++) {
			if(!isDark) {
				if(this.worldObj.getBlockMetadata(this.xCoord+pillarChoords[i][0], this.yCoord+pillarChoords[i][1], this.zCoord+pillarChoords[i][2]) != 2) {
					return false;
				}
			}
			if(this.worldObj.getBlock(this.xCoord+pillarChoords[i][0], this.yCoord+pillarChoords[i][1], this.zCoord+pillarChoords[i][2]) != pillar) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("Creative", this.creative);
		this.macht.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.creative = nbt.getBoolean("Creative");
		this.macht.readFromNBT(nbt);
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}	

}
