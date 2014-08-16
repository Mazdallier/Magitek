package democretes.block.generators;

import democretes.item.ItemsMT;
import democretes.utils.crafting.RunicRecipes;
import net.minecraft.item.ItemStack;

public class TileRunicGenerator extends TileGeneratorBase {
	
	ItemStack inventory;

	@Override
	protected boolean canGenerate() {
		return this.inventory != null;
	}

	int count;
	@Override
	protected int getFuel() {
		if(!RunicRecipes.recipeExists(this.inventory)) {
			return 0;
		}
		if(count < 20) {
			count ++;
			return 0;
		}
		count =  0;
		ItemStack stack = this.inventory;
		this.inventory.stackSize--;
		if(this.inventory.stackSize == 0) {
			this.inventory = null;
		}
		return RunicRecipes.getMacht(stack)*4/5;
	}

	@Override
	protected void renderWhenActive() {}

}
