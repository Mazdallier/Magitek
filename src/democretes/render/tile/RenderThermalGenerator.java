package democretes.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import democretes.lib.Reference;
import democretes.render.models.ModelAltar;
import democretes.render.models.ModelThermalGenerator;

public class RenderThermalGenerator extends TileEntitySpecialRenderer {
	
	ModelThermalGenerator model = new ModelThermalGenerator();

	private static final ResourceLocation modelTexture = new ResourceLocation(Reference.MODEL_THERMAL_TEXTURE);
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1F, -1F, 1F);
		GL11.glTranslatef(-.5F, -1.5F, .5F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glScalef(1.1F, 1.1F, 1.1F);
		GL11.glTranslatef(0.0F, -0.15F, 0.0F);
		bindTexture(modelTexture);
		model.render();

		GL11.glPopMatrix();	
	}
}
