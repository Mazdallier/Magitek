package democretes.block.generators;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class TileCoalGenerator extends TileGeneratorBase implements IInventory{
	
	ItemStack inventory;
	int fuel;
	
	@Override
	protected boolean canGenerate() {
		if(this.fuel + GameRegistry.getFuelValue(inventory) < 2000) {
			this.fuel += GameRegistry.getFuelValue(inventory);
			decrStackSize(0, 1);			
		}
		return fuel > 0;
	}

	@Override
	protected int getFuel() {
		int amount = Math.min(100, fuel);
		fuel -= amount;
		return amount;
	}

	@Override
	protected void renderWhenActive() {}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		this.inventory.stackSize -= amount;
		if(this.inventory.stackSize <=  0) {
			this.inventory = null;
		}
		return this.inventory;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return this.inventory;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inventory = stack.copy();
	}

	@Override
	public String getInventoryName() {
		return "InvCoalGenerator";
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
		if(GameRegistry.getFuelValue(stack) > 0) {
			return true;
		}
		return false;
	}

}
