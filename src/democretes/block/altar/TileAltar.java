package democretes.block.altar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.IIcon;
import democretes.api.altar.RitualType;
import democretes.block.TilePurityBase;
import democretes.utils.crafting.AltarRecipes;

public class TileAltar extends TilePurityBase implements IInventory{

	public ItemStack inventory;
	public RitualType ritual = RitualType.COMPLEX;
	
	public TileAltar() {
		super(10000);
	}
	
	int energy;
	@Override
	public void updateEntity() {
		if(this.worldObj.isRemote) {
			return;
		}
		if(this.inventory != null) {
			if(AltarRecipes.recipeExists(this.inventory)) {
				energy += this.extractMacht(AltarRecipes.getMacht(inventory)/20);
				if(this.energy >= AltarRecipes.getMacht(inventory)*inventory.stackSize) {
					this.energy = 0;
					ItemStack stack = AltarRecipes.getResult(this.inventory).copy();
					stack.stackSize = this.inventory.stackSize;
					this.inventory = stack.copy();
				}				
			}
			if(this.ritual != null ) {
				
			}
		}else{
			if(this.energy > 0) {
				this.energy = 0;
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
		return true;
	}	
	
	int index;
	public IIcon getRitualIcon() {
		BlockAltar altar = (BlockAltar)this.getBlockType();
		if(this.ritual != null) {
			index = this.ritual == RitualType.BASIC ? 0 : this.ritual == RitualType.ADVANCED ? 1 : 2;
			return altar.circle[index];
		}
		return null;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Index", this.index);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.index = pkt.func_148857_g().getInteger("Index");
	}

	
		
}
