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
	
	int facing;
	@Override
	public void doStuff() {
		int negX = facing == 4 ? -2 : 0;
		int posX = facing == 5 ? 2 : 1;
		int negY = facing == 0 ? -2 : 0;
		int posY = facing == 1 ? 2 : 1;
		int negZ = facing == 2 ? -2 : 0;
		int posZ = facing == 3 ? 2 : 1;
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(this.xCoord+negX, this.yCoord+negY, this.zCoord+negZ, this.xCoord+posX, this.yCoord+posY, this.zCoord+posZ);
		List<EntityItem> entities = this.worldObj.getEntitiesWithinAABB(EntityItem.class, box);
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
