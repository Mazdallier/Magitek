package democretes.api.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class ChipCrafterRecipe {
	
	ItemStack catalyst;
	ItemStack output;
	int macht;
	int purity;
	
	public ChipCrafterRecipe(ItemStack catalyst, ItemStack output, int macht, int purity) {
		this.catalyst = catalyst;
		this.output = output;
		this.macht = macht;
		this.purity = purity;
		runeRecipes.add(this);
	}
	
	public ItemStack getCatalyst() {
		return catalyst;
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public int getEnergyRequired() {
		return macht;
	}
	
	public int getPurity() {
		return purity;
	}
	
	public static List<ChipCrafterRecipe> runeRecipes = new ArrayList<ChipCrafterRecipe>();


}
