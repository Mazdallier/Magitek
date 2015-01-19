package democretes.utils.nei;

import static codechicken.lib.gui.GuiDraw.changeTexture;
import static codechicken.lib.gui.GuiDraw.drawTexturedModalRect;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;
import democretes.api.RitualType;
import democretes.api.recipe.RitualRecipe;
import democretes.utils.helper.StringHelper;

public class NEIRitualRecipeHandler extends TemplateRecipeHandler {
	
	public class CachedRitualRecipe extends CachedRecipe {
		
		int[][] basicPositions = {{25, 20}, {99, 20}, {62, 88}};
		int[][] advancedPositions = {{29, 20}, {95, 20}, {25, 66}, {99, 66}, {62, 88}};
		int[][] complexPositions = {{45, 4}, {79, 4},  {22, 22}, {103, 22}, {9, 46}, {115, 45}, {22, 70}, {103, 70}, {45, 88}, {79, 88}};
		
		List<PositionedStack> input = new LinkedList<PositionedStack>();
		PositionedStack output;
		int macht;
		
		public CachedRitualRecipe(RitualRecipe recipe) {
			input.add(new PositionedStack(recipe.getCatalyst(), 62, 34));
			output = new PositionedStack(recipe.getOutput(), 147, 44);
			macht = recipe.getEnergyRequired();
			int[][] positions = null;
			if(recipe.getRitual() == RitualType.BASIC) {
				positions = basicPositions;
			}else if(recipe.getRitual() == RitualType.ADVANCED) {
				positions = advancedPositions;
			}else{
				positions = complexPositions;
			}
			for(int i = 0; i < positions.length; i++) {
				input.add(new PositionedStack(recipe.getInput()[i], positions[i][0], positions[i][1]));
			}
		}

		@Override
		public PositionedStack getResult() {
			return output;
		}
		
		@Override
		public List<PositionedStack> getIngredients() {
			return input;
		}
		
	}
	
	@Override
	public int recipiesPerPage() {
		return 1;
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if(outputId.equals("magitekInfusion") && getClass() == NEIRitualRecipeHandler.class) {
			for(RitualRecipe recipe: RitualRecipe.ritualRecipes) {
				if(recipe.getRitual() != getRitualType()) {
					continue;
				}
				if(recipe.getOutput() != null) {
					arecipes.add(new CachedRitualRecipe(recipe));
				}
			}
		}else{
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@Override
	public void drawExtras(int id) {
		CachedRitualRecipe recipe = (CachedRitualRecipe)arecipes.get(id);
		Minecraft.getMinecraft().fontRenderer.drawString(StringHelper.GRAY + "Macht: " + recipe.macht, 50, 110, 0);
	}
	
	@Override
	public void drawBackground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        changeTexture(getGuiTexture());
        drawTexturedModalRect(0, 0, 3, 8, 166, 110);
    }
	
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for(RitualRecipe recipe: RitualRecipe.ritualRecipes) {
			if(recipe.getRitual() != getRitualType()) {
				continue;
			}
			if(NEIServerUtils.areStacksSameTypeCrafting(recipe.getOutput(), result)) {
				if(recipe.getOutput() != null) {
					arecipes.add(new CachedRitualRecipe(recipe));
				}
			}
		}
	}
	
	@Override
    public void loadUsageRecipes(ItemStack ingredient)  {
		for(RitualRecipe recipe: RitualRecipe.ritualRecipes) {
			if(recipe.getRitual() != getRitualType()) {
				continue;
			}
			if(NEIServerUtils.areStacksSameTypeCrafting(recipe.getCatalyst(), ingredient)) {
				if(recipe.getOutput() != null) {
					arecipes.add(new CachedRitualRecipe(recipe));
				}
			}
			for(ItemStack stack : recipe.getInput()) {
				if(NEIServerUtils.areStacksSameTypeCrafting(stack, ingredient)) {
					if(recipe.getOutput() != null) {
						arecipes.add(new CachedRitualRecipe(recipe));
					}
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
		return StringHelper.localize("magitek.nei.ritual." + getRitualType().toString().toLowerCase());
	}

	@Override
	public String getGuiTexture() {
		return new ResourceLocation("democretes", "misc/nei/nei_ritual_" + getRitualType().toString().toLowerCase() + ".png").toString();
	}
	
	public RitualType getRitualType() {
		return null;
	}
	
	public static class NEIBasicRitualHandler extends NEIRitualRecipeHandler {
		
		@Override
		public RitualType getRitualType() {
			return RitualType.BASIC;
		}		
	}
	public static class NEIAdvancedRitualHandler extends NEIRitualRecipeHandler {
		
		@Override
		public RitualType getRitualType() {
			return RitualType.ADVANCED;
		}		
	}
	public static class NEIComplexRitualHandler extends NEIRitualRecipeHandler {
		
		@Override
		public RitualType getRitualType() {
			return RitualType.COMPLEX;
		}		
	}
}
