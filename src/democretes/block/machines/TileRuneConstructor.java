package democretes.block.machines;

import cpw.mods.fml.common.FMLLog;
import democretes.item.ItemsMT;
import democretes.utils.crafting.AltarHelper;
import democretes.utils.crafting.RunicHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileRuneConstructor extends TileMachineBase implements IInventory {

	public ItemStack[] inventory = new ItemStack[2];
	
	public TileRuneConstructor() {
		super(10000);
	}

	int energy;
	@Override
	public void doStuff() {
		if(RunicHelper.recipeExists(this.inventory[1])) {
			int multiplier = Math.min(this.inventory[0].stackSize, this.inventory[1].stackSize);
			energy += this.extractMacht(RunicHelper.getMacht(this.inventory[1])*multiplier/20);
			if(this.energy >= RunicHelper.getMacht(this.inventory[1])) {
				this.energy = 0;
				ItemStack stack = RunicHelper.getResult(this.inventory[1]).copy();
				stack.stackSize *= multiplier;
				if(stack.stackSize > stack.getMaxStackSize() || this.inventory[1].stackSize > this.inventory[0].stackSize) {
					EntityItem item = new EntityItem(this.worldObj, this.xCoord, this.yCoord + 0.5F, this.zCoord, stack);
					this.inventory[1].stackSize -= multiplier;
					if(this.inventory[1].stackSize == 0) {
						this.inventory[1] = null;
					}
					this.worldObj.spawnEntityInWorld(item);
				}else{
					this.inventory[1] = stack;						
				}	
				this.inventory[0].stackSize -= multiplier;
				if(this.inventory[0].stackSize == 0) {
					this.inventory[0] = null;
				}				
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);				
			}
		}else{
			if(this.energy > 0) {
				this.energy = 0;
			}
		}
	}

	@Override
	public boolean canActivate() {
		return this.inventory[0] != null && this.inventory[1] != null;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList var2 = new NBTTagList();
		for (int var3 = 0; var3 < inventory.length; ++var3) {
			if (inventory[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte)var3);
				inventory[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		nbt.setTag("Items", var2);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList var2 = nbt.getTagList("Items", 10);
		inventory = new ItemStack[getSizeInventory()];
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = var2.getCompoundTagAt(var3);
			byte var5 = var4.getByte("Slot");
			if (var5 >= 0 && var5 < inventory.length)
				inventory[var5] = ItemStack.loadItemStackFromNBT(var4);
		}
	}
	
	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(this.inventory[i] != null) {
			if(this.inventory[i].stackSize <= j) {
				ItemStack stack = this.inventory[i];
				this.inventory[i] = null;
				return stack;
			}
			ItemStack stack = this.inventory[i].splitStack(j);
		    if (this.inventory[i].stackSize == 0) {
		    	this.inventory[i] = null;
		    }
		    return stack;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if(this.inventory[i] != null) {
			return this.inventory[i];
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		this.inventory[i] = stack;
		if(stack != null) {
			if(stack.stackSize > this.getInventoryStackLimit()) {
				this.inventory[i].stackSize = this.getInventoryStackLimit();
			}
		}
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
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if(i == 0) {
			return stack.getItem() == ItemsMT.material && stack.getItemDamage() == 0;
		}else{
			return i == 1;
		}
	}

	@Override
	public String getInventoryName() {
		return "RuneConstructorInventory";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt  = new NBTTagCompound();
		NBTTagList nbttaglist = new NBTTagList();
	    for (int i = 0; i < this.inventory.length; ++i) {
	        if (this.inventory[i] != null) {
	            NBTTagCompound compound1 = new NBTTagCompound();
	            compound1.setByte("Slot", (byte)i);
	            this.inventory[i].writeToNBT(compound1);
	            nbttaglist.appendTag(compound1);
	        }
	    }
	    nbt.setTag("Items", nbttaglist);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagList nbttaglist = pkt.func_148857_g().getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound compound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            int j = compound1.getByte("Slot") & 255;
            if (j >= 0 && j < this.inventory.length)            {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(compound1);
            }
        }
	}
	

}
