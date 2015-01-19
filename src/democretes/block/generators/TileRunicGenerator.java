package democretes.block.generators;

import cpw.mods.fml.common.FMLLog;
import democretes.Magitek;
import democretes.api.RitualType;
import democretes.api.helpers.RitualHelper;
import democretes.api.helpers.RunicHelper;
import democretes.item.MTItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileRunicGenerator extends TileGeneratorBase implements IInventory {
	
	public ItemStack inventory;
	
	public TileRunicGenerator() {
		super(25000);
	}

	@Override
	protected boolean canGenerate() {
		if(this.inventory == null) {
			return false;
		}
		return RunicHelper.recipeExistsFromOutput(this.inventory);
	}

	int count;
	@Override
	protected int getFuel() {
		if(count++ >= 60) {
			count =  0;
			ItemStack stack = this.inventory;
			this.inventory.stackSize--;
			if(this.inventory.stackSize == 0) {
				this.inventory = null;
			}
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			for(int i = 0; i < 5; i++) {
				Magitek.proxy.orbFX(this.worldObj, this.xCoord + 0.5D, this.yCoord + 1.0D, this.zCoord + 0.5D, (Math.random() - Math.random())/10, (Math.random() - Math.random())/10, (Math.random() - Math.random())/10, (float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(), 2, true);
			}
			this.increasePurity(2);
			return RunicHelper.getMachtFromOutput(stack)*4/5;
		}
		return 0;
	}

	@Override
	protected void renderWhenActive() {}

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
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory ==  null ? null : this.inventory.copy();
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
		return "TileAltarInventory";
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
		return RunicHelper.recipeExistsFromOutput(stack);
	}	

}
