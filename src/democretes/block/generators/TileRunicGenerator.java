package democretes.block.generators;

import cpw.mods.fml.common.FMLLog;
import democretes.item.ItemsMT;
import democretes.utils.crafting.RunicHelper;
import net.minecraft.item.ItemStack;

public class TileRunicGenerator extends TileGeneratorBase {
	
	ItemStack inventory;
	
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
		if(count >= 40) {
			count =  0;
			ItemStack stack = this.inventory;
			this.inventory.stackSize--;
			if(this.inventory.stackSize == 0) {
				this.inventory = null;
			}
			return RunicHelper.getMachtFromOutput(stack)*4/5;
		}
		count ++;
		return 0;
	}

	@Override
	protected void renderWhenActive() {}

}
