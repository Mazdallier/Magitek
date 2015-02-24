package democretes.block.machines;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

public class TileMachtFurnace extends TileMachineBase {	

	public TileMachtFurnace() {
		super(2000);
	}

	@Override
	public void doStuff() {
		List<EntityItem> entities = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord+1, this.yCoord+2, this.zCoord+1));
		for(EntityItem item : entities) {
			if(FurnaceRecipes.smelting().getSmeltingResult(item.getEntityItem()) != null && this.getMachtStored() > 100) {
				this.extractMacht(100);
				item.setEntityItemStack(FurnaceRecipes.smelting().getSmeltingResult(item.getEntityItem()).copy());
				item.delayBeforeCanPickup = 20;
			}
		}
	}

	@Override
	public boolean canActivate() {
		return true;
	}


}
