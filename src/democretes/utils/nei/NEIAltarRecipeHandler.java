package democretes.utils.nei;

import java.awt.Rectangle;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import cpw.mods.fml.common.FMLLog;
import democretes.api.recipe.AltarRecipe;
import democretes.utils.helper.StringHelper;

public class NEIAltarRecipeHandler extends TemplateRecipeHandler {
	
	public class CachedAltarRecipe extends CachedRecipe {
		
		PositionedStack input;
		PositionedStack output;
		int macht;
		
		public CachedAltarRecipe(AltarRecipe recipe) {
			input = new PositionedStack(recipe.getInput(), 28, 2);
			output = new PositionedStack(recipe.getOutput(), 110, 23);
			macht = recipe.getEnergyRequired();
		}
		
		@Override
		public PositionedStack getIngredient() {
			return input;
		}

		@Override
		public PositionedStack getResult() {
			return output;
		}
		
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if(outputId.equals("magitekInfusion") && getClass() == NEIAltarRecipeHandler.class) {
			for(AltarRecipe recipe: AltarRecipe.altarRecipes) {
				if(recipe.getOutput() != null) {
					arecipes.add(new CachedAltarRecipe(recipe));
				}
			}
		}else{
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@Override
	public void drawExtras(int id) {
		CachedAltarRecipe recipe = (CachedAltarRecipe)arecipes.get(id);
		Minecraft.getMinecraft().fontRenderer.drawString(StringHelper.GRAY + "Macht: " + recipe.macht, 62, 50, 0);
	}
	
	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(90, 32, 22, 16), "magitekInfusion"));
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for(AltarRecipe recipe: AltarRecipe.altarRecipes) {
			if(NEIServerUtils.areStacksSameTypeCrafting(recipe.getOutput(), result)) {
				if(recipe.getOutput() != null) {
					arecipes.add(new CachedAltarRecipe(recipe));
				}
			}
		}
	}
	
	@Override
    public void loadUsageRecipes(ItemStack ingredient)  {
		for(AltarRecipe recipe: AltarRecipe.altarRecipes) {
			if(NEIServerUtils.areStacksSameTypeCrafting(recipe.getInput(), ingredient)) {
				if(recipe.getOutput() != null) {
					arecipes.add(new CachedAltarRecipe(recipe));
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
		return StringHelper.localize("magitek.nei.infusion");
	}

	@Override
	public String getGuiTexture() {
		return new ResourceLocation("democretes", "misc/nei/nei_altar.png").toString();
	}

}
