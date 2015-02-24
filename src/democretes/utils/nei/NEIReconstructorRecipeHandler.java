package democretes.utils.nei;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import democretes.api.recipe.ReconstructorRecipe;
import democretes.utils.helper.StringHelper;

public class NEIReconstructorRecipeHandler extends TemplateRecipeHandler {
	
	public class CachedReconstructorRecipe extends CachedRecipe {
		
		PositionedStack output;
		int macht;
		
		public CachedReconstructorRecipe(ReconstructorRecipe recipe) {
			output = new PositionedStack(recipe.getOutput(), 71, 8);
			macht = recipe.getEnergy();
		}

		@Override
		public PositionedStack getResult() {
			return output;
		}
		
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if(outputId.equals("magitekInfusion") && getClass() == NEIReconstructorRecipeHandler.class) {
			for(ReconstructorRecipe recipe: ReconstructorRecipe.reconstructorRecipes) {
				if(recipe.getOutput() != null) {
					arecipes.add(new CachedReconstructorRecipe(recipe));
				}
			}
		}else{
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@Override
	public void drawExtras(int id) {
		CachedReconstructorRecipe recipe = (CachedReconstructorRecipe)arecipes.get(id);
		Minecraft.getMinecraft().fontRenderer.drawString(StringHelper.GRAY + "Macht: " + recipe.macht, 45, 70, 0);
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for(ReconstructorRecipe recipe: ReconstructorRecipe.reconstructorRecipes) {
			if(NEIServerUtils.areStacksSameTypeCrafting(recipe.getOutput(), result)) {
				if(recipe.getOutput() != null) {
					arecipes.add(new CachedReconstructorRecipe(recipe));
				}
			}
		}
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "magitekInfusion";
	}
	
	@Override
	public String getRecipeName() {
		return StringHelper.localize("magitek.nei.reconstructor");
	}

	@Override
	public String getGuiTexture() {
		return new ResourceLocation("democretes", "misc/nei/nei_reconstructor.png").toString();
	}
}
