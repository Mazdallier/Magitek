package democretes.block.machines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.api.helpers.ReconstructorHelper;
import democretes.api.helpers.RitualType;
import democretes.utils.helper.DirectionHelper;


public class TileReconstructor extends TileMachineBase implements IInventory{

	public TileReconstructor() {
		super(25000);
	}

	public ItemStack inventory;
	ArrayList<ItemStack> filter = new ArrayList<ItemStack>();
	private int count;
	int energy;
	int slot;
	ItemStack current;
	
	@Override
	public void doStuff() {
		getDisplayItems();
		if(filter.size() == 0) {
			return;
		}
		if(this.current == null) {
			current = filter.get(slot);
			if(slot++ >= filter.size()) {
				slot = 0;
			}
		}
		if(ReconstructorHelper.recipeExists(current)) {
			if(count++ >= 10) {
				energy += this.extractMacht(ReconstructorHelper.getEnergyForItemStack(current)/100);
				if(energy >= ReconstructorHelper.getEnergyForItemStack(current)) {
					this.energy = 0;
					if(this.inventory != null) {
						if(this.inventory.getItem() == current.getItem() && current.getItemDamage() == this.inventory.getItemDamage()) {
							if(this.inventory.stackSize < this.inventory.getMaxStackSize()) {
								this.inventory.stackSize++;
								this.current = null;
								return;
							}
						}
						this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, current));
					}else{
						this.inventory = current.copy();
					}
					this.current = null;
				}
			}
		}			
	}
	
	void getDisplayItems() {
		final int[] orientationToDir = new int[] {
				3, 4, 2, 5
		};
		for(ForgeDirection dir : DirectionHelper.horizontal) {
			List<EntityItemFrame> frames = this.worldObj.getEntitiesWithinAABB(EntityItemFrame.class, AxisAlignedBB.getBoundingBox(this.xCoord+ dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, this.xCoord + dir.offsetX + 1, this.yCoord + dir.offsetY + 1, this.zCoord + dir.offsetZ + 1));
			for(EntityItemFrame frame : frames) {
				int orientation = frame.hangingDirection;
				if(orientationToDir[orientation] == dir.ordinal())
					filter.add(frame.getDisplayedItem());
			}
		}
	}

	@Override
	public boolean canActivate() {
		return this.getMachtStored() > 0;
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory == null ? null : this.inventory.copy();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		this.inventory.stackSize -= amount;
		if(this.inventory.stackSize <= 0) {
			this.inventory = null;
		}
		return this.inventory;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inventory = stack;
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public String getInventoryName() {
		return "TileReconstructornInv";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);	
		NBTTagCompound tag = new NBTTagCompound();
		if(this.inventory != null) {
			this.inventory.writeToNBT(tag);
		}
		nbt.setTag("Item", tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {		
		super.readFromNBT(nbt);
		NBTTagCompound tag = nbt.getCompoundTag("Item");
		this.inventory = ItemStack.loadItemStackFromNBT(tag);	
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagCompound tag = new NBTTagCompound();
		if(this.inventory != null) {
			this.inventory.writeToNBT(tag);
		}
		nbt.setTag("Item", tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.func_148857_g().getCompoundTag("Item");
		this.inventory = ItemStack.loadItemStackFromNBT(tag);
	}

}
