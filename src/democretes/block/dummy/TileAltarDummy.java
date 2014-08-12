package democretes.block.dummy;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import democretes.block.TileMTBase;

public class TileAltarDummy extends TileMTBase implements IInventory {

	public ItemStack inventory;
	public int deathTime = 600;
	
	EntityItem item;
	@Override
	public void updateEntity() {
		this.deathTime--;
		if(deathTime <= 0 && !this.worldObj.isRemote) {
			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
		}
		item = (EntityItem)this.worldObj.findNearestEntityWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1), null);
		if(item == null) {
			if(this.inventory != null) {
				this.inventory = null;
			}
			return;
		}
		this.inventory = item.getEntityItem();
	}
	
	public void removeItem() {
		this.worldObj.removeEntity(item);
		this.inventory = null;
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory;
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
	}

	@Override
	public String getInventoryName() {
		return "TileAltarDummyInventory";
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
		return true;
	}	

}
