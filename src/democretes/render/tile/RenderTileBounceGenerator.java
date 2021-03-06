package democretes.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import democretes.block.generators.TileBounceGenerator;
import democretes.block.transfer.TileItemTransfer;
import democretes.lib.Reference;
import democretes.render.models.ModelBounceGenerator;
import democretes.render.models.ModelItemTransfer;

public class RenderTileBounceGenerator extends TileEntitySpecialRenderer {

	ModelBounceGenerator model = new ModelBounceGenerator();
	
	private static final ResourceLocation modelTexture = new ResourceLocation(Reference.MODEL_BOUNCE_GENERATOR_TEXTURE);

	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1.0F, -1.0F, 1.0f);
		GL11.glTranslatef(-.5F, -1.5F, .5F);		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glTranslatef(0.0F, 2.0F, 0.0F);
		renderFacing(tile);		
		
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindTexture(modelTexture);
		GL11.glRotatef(-90, 0, 0, 1);
		model.render();	
		
		GL11.glPushMatrix();
	    float t = (float)Minecraft.getSystemTime();
	    GL11.glTranslated(Math.sin(t/500D)/20 - 0.025, 0, 0D);	 
		model.renderOrbA();  		
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
	    GL11.glTranslated(-(Math.sin(t/500D)/20 - 0.025), 0, 0D);	
		model.renderOrbB();   		
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();	
		
	}
	
	public void renderFacing(TileEntity tile) {
		switch (((TileBounceGenerator)tile).facing){
		case 0:
			GL11.glTranslatef(1.0F, -1.0F, 0.0F);
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F); break;
		case 1:
			GL11.glTranslatef(-1.0F, -1.0F, 0.0F);
			GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);break;
		case 3: 
			GL11.glTranslatef(-1.0F, -1.0F, 0.0F);
			GL11.glRotatef(90.0F, 1.0F, 0F, 0.0F); break;
		case 2:
			GL11.glTranslatef(1.0F, -1.0F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F); 
			GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);break;
		case 5: 
			GL11.glTranslatef(0.0F, -1.0F, -1.0F);
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(90.0F, 1.0F, 0F, 0.0F);break;
		case 4:
			GL11.glTranslatef(0.0F, -1.0F, 1.0F);
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(90.0F, 1.0F, 0F, 0.0F);
		}
	}
}
