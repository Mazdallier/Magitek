package democretes.block.transfer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.block.TileMTBase;
import democretes.utils.handlers.ConfigHandler;

public class TileItemTransfer extends TileMTBase implements IInventory {
	
	ItemStack inventory;
	public int facing;
	
	private int count = 20;
	@Override
	public void updateEntity() {
		if(count++ >= 20) {
			getItems();
			transferItems();
			count = 0;
		}
	}
	
	void getItems() {
		ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[facing];
		TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
		if(tile instanceof IInventory) {
			for(int j = 0; j < ((IInventory)tile).getSizeInventory(); j++) {
				ItemStack inv = ((IInventory)tile).getStackInSlot(j);
				if(inv == null) {
					continue;
				}
				int amount = 0;
				if(this.inventory != null) {
					if(inv.getItem() != this.inventory.getItem() || inv.getItemDamage() != this.inventory.getItemDamage()) {
						continue;
					}
					if(this.inventory.stackSize + inv.stackSize > this.inventory.getMaxStackSize()) {
						amount = this.inventory.getMaxStackSize() - this.inventory.stackSize;
						this.inventory.stackSize = this.inventory.getMaxStackSize();
					}else{
						amount = inv.getMaxStackSize();
						this.inventory.stackSize += inv.stackSize;
					}
				}else{
					this.inventory = inv;
					amount = inv.stackSize;
				}
				if(tile instanceof ISidedInventory) {
					if(((ISidedInventory)tile).canExtractItem(j, inv, this.facing)) {
						((ISidedInventory)tile).decrStackSize(j, amount);
					}
				}else{
					((IInventory)tile).decrStackSize(j, amount);							
				}
			}
		}
	}
	
	void transferItems() {		
		ForgeDirection dir = ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[facing]];
		for(int i = 1; i < ConfigHandler.range; i++) {
			TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX*i, this.yCoord + dir.offsetY*i, this.zCoord + dir.offsetZ*i);
			if(tile instanceof IInventory) {
				for(int j = 0; j < ((IInventory)tile).getSizeInventory(); j++) {
					ItemStack inv = ((IInventory)tile).getStackInSlot(j);
					int amount = 0;
					if(this.inventory != null) {
						if(inv == null) {
							inv = this.inventory;
							this.inventory = null;
						}else if(inv.stackSize >= inv.getMaxStackSize()) {
							continue;
						}else if(inv.getItem() == this.inventory.getItem() && inv.getItemDamage() == this.inventory.getItemDamage()) {
							if(inv.stackSize + this.inventory.stackSize > inv.getMaxStackSize()) {
								this.inventory.stackSize = inv.getMaxStackSize() - inv.stackSize;
								inv.stackSize = inv.getMaxStackSize();
							}else{
								inv.stackSize += this.inventory.stackSize;
								this.inventory = null;
							}
						}
						if(tile instanceof ISidedInventory) {
							if(((ISidedInventory)tile).canInsertItem(j, inv, this.facing)) {
								((ISidedInventory)tile).setInventorySlotContents(j, inv);
							}
						}else{
							((IInventory)tile).setInventorySlotContents(j, inv);							
						}
					}
				}
				return;
			}
		}
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
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public String getInventoryName() {
		return "TileItemThings";
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
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);	
			
		nbt.setInteger("Facing", this.facing);
		
		NBTTagCompound tag = new NBTTagCompound();
		if(this.inventory != null) {
			this.inventory.writeToNBT(tag);
		}
		nbt.setTag("Item", tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {		
		super.readFromNBT(nbt);
		
		this.facing = nbt.getInteger("Facing");
		
		NBTTagCompound tag = nbt.getCompoundTag("Item");
		this.inventory = ItemStack.loadItemStackFromNBT(tag);	
	}
	
	

}
