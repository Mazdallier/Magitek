package democretes.block.altar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import democretes.block.BlocksMT;
import democretes.block.TilePurityBase;
import democretes.utils.crafting.AltarRecipes;

public class TileAltar extends TilePurityBase implements IInventory{

	public ItemStack inventory;
	public RitualType ritual;
	
	public TileAltar() {
		super(5000);
	}
	
	int energy;
	@Override
	public void updateEntity() {
		if(this.inventory != null) {
			if(AltarRecipes.getResult(this.inventory) != null) {
				energy += this.extractMacht(AltarRecipes.getMacht(inventory)/20);
				if(this.energy >= AltarRecipes.getMacht(inventory)*inventory.stackSize) {
					energy -= this.receiveMacht(this.energy);
					int size = this.inventory.stackSize;
					this.inventory = AltarRecipes.getResult(this.inventory);
					this.inventory.stackSize = size;
				}
			}
		}else{
			if(this.energy > 0) {
				energy -= this.receiveMacht(energy);
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
		if(this.inventory.stackSize < 0) {
			this.inventory.stackSize = 0;
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
	
	public IIcon getRitualIcon() {
		BlockAltar altar = (BlockAltar)this.getBlockType();
		if(ritual == RitualType.BASIC) {
			return altar.basic;
		}
		if(ritual == RitualType.ADVANCED) {
			return altar.advanced;
		}
		if(ritual == RitualType.COMPLEX) {
			return altar.complex;
		}
		return null;
	}

	public static enum RitualType {
		BASIC, ADVANCED, COMPLEX
	}
		
}
