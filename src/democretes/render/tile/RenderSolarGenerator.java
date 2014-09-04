package democretes.render.tile;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import democretes.block.MTBlocks;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.lib.Reference;
import democretes.render.models.ModelPurityGenerator;
import democretes.render.models.ModelSolarGenerator;

public class RenderSolarGenerator extends TileEntitySpecialRenderer {

	ModelSolarGenerator model = new ModelSolarGenerator();

	private static final ResourceLocation modelTexture = new ResourceLocation(Reference.MODEL_SOLAR_GENERATOR_TEXTURE);

	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1F, -1F, 1F);
		GL11.glTranslatef(-.5F, -1.5F, .5F);
		GL11.glRotatef(-90F, 0F, 1F, 0F);
		model.Shape8.rotateAngleX = ((TileSolarGenerator)tile).angle;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindTexture(modelTexture);
		model.render();

		GL11.glPopMatrix();
	}
	
}
