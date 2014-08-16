package democretes.block.machines;

import democretes.item.ItemsMT;
import democretes.utils.crafting.AltarRecipes;
import democretes.utils.crafting.RunicRecipes;
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

	ItemStack[] inventory = new ItemStack[2];
	
	public TileRuneConstructor() {
		super(5000);
	}

	int energy;
	@Override
	public void doStuff() {
		if(RunicRecipes.recipeExists(this.inventory[1])) {
			energy += this.extractMacht(RunicRecipes.getMacht(this.inventory[1])/20);
			if(this.energy >= RunicRecipes.getMacht(this.inventory[1])) {
				ItemStack stack;
				this.energy = 0;
				stack = RunicRecipes.getResult(this.inventory[1]).copy();
				stack.stackSize = this.inventory[1].stackSize*2;
				if(stack.stackSize > 64) {
					EntityItem item = new EntityItem(this.worldObj, this.xCoord + 0.5F, this.yCoord + 0.5F, this.zCoord + 0.5F, stack);
					this.worldObj.spawnEntityInWorld(item);
				}else{
					this.inventory[1] = stack;						
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
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList nbttaglist = new NBTTagList();
	    for (int i = 0; i < this.inventory.length; ++i) {
	        if (this.inventory[i] != null) {
	            NBTTagCompound compound1 = new NBTTagCompound();
	            compound1.setByte("Slot", (byte)i);
	            this.inventory[i].writeToNBT(compound1);
	            nbttaglist.appendTag(compound1);
	        }
	    }
	    compound.setTag("Items", nbttaglist);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = compound.getTagList("Items", 2);
        this.inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound compound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            int j = compound1.getByte("Slot") & 255;
            if (j >= 0 && j < this.inventory.length)            {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(compound1);
            }
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
		// TODO Auto-generated method stub
		return super.getDescriptionPacket();
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		// TODO Auto-generated method stub
		super.onDataPacket(net, pkt);
	}
	

}
