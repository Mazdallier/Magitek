package democretes.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderSpreader extends TileEntitySpecialRenderer{
	
	private IModelCustom model;
	private ResourceLocation texture = new ResourceLocation("democretes", "textures/models/spreader.png");
	
	public RenderSpreader() {
		model = AdvancedModelLoader.loadModel(new ResourceLocation("democretes","misc/models/spreader.obj"));
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f((float)7/256, (float)132/256, (float)212/256, 0.7F);
		
		
	    float t = (float)Minecraft.getSystemTime();
	    GL11.glTranslatef((float)x + 0.5F, (float)y + 0.2F, (float)z + 0.5F);
	    GL11.glTranslated(0D, Math.sin(t/700D)/40 - 0.025, 0D);	    
	    
	    GL11.glScalef(0.175F, 0.17F, 0.175F);
	    GL11.glRotatef(t/250, 0.0F, 1.0F, 0.0F);
	    GL11.glTranslatef(0.0F, 1.75F, 0.0F);
	    
	    Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
	    model.renderAll();
	    	    
	    GL11.glPopMatrix();		    
	}

}
